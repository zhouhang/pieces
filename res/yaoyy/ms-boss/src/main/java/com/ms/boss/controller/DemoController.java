package com.ms.boss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("demo/")
public class DemoController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("user", "user");
        return "commodity_list";
    }
}
