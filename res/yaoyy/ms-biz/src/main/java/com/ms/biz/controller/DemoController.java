package com.ms.biz.controller;

import com.ms.service.CommoditySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.tools.entity.Result;


/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("demo/")
public class DemoController {

    @Autowired
    CommoditySearchService commoditySearchService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("user", "user");
        return "user_list";
    }

    @RequestMapping("/create/commodity/all")
    public Result searchIndexCreate(HttpServletRequest request,
                                  HttpServletResponse response){
        commoditySearchService.createAllCommodityDoc();
        return Result.success().data("索引创建成功");
    }
}
