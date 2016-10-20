package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Admin;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.CommodityService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("commodity/")
public class CommodityController {

    // CRUD

    @Autowired
    CommodityService commodityService;

    /**
     * 商品列表页面
     * @param commodity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(CommodityVo commodity, Integer pageNum, Integer pageSize, ModelMap model) {
        PageInfo<CommodityVo> pageInfo = commodityService.findByParams(commodity, pageNum, pageSize);
        model.put("pageInfo", pageInfo);
        // 参数
        return "commodity_list";
    }

    /**
     * 添加商品页面
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "commodity_detail";
    }

    /**
     * 商品详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        return "commodity_detail";
    }


    /**
     * 保存
     * @param commodity
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Commodity commodity) {
        return null;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "detete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id) {
        return null;
    }


}
