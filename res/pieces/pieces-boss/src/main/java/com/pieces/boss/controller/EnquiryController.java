package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.Member;
import com.pieces.dao.vo.EnquiryBillsVO;
import com.pieces.dao.vo.EnquiryCommoditysVO;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(EnquiryBillsVO enquiryBillsVO, Integer pageNum, Integer pageSize, ModelMap modelMap) {

        PageInfo<EnquiryBillsVO> pageInfo = enquiryBillsService.findByParam(enquiryBillsVO,pageNum,pageSize);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("param", enquiryBillsVO);
        modelMap.put("paramGet", Reflection.serialize(enquiryBillsVO));
        return "enquiry";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        EnquiryBillsVO vo = enquiryBillsService.findVOById(id);
        modelMap.put("enquiryBills", vo);
        return "enquiry-detail";
    }

    /**
     * 商品报价
     * 更新报价时间和最后修改时间
     * @param commodityses
     * @return
     */
    @RequestMapping(value = "/quoted", method = RequestMethod.POST)
    @ResponseBody
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
    @RequestMapping(value = "/quotedUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Result quotedUpdate(@RequestBody List<EnquiryCommoditys> commodityses, Integer billsId) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Member member = (Member)session.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        enquiryCommoditysService.quotedUpdate(commodityses, member.getId(), billsId);
        return new Result(true).info("报价成功!");
    }

}
