package com.pieces.boss.controller;

import com.pieces.dao.model.SeoSetting;
import com.pieces.dao.vo.SeoSettingVo;
import com.pieces.service.SeoSettingService;
import com.pieces.service.constant.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xc on 2017/3/15.
 */
@Controller
@RequestMapping("/seo/setting")
public class SeoSettingController {

    @Autowired
    private SeoSettingService seoSettingService;

    @RequestMapping("/{id}")
    public String info(@PathVariable("id") Integer id,
                       ModelMap model){

        String vieWName="seo_base";
        SeoSettingVo seoSettingVo=seoSettingService.findByType(id);
        model.put("seoSettingVo",seoSettingVo);
        switch (id){
            case 1:
                vieWName = "seo_base";
                break;
            case 2:
                vieWName = "seo_commodity_list";
                break;
            case 3:
                vieWName = "seo_commodity_info";
                break;
            case 4:
                vieWName = "seo_commodity_search";
                break;
            case 5:
                vieWName = "seo_article_list";
                break;
            case 6:
                vieWName = "seo_article_info";
                break;

            default:break;
        }
        return vieWName;


    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(SeoSettingVo seoSettingVo){
        seoSettingService.save(seoSettingVo);
        return new Result(true).info("保存成功").data(seoSettingVo);
    }







}
