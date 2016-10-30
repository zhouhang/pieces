package com.ms.biz.controller;

import com.github.pagehelper.PageInfo;
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
 * 10/25/16.
 */
@Controller
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    // 需添加方法传进来的是品种id 根据品种id 来获取商品id 重定向到
    // 指定的商品. 或许定向到商品不存在页面

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String commodityDetail(@PathVariable("id") Integer id,ModelMap model) {

        CommodityVo commodityVo=commodityService.findById(id);
        if(commodityVo.getMark()==1){
            commodityVo.setPrice(commodityVo.getGradient().get(0).getPrice());
        }

        List<CommodityVo> commodityVoList=commodityService.findByCategoryId(commodityVo.getCategoryId());
        model.put("commodityVo",commodityVo);
        model.put("commodityVoList",commodityVoList);

        return "commodity_detail";
    }
}
