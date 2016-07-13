package com.pieces.boss.controller;

import com.pieces.dao.model.Commodity;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.log.annotation.BizLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 7/12/16.
 * 商品信息
 */
@Controller(value = "/commodity")
public class CommodityController extends BaseController{

    @Autowired
    CommodityService commodityService;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "保存商品信息")
    public String save(Commodity commodity) {

        return "sss";
    }


}
