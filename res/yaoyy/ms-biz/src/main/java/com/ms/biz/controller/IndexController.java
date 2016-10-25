package com.ms.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping()
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }
}
