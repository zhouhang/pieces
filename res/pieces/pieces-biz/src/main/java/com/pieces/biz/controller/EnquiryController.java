package com.pieces.biz.controller;

import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.log.util.JSONUtils;
import com.pieces.tools.utils.CookieUtils;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 客户询价页面
     */
    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        ModelMap modelMap,
                        Integer commodityId){
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
                       Integer[] commodityId,
                       String[] commodityName,
                       String[] specs,
                       String[] level,
                       String[] origin,
                       Integer[] amount,
                       Double[] expectPrice,
                       Date[] expectDate){
        List<EnquiryCommoditys> list = params2Object(commodityId,commodityName,specs,level,origin,amount,expectPrice,expectDate);
        User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        enquiryBillsService.create(list,user);
        WebUtil.print(response,new Result(true));
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
                                ModelMap modelMap){


        return "user_enquiry_record";
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
        if(commodityId.length>0){
            for(int i=0;i<commodityId.length;i++){
                commoditysList.get(i).setCommodityId(commodityId[i]);
            }
        }
        if(expectPrice.length>0){
            for(int i=0;i<expectPrice.length;i++){
                commoditysList.get(i).setExpectPrice(expectPrice[i]);
            }
        }

        return commoditysList;
    }


}
