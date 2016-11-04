package com.ms.biz.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
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
 * 10/25/16.
 */
@Controller
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

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
    @RequestMapping(value="/getDetail",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result getDetail(@RequestBody List<Integer> list){
        StringBuilder ids = new StringBuilder();
        if (list != null && list.size() >0){
            list.forEach(sc ->{
                ids.append(sc).append(",");
            });
        }
        List<CommodityVo> commodities = commodityService.findByIds(ids.substring(0,ids.length()-1));

        return Result.success().data(commodities);
    }





}
