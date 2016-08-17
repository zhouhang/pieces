package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.vo.AdVo;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.dao.vo.HomeCategoryVo;
import com.pieces.service.AdService;
import com.pieces.service.ArticleService;
import com.pieces.service.FreeMarkForHtmlService;
import com.pieces.service.HomeWeightService;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.enums.WeightEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/8/16.
 */
@Service
public class CreateHtmlService {

    @Autowired
    private FreeMarkForHtmlService freeMarkForHtmlService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private HomeWeightService homeWeightService;
    @Autowired
    private AdService adService;

    public void createHomePage(FreeMarkerConfigurer freeMarkerConfigurer){

        Map<String,Object> model = new HashMap<>();

        //新闻
        PageInfo<ArticleVo> page = articleService.findByModel(2,1,5);
        model.put("articles",page.getList());
        //商品和分类
        List<CommodityVo> commodityVos = homeWeightService.getHomeCommoditys(WeightEnum.COMMODITY.name());
        List<HomeCategoryVo> categoryList = homeWeightService.getHomeCategorys(WeightEnum.CATEGORY.name());

        model.put("commodityList",commodityVos);
        model.put("categoryList",categoryList);
        //广告
        List<AdVo> adBannerList =adService.findByType(CodeEnum.AD_BANNER.getId());
        List<AdVo> adBarList =adService.findByType(CodeEnum.AD_SHOWCASE_BAR.getId());
        model.put(CodeEnum.AD_BANNER.name(),adBannerList);
        model.put(CodeEnum.AD_SHOWCASE_BAR.name(),adBarList);

        //标志首页
        model.put("CURRENT_PAGE","home");
        try {
            freeMarkForHtmlService.geneHtmlFile(freeMarkerConfigurer,"/","home.ftl","E:\\temp","index.html",model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
