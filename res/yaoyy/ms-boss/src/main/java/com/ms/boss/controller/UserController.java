package com.ms.boss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: koabs
 * 10/13/16.
 * 用户管理列表
 */
@Controller
@RequestMapping("user/")
public class UserController {

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Integer pageNum, Integer pageSize, ModelMap model){
        return "user_list";
    }

}
