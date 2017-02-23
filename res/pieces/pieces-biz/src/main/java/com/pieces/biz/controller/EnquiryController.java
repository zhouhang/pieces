package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryRecordVo;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.dto.UserValidate;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.ExcelParse;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.WebUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 询价前端
 * Created by wangbin on 2016/7/22.
 */
@Controller
@RequestMapping(value = "/center/enquiry")
public class EnquiryController extends BaseController{

    @Autowired
    private EnquiryBillsService enquiryBillsService;
    @Autowired
    private CommoditySearchService commoditySearchService;
    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;


    private List<EnquiryCommoditys> handleEnquiryAgain(Integer userId,Integer billId){
        List<EnquiryCommoditys> enquiryCommoditysList =  enquiryCommoditysService.findByBillId(billId,userId,null);
        return enquiryCommoditysList;
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
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "record")
    @BizLog(type = LogConstant.enquiry, desc = "询价记录页面")
    public String enquiryRecord(ModelMap modelMap,
                                Integer pageSize,
                                Integer pageNum,
                                EnquiryRecordVo enquiryRecordVo){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        User user = (User)httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());

        enquiryRecordVo.setUserId(user.getId());
        modelMap.put("status",enquiryRecordVo.getStatus());
        //查询用户的询价单
        PageInfo<EnquiryBillsVo> billsPageInfo =  enquiryBillsService.findByPage(pageNum,pageSize,enquiryRecordVo);
        modelMap.put("billsPage",billsPageInfo);
        enquiryRecordVo.setUserId(null);
        UserValidate userValidate = userService.validateUser(user);
        modelMap.put("userValidate",userValidate);
        return "user_enquiry_record";
    }

    /**
     * 询价记录详情页面
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "detail")
    @BizLog(type = LogConstant.enquiry, desc = "询价记录详情页面")
    public String enquiryRecordDetail(HttpServletRequest request,
                                ModelMap modelMap,Integer billId){
        User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        // 用户只能看到他自己的询价单 TODO:
        // 把询价单状态修改为已读
        enquiryBillsService.read(billId);
        EnquiryBillsVo vo =  enquiryBillsService.findVOById(billId);
        UserValidate userValidate = userService.validateUser(user);
        modelMap.put("userValidate",userValidate);
        modelMap.put("bill", vo);
        return "user_enquiry_record_detail";
    }


    /**
     * 查询询价单下所有订购商品
     */
    @RequestMapping(value = "commodity")
    public void commodityAll(HttpServletRequest request,
                             HttpServletResponse response,
                             Integer billId){
        User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<EnquiryCommoditys> enquiryCommoditysList =  handleEnquiryAgain(user.getId(),billId);
        WebUtil.print(response,new Result(true).data(enquiryCommoditysList));
    }

    /**
     * 询价成功页面
     * @return
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(){
        return "user_enquiry_message";
    }

    /**
     * 解析上传的excel 文件
     * @param excel
     * @return
     */
    @RequestMapping(value = "/parseXsl", method = RequestMethod.POST)
    public void parseXsl(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam(required = false) MultipartFile excel) {
        List<EnquiryCommoditys> list = null;
        try {
            list = ExcelParse.parseEnquiryXLS(excel.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        WebUtil.print(response,list);

    }


    /**
     * 导出勾选的商品报价
     * @param response
     * @param request
     * @param billId 询价单Id
     */
    @RequestMapping(value = "/download")
    public void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, Integer billId){
        enquiryBillsService.exportEnquiryExcel(response, request, billId);
    }

}