package com.pieces.boss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * BOSS系统首页和登录
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
    	return "redirect:/user/index";
    }


    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response){
        return "login";
    }


    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public void loginSubmit(HttpServletRequest request,
                        HttpServletResponse response){


    }



}
