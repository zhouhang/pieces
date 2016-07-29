package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.dao.vo.EnquiryRecordVo;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.ExcelParse;
import com.pieces.tools.utils.CookieUtils;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 询价前端
 * Created by wangbin on 2016/7/22.
 */
@Controller
@RequestMapping(value = "/center/enquiry")
public class EnquiryController extends BaseController{

    private final int COOKIE_EXPIRE = 3600;

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private EnquiryBillsService enquiryBillsService;
    @Autowired
    private CommoditySearchService commoditySearchService;
    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;


    /**
     * 客户询价页面
     */
    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        ModelMap modelMap,
                        Integer billId,
                        Integer commodityId){
        //重新询价删除之前的询价记录
        if(billId!=null){
            List<EnquiryCommoditys> enquiryCommoditysList = handleEnquiryAgain(billId);
            if(!enquiryCommoditysList.isEmpty()){
                modelMap.put("enquiryCommoditysList",enquiryCommoditysList);
                modelMap.put("billId",billId);
                return "user_enquiry";
            }
        }


        Set<Integer> cookieSet = null;
        String cookieValue = CookieUtils.getCookieValue(request, BasicConstants.ENQUIRY_COOKIES);
        if(StringUtils.isBlank(cookieValue)){
            cookieSet = new HashSet<>();
        }else{
            cookieSet = GsonUtil.jsonToEntity(cookieValue,Set.class);
        }
        if(commodityId!=null){
            cookieSet.add(commodityId);
        }

        //删除没用的ID
        Set<Integer> removesSet = new HashSet<>();

        //把询价的商品ID转换成集合对象
        List<CommodityVO> list = new ArrayList<>();
        if(!cookieSet.isEmpty()){
           Iterator<Integer> it = cookieSet.iterator();
           while (it.hasNext()){
               Integer id = it.next();
               CommodityVO commodityVO =  commodityService.findVoById(id);
               if(commodityVO!=null){
                   list.add(commodityVO);
               }else{
                   removesSet.add(id);
               }
           }
        }
        cookieSet.removeAll(removesSet);
        CookieUtils.setCookie(response, BasicConstants.ENQUIRY_COOKIES,GsonUtil.toJson(cookieSet),COOKIE_EXPIRE);
        modelMap.put("commodityList",list);
        return "user_enquiry";
    }


    private List<EnquiryCommoditys> handleEnquiryAgain(Integer billId){
        List<EnquiryCommoditys> enquiryCommoditysList =  enquiryCommoditysService.findByBillId(billId,null);
        return enquiryCommoditysList;
    }


    /**
     * 删除询价单里的一个商品
     */
    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request,
                       HttpServletResponse response,
                       Integer commodityId){
        String cookieValue = CookieUtils.getCookieValue(request, BasicConstants.ENQUIRY_COOKIES);
        Set<Integer> cookieSet = GsonUtil.jsonToEntity(cookieValue,Set.class);
        if(!cookieSet.isEmpty()){
            cookieSet.remove(commodityId);
        }
        CookieUtils.setCookie(response, BasicConstants.ENQUIRY_COOKIES,GsonUtil.toJson(cookieSet),COOKIE_EXPIRE);
        WebUtil.print(response,new Result(true));
    }

    /**
     * 提交询价单
     */
    @RequestMapping(value = "submit")
    public void submit(HttpServletRequest request,
                       HttpServletResponse response,
                       Integer billId,
                       Integer[] commodityId,
                       String[] commodityName,
                       String[] specs,
                       String[] level,
                       String[] origin,
                       Integer[] amount,
                       Double[] expectPrice,
                       Date[] expectDate){
            String message = "您的询价提交成功!";
            List<EnquiryCommoditys> list = params2Object(commodityId,commodityName,specs,level,origin,amount,expectPrice,expectDate);
            User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
            if(billId==null){
                enquiryBillsService.create(list,user);
            }else{
                try {
                    message="您的询价单重新修改成功!";
                    enquiryBillsService.update(list,user,billId);
                }catch (Exception e){
                    WebUtil.print(response,new Result(true).info(e.getMessage()));
                    return;
                }
            }
            WebUtil.print(response,new Result(true).info(message));
    }

    /**
     * 联想输入
     * @param request
     * @param response
     * @param commodityName
     */
    @RequestMapping(value = "auto")
    public void inputAuto(HttpServletRequest request,
                          HttpServletResponse response,
                          String commodityName){
        List<CommodityDoc> commodityDocList = commoditySearchService.findByCommodityName(commodityName);
        WebUtil.print(response,new Result(true).data(commodityDocList));
    }


    /**
     * 询价记录页面
     * @param request
     * @param response
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "record")
    public String enquiryRecord(HttpServletRequest request,
                                HttpServletResponse response,
                                ModelMap modelMap,
                                Integer pageSize,
                                Integer pageNum,
                                EnquiryRecordVo enquiryRecordVo){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<EnquiryBills> billsPageInfo =  enquiryBillsService.findByPage(pageNum,pageSize,enquiryRecordVo);
        modelMap.put("billsPage",billsPageInfo);
        String enquiryRecordParam = enquiryRecordVo.toString();
        if(StringUtils.isNotBlank(enquiryRecordParam)){
            enquiryRecordParam = enquiryRecordVo.toString().substring(1);
        }
        modelMap.put("enquiryRecordParam",enquiryRecordParam);
        return "user_enquiry_record";
    }

    /**
     * 查询询价单下所有订购商品
     */
    @RequestMapping(value = "commodity")
    public void commodityAll(HttpServletRequest request,
                             HttpServletResponse response,
                             Integer billId){
        List<EnquiryCommoditys> enquiryCommoditysList =  enquiryCommoditysService.findByBillId(billId,null);
        WebUtil.print(response,new Result(true).data(enquiryCommoditysList));
    }


    private List<EnquiryCommoditys> params2Object(Integer[] commodityId,
                                                   String[] commodityName,
                                                   String[] specs,
                                                   String[] level,
                                                   String[] origin,
                                                   Integer[] amount,
                                                   Double[] expectPrice,
                                                   Date[] expectDate){
        List<EnquiryCommoditys> commoditysList = new ArrayList<>();
        for(int i=0;i<commodityName.length;i++){
            EnquiryCommoditys enquiryCommoditys = new EnquiryCommoditys();
            enquiryCommoditys.setCommodityName(commodityName[i]);
            enquiryCommoditys.setSpecs(specs[i]);
            enquiryCommoditys.setLevel(level[i]);
            enquiryCommoditys.setOrigin(origin[i]);
            enquiryCommoditys.setAmount(amount[i]);
            enquiryCommoditys.setExpectDate(expectDate[i]);
            commoditysList.add(enquiryCommoditys);
        }
        if(commodityId!=null&&commodityId.length>0){
            for(int i=0;i<commodityId.length;i++){
                commoditysList.get(i).setCommodityId(commodityId[i]);
            }
        }
        if(expectPrice!=null||expectPrice.length>0){
            for(int i=0;i<expectPrice.length;i++){
                commoditysList.get(i).setExpectPrice(expectPrice[i]);
            }
        }

        return commoditysList;
    }


    /**
     * 解析上传的excel 文件
     * @param excel
     * @return
     */
    @RequestMapping(value = "parseXsl", method = RequestMethod.POST)
    @ResponseBody
    public List<EnquiryCommoditys> parseXsl(@RequestParam(required = false) MultipartFile excel) {
        List<EnquiryCommoditys> list = null;
        try {
            list = ExcelParse.parseEnquiryXLS(excel.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return list;
    }
}
