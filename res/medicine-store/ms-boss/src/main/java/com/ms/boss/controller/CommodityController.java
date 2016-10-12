package com.ms.boss.controller;

import com.ms.service.CommodityService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("commodity/")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    public Result index() {
        return Result.success().data(commodityService.findAll());
    }
}
