package com.ms.biz.controller;

import com.ms.service.CommoditySearchService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping()
public class IndexController {
    @Autowired
    CommoditySearchService commoditySearchService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "/create/commodity/index",method = RequestMethod.GET)
    @ResponseBody
    public Result createIndex(){
        commoditySearchService.createAllCommodityDoc();
        return Result.success("索引创建成功");
    }
}
