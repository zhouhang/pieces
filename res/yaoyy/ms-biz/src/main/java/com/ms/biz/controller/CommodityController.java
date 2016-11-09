package com.ms.biz.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Article;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.ArticleService;
import com.ms.service.CommodityService;
import com.ms.tools.entity.Result;
import com.ms.tools.exception.NotFoundException;
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

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String commodityDetail(@PathVariable("id") Integer id,ModelMap model) {

        CommodityVo commodityVo=commodityService.findById(id);
        if(commodityVo==null){
            throw new NotFoundException("找不到该商品");
        }
        if(commodityVo.getMark()==1){
            commodityVo.setPrice(commodityVo.getGradient().get(0).getPrice());
        }

        List<CommodityVo> commodityVoList=commodityService.findByCategoryId(commodityVo.getCategoryId());
        model.put("commodityVo",commodityVo);
        model.put("commodityVoList",commodityVoList);

        // 质量保证
        Article article = articleService.findById(2);
        if (article!= null) {
            model.put("article", article.getContent());
        }

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
