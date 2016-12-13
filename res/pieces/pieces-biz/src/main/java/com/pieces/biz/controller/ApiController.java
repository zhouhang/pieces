package com.pieces.biz.controller;

import com.pieces.service.UserCertificationService;
import com.pieces.service.UserQualificationService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by xiao on 2016/12/13.
 * ERP对接接口
 */

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserCertificationService userCertificationService;

    @Autowired
    private UserQualificationService userQualificationService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/company/info")
    @ResponseBody
    public Result companyInfo(String timestamp,String auth){

        /**
         *
         */

        HashMap<String,Object> ret=new HashMap<String,Object>();

        ret.put("total",10);

        return new Result("200").data(ret);

    }





}
