package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Ad;
import com.ms.dao.model.AdType;
import com.ms.dao.vo.AdTypeVo;
import com.ms.dao.vo.AdVo;
import com.ms.service.AdService;
import com.ms.service.AdTypeService;
import com.ms.tools.entity.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 10/27/16.
 */
@Controller
@RequestMapping("ad/")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private AdTypeService adTypeService;

    @RequiresPermissions(value = "ad:list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(AdVo adVo, Integer pageNum, Integer pageSize, ModelMap model) {
        List<AdType> types = adTypeService.findAll();
        PageInfo pageInfo = adService.findByParams(adVo,pageNum,pageSize);
        model.put("types", types);
        model.put("pageInfo", pageInfo);
        return "ad_list";
    }

    @RequiresPermissions(value = "ad:edit")
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result detail(@PathVariable("id") Integer id) {
        return Result.success().data(adService.findById(id));
    }

    @RequiresPermissions(value = "ad:edit")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Ad ad) {
        adService.save(ad);
        return Result.success("保存成功!");
    }

    @RequiresPermissions(value = "ad:edit")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id")Integer id) {
        adService.deleteById(id);
        return Result.success("删除成功!");
    }

    @RequiresPermissions(value = "ad:edit")
    @RequestMapping(value = "enable/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result enable(@PathVariable("id")Integer id) {
        adService.changeStatus(id, 1);
        return Result.success("启用成功!");
    }

    @RequiresPermissions(value = "ad:edit")
    @RequestMapping(value = "disable/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result disable(@PathVariable("id")Integer id) {
        adService.changeStatus(id,0);
        return Result.success("禁用成功!");
    }

}
