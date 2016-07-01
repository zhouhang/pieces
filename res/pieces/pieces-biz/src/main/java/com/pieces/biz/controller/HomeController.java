package com.pieces.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理主页业务
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        return "home";
    }



}
