package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.Member;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.annotation.SameUrlData;
import com.pieces.tools.annotation.TokenHold;
import com.pieces.tools.annotation.TokenVerify;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
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
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        EnquiryBillsVo vo = enquiryBillsService.findVOById(id);
        if (vo.getExpireDate() == null){
            //报价时间为空时设置默认报价时间
            LocalDate date = LocalDate.now();
            date = date.plusDays(3);
            vo.setExpireDate(date.toDate());
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
}
