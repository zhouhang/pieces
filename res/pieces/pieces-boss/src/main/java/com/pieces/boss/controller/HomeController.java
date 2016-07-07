package com.pieces.boss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
    	return "redirect:/user/index";
    }
}
