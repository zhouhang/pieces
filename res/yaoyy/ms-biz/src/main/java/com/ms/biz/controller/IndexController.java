package com.ms.biz.controller;

import com.ms.dao.vo.AdVo;
import com.ms.service.AdService;
import com.ms.service.CommoditySearchService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping()
public class IndexController {
    @Autowired
    CommoditySearchService commoditySearchService;

    @Autowired
    private AdService adService;
    /**
     * 首页广告
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        // 首页banner 广告
        // 专场广告
        List<AdVo> banners = adService.findByType(1);
        List<AdVo> specials = adService.findByType(2);
        model.put("banners", banners);
        model.put("specials",specials);
        return "index";
    }

    @RequestMapping(value = "/create/commodity/index",method = RequestMethod.GET)
    @ResponseBody
    public Result createIndex(){
        commoditySearchService.createAllCommodityDoc();
        return Result.success("索引创建成功");
    }
}
