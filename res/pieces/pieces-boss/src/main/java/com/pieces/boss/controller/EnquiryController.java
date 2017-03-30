package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.CommodityService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.impl.SmsService;
import com.pieces.service.utils.ExcelParse;
import com.pieces.tools.annotation.SameUrlData;
import com.pieces.tools.annotation.TokenHold;
import com.pieces.tools.annotation.TokenVerify;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 7/25/16.
 * 询价
 */
@Controller
@RequestMapping("/enquiry")
public class EnquiryController extends BaseController{

    @Autowired
    EnquiryBillsService enquiryBillsService;

    @Autowired
    EnquiryCommoditysService enquiryCommoditysService;

    @Autowired
    UserService userService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    HttpSession session;

    @Autowired
    SmsService smsService;

    @RequiresPermissions(value = "enquiry:index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @BizLog(type = LogConstant.enquiry, desc = "询价列表页面")
    public String index(EnquiryBillsVo enquiryBillsVO, Integer pageNum, Integer pageSize, ModelMap modelMap) {
        PageInfo<EnquiryBillsVo> pageInfo = enquiryBillsService.findByParam(enquiryBillsVO,pageNum,pageSize);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("param", enquiryBillsVO);
        modelMap.put("paramGet", Reflection.serialize(enquiryBillsVO));
        return "enquiry";
    }

    @RequiresPermissions(value = "enquiry:info")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @BizLog(type = LogConstant.enquiry, desc = "询价详情页面")
    public String detail(@PathVariable("id") Integer id,String create, ModelMap modelMap) {
        EnquiryBillsVo vo = enquiryBillsService.findVOById(id);
        if (vo.getExpireDate() == null){
            //报价时间为空时设置默认报价时间
            LocalDate date = LocalDate.now();
            date = date.plusDays(3);
            vo.setExpireDate(date.toDate());
        }

        if (create!= null){
            modelMap.put("create", create);
        }
        modelMap.put("enquiryBills", vo);
        return "enquiry-detail";
    }

    /**
     * 商品报价
     * 更新报价时间和最后修改时间
     * @param commodityses
     * @return
     */
    @RequiresPermissions(value = "enquiry:quote")
    @RequestMapping(value = "/quoted", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = LogConstant.enquiry, desc = "询价商品报价")
    public Result quoted(@RequestBody List<EnquiryCommoditys> commodityses, Integer billsId) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Member member = (Member)session.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        enquiryCommoditysService.quoted(commodityses, member.getId(), billsId);
        return new Result(true).info("报价成功!");
    }



    /**
     * 修改商品报价
     * 只更新最后修改时间
     * @param commodityses
     * @return
     */
    @RequiresPermissions(value = "enquiry:quote")
    @RequestMapping(value = "/quotedUpdate", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = LogConstant.enquiry, desc = "修改询价商品报价")
    public Result quotedUpdate(@RequestBody List<EnquiryCommoditys> commodityses, Integer billsId) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Member member = (Member)session.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        enquiryCommoditysService.quotedUpdate(commodityses, member.getId(), billsId);
        return new Result(true).info("报价成功!");
    }

    /**
     * 上传报价附件
     * @param file
     */
    @RequestMapping(value = "/excel/{id}")
    public String importEnquiryExcel(@RequestParam(required = true) MultipartFile file, @PathVariable("id") Integer id, ModelMap modelMap){
        // TODO: 判断文件后缀名 只能上传excel 类型的文件
        EnquiryBillsVo vo = enquiryBillsService.importEnquiryExcel(file, id);
        modelMap.put("enquiryBills", vo);
        return "enquiry-detail";
    }

    /**
     * 下载报价excel
     */
    @RequestMapping(value = "/download/{id}")
    public void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") Integer id){
        enquiryBillsService.exportEnquiryExcel(response, request, id);
    }


    /**
     * 新建询价单客户列表
     * @param pageNum
     * @param pageSize
     * @param model
     * @param userVo
     * @return
     */
    @RequestMapping(value = "customer")
    @BizLog(type = LogConstant.enquiry, desc = "新建询价单客户选择页面")
    public String customerEnquiryIndex(Integer pageNum,
                                     Integer pageSize,
                                     ModelMap model,
                                     UserVo userVo){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        // 删除的客户不显示
        userVo.setIsDel(false);
        PageInfo<UserVo> customerPage =  userService.findVoByCondition(userVo,pageNum,pageSize);
        model.put("customerParams", Reflection.serialize(userVo));
        model.put("customerPage",customerPage);
        return "enquiry-customers";
    }

    /**
     * 创建询价单页面
     * @param userId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "create/{id}")
    @BizLog(type = LogConstant.enquiry, desc = "新建询价单客户选择页面")
    public String createEnquiry(@PathVariable("id") Integer userId, ModelMap modelMap){
        User user = userService.findById(userId);
        List<EnquiryCommoditys> commoditys = (List<EnquiryCommoditys>) session.getAttribute("enquiryCommodityName");
        if (commoditys!= null) {
            modelMap.put("commoditys", commoditys);
        }


        //报价时间为空时设置默认报价时间
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        modelMap.put("expireDate", date);

        modelMap.put("user", user);
        return "enquiry-create";
    }

    /**
     * 保存询价单
     * @return
     */
    @RequestMapping(value = "save")
    @BizLog(type = LogConstant.enquiry, desc = "新建询价单客户选择页面")
    @ResponseBody
    public Result saveEnquiry(@RequestBody List<EnquiryCommoditys> commoditys, Integer userId){
        Member member = (Member)session.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        User user = userService.findById(userId);
        for(EnquiryCommoditys commodity:commoditys){
            Commodity data = commodityService.findById(commodity.getCommodityId());
            commodity.setCommodityId(data.getId());
            commodity.setLevel(data.getLevel());
            commodity.setOrigin(data.getOriginOf());
            commodity.setSpecs(data.getSpec());
            commodity.setCommodityName(data.getName());
        }

        if(commoditys == null || commoditys.size()==0) {
            throw new RuntimeException("必须提交询价商品才能询价成功");
        }

        Integer  billId = enquiryBillsService.create(commoditys, user);

        // 更新报价单的报价有效期
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(billId);
        enquiryBills.setExpireDate(commoditys.get(0).getExpireDate());
        enquiryBills.setQuotedTime(new Date());
        enquiryBills.setMemberId(member.getId());

        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(member.getId());
        enquiryBills.setStatus(1);
        enquiryBills.setType(1);
        enquiryBillsService.update(enquiryBills);

        session.removeAttribute("enquiryCommodityName");

        //报价成功后通知客户 TODO:
        EnquiryBillsVo billsVo = enquiryBillsService.findVOById(billId);
        smsService.sendQuoted(billsVo);

        // 保存报价成功后跳到编辑页面并提示报价成功
        return new Result(true).data("/enquiry/"+billId+"?create=create");
    }

    /**
     * 上传报价附件
     * @param file
     */
    @RequestMapping(value = "/commodityName")
    public String importEnquiryName(@RequestParam(required = true) MultipartFile file, Integer userId) throws IOException, InvalidFormatException {

//        EnquiryBillsVo vo = enquiryBillsService.importEnquiryExcel(file, id);
        List<EnquiryCommoditys> commoditys = ExcelParse.parseEnquiryXLS(file.getInputStream());
        session.setAttribute("enquiryCommodityName",commoditys);
        return "redirect:/enquiry/create/"+userId;
    }

}
