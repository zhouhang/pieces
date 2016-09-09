package com.pieces.biz.controller;

import com.pieces.service.constant.bean.Result;
import com.pieces.service.impl.SerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangbin on 2016/9/8.
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private SerialNumberService serialNumberService;




    @RequestMapping(value = "order/code")
    @ResponseBody
    public  Result test() throws Exception{
        List<String> arrayList = new ArrayList<>();


        Set<String> set = new HashSet<>();
        String code = serialNumberService.generateOrderCode();
        System.out.println("code:"+code);
        System.out.println("list:"+arrayList.size());
        System.out.println("set:"+set.size());
        return new Result(true);
    }




}
