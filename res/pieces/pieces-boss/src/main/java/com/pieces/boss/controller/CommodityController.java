package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.UEditorResult;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.vo.CropInfo;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Integer pageSize, Integer pageNum, CommodityVO commodityVO , ModelMap model){

        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;

        PageInfo<CommodityVO> pageInfo = commodityService.query(commodityVO,pageNum, pageSize);

        model.put("pageNum", pageNum);
        model.put("pageSize", pageSize);
        model.put("pageInfo", pageInfo);
        model.put("vo", commodityVO);
        model.put("param", Reflection.serialize(commodityVO));
        return "commodity";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @BizLog(type = "", desc = "新增商品信息页面")
    public String addPage() {
       return "commodity-add";
    }


    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    @BizLog(type = "", desc = "根据已有商品新增商品信息页面")
    public String copyPage(@PathVariable("id")Integer id, ModelMap model) {
        CommodityVO vo = commodityService.findVoById(id);
        model.put("commodity", vo);
        return "commodity-copy";
    }

    @RequestMapping(value = "/editer/{id}", method = RequestMethod.GET)
    @BizLog(type = "", desc = "编辑商品信息页面")
    public String editerPage(@PathVariable("id")Integer id, ModelMap model) {
        CommodityVO vo = commodityService.findVoById(id);
        model.put("commodity", vo);
        return "commodity-editer";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = "", desc = "保存商品信息")
    public Result save(Commodity commodity) {
        commodityService.saveOrUpdate(commodity);
        return new Result(true).data(null).info("商品信息保存成功!");
    }

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
    public Result queryById(CommodityVO commodity, Integer pageNum, Integer pageSize) {
        return new Result(true).data(commodityService.query(commodity,pageNum, pageSize));
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "根据ID查询商品信息")
    public Result query(@PathVariable("id")Integer id) {
        return new Result(true).data(commodityService.findById(id));
    }

}
