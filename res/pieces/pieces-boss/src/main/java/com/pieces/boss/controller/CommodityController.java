package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Author: koabs
 * 7/12/16.
 * 商品信息
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController{

    @Autowired
    CommodityService commodityService;

    @RequiresPermissions(value = "commodity:index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Integer pageSize, Integer pageNum, CommodityVo commodityVO , ModelMap model){

        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;

        PageInfo<CommodityVo> pageInfo = commodityService.query(commodityVO,pageNum, pageSize);

        model.put("pageNum", pageNum);
        model.put("pageSize", pageSize);
        model.put("pageInfo", pageInfo);
        model.put("vo", commodityVO);
        model.put("param", Reflection.serialize(commodityVO));
        return "commodity";
    }

    @RequiresPermissions(value = "commodity:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @BizLog(type = "", desc = "新增商品信息页面")
    public String addPage() {
       return "commodity-add";
    }


    @RequiresPermissions(value = "commodity:add")
    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    @BizLog(type = "", desc = "根据已有商品新增商品信息页面")
    public String copyPage(@PathVariable("id")Integer id, ModelMap model) {
        CommodityVo vo = commodityService.findVoById(id);
        model.put("commodity", vo);
        return "commodity-copy";
    }

    @RequiresPermissions(value = "commodity:edit")
    @RequestMapping(value = "/editer/{id}", method = RequestMethod.GET)
    @BizLog(type = "", desc = "编辑商品信息页面")
    public String editerPage(@PathVariable("id")Integer id, ModelMap model) {
        CommodityVo vo = commodityService.findVoById(id);
        model.put("commodity", vo);
        return "commodity-editer";
    }

    @RequiresPermissions(value = {"commodity:add","commodity:edit"},logical = Logical.OR)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "保存商品信息")
    public Result save(@Valid Commodity commodity) throws IOException {
        commodityService.saveOrUpdate(commodity);
        return new Result(true).data(null).info("商品信息保存成功!");
    }

    @RequiresPermissions(value = "commodity:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "根据ID删除商品")
    public Result delete(@PathVariable("id") Integer id) {
        commodityService.deleteById(id);
        return new Result(true).info("新增商品信息成功!");
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "查询商品信息列表")
    public Result queryById(CommodityVo commodity, Integer pageNum, Integer pageSize) {
        return new Result(true).data(commodityService.query(commodity,pageNum, pageSize));
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "根据ID查询商品信息")
    public Result query(@PathVariable("id")Integer id) {
        return new Result(true).data(commodityService.findById(id));
    }

}
