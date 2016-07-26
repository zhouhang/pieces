package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.vo.EnquiryBillsVO;
import com.pieces.service.EnquiryBillsService;
import com.pieces.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
