package com.ms.boss.controller;

import com.ms.dao.model.Ad;
import com.ms.dao.vo.AdVo;
import com.ms.service.AdService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 10/27/16.
 */
@Controller
@RequestMapping("ad/")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(AdVo adVo, Integer pageNum, Integer pageSize, ModelMap model) {

        // 获取广告类型
        return "ad_list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result detail(@PathVariable("id") Integer id) {

        // 参数
        return null;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Ad ad) {

        // 参数
        return null;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id")Integer id) {

        // 参数
        return null;
    }

    @RequestMapping(value = "enable/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result enable(@PathVariable("id")Integer id) {

        // 参数
        return null;
    }

    @RequestMapping(value = "disable/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result disable(@PathVariable("id")Integer id) {

        // 参数
        return null;
    }

}
