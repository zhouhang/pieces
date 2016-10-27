package com.ms.biz.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.enums.CategoryEnum;
import com.ms.dao.vo.CategoryVo;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.SpecialVo;
import com.ms.service.SpecialService;
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
 * 10/26/16.
 * 专场
 */
@Controller
@RequestMapping("special")
public class SpecialController {

    @Autowired
    private SpecialService specialService;

    /**
     * 专场详情页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id, ModelMap model) {
        // 根据id查询专场信息
        return "index";
    }

    /**
     * 专场商品列表
     * @param id 专场id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(Integer id, Integer pageNum, Integer pageSize) {
        PageInfo<CommodityVo> pageInfo = specialService.findCommodity(id, pageNum, pageSize);
        // 根据专场id 来查询专场商品
        return Result.success().data(pageInfo);
    }
}
