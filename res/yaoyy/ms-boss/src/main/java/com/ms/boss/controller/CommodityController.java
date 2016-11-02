package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Admin;
import com.ms.dao.model.Category;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CategoryVo;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.CommodityService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("commodity/")
public class CommodityController extends BaseController{

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
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        // 获取单位信息
        return "commodity_add";
    }

    /**
     * 商品详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        // 获取单位信息
        CommodityVo vo = commodityService.findById(id);
        model.put("commodity", vo);
        return "commodity_editor";
    }


    /**
     * 保存
     * @param commodity
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody CommodityVo commodity) {
        commodityService.save(commodity);
        return Result.success("保存成功!");
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "detete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id) {
        commodityService.deleteById(id);
        return Result.success("删除成功!");
    }

    /**
     * 按名称模糊查询商品
     * @param commodityVo
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Result search(CommodityVo commodityVo) {
        List<Commodity> commodityList=commodityService.searchComodity(commodityVo);
        return  Result.success().data(commodityList);
    }



}
