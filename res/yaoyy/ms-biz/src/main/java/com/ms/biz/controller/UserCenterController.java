package com.ms.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by burgl on 2016/10/30.
 */
@Controller
@RequestMapping("center/")
public class UserCenterController {


    @RequestMapping("index")
    public String index(){

        return "center_index";
    }


}
