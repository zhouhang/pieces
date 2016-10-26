package com.ms.biz.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.CommodityService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 10/25/16.
 */
@Controller
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;
}
