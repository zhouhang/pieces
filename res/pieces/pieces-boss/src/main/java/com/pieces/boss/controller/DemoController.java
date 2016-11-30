package com.pieces.boss.controller;


import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.AreaService;
import com.pieces.service.CategoryService;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.utils.CommodityExcelParse;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/7/1.
 */
@Controller
@RequestMapping(value = "demo")
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private AreaService areaService;

    @Autowired
    private CommoditySearchService commoditySearchService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommodityService commodityService;

    @RequestMapping("create/index/all")
    public void searchIndexCreate(HttpServletRequest request,
                                  HttpServletResponse response){
        commoditySearchService.createAllCommodityDoc();
        WebUtil.print(response,new Result(true).info("索引创建成功"));
    }
    @RequestMapping("/pinyin")
    public void pinyinSearch(HttpServletRequest request,
                                  HttpServletResponse response,
                             String pinyin){
        Page<CommodityDoc> result=commoditySearchService.findByPinyinSearch(1,10,pinyin);
        WebUtil.print(response,new Result(true).data(result.getContent()));
    }


    public Result installCommodity()throws Exception{
        File file = new File("G:\\Downloads\\产品规格等级手册20160524.xls");
        List<CommodityVo> commodityVos =   CommodityExcelParse.parseEnquiryXLS(new FileInputStream(file));
        for(CommodityVo commodityVo : commodityVos){

            try {
                Commodity commodity = new Commodity();
                //类别名称
                String categoryName = commodityVo.getCategoryName();
                if(StringUtils.isBlank(categoryName) ){
                    continue;
                }
                //真实类别
                Category category = categoryService.findByNameAndLevel(categoryName,1);
                if(category==null){
                    continue;
                }
                //品种名称
                String breedName = commodityVo.getBreedName();
                Category breed = categoryService.findByNameAndLevel(breedName,2);
                if(breed==null){
                    BreedVo bvo = new BreedVo();
                    bvo.setName(breedName);
                    bvo.setClassifyId(category.getId());
                    bvo.setAliases(breedName);
                    categoryService.addBreed(bvo);
                    commodity.setCategoryId(bvo.getId());
                }else{
                    commodity.setCategoryId(breed.getId());
                }
                commodity.setName(commodityVo.getName());
                commodity.setExterior(commodityVo.getExterior());
                commodity.setExecutiveStandard(commodityVo.getExecutiveStandard());
                commodity.setCreateTime(new Date());
                commodity.setStatus(1);
                commodityService.create(commodity);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("info", GsonUtil.toJson(commodityVo));
            }

        }

        return new Result(true).info("数据导入完成!");
    }

}
