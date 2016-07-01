package com.pieces.boss.controller;

import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pieces.dao.model.User;
import com.pieces.service.enums.RedisEnum;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
    	return "redirect:/menber/get/user";
    }
}
