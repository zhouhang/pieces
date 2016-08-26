package com.pieces.boss.controller;


import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.AreaService;
import com.pieces.service.CategoryService;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.utils.CommodityExcelParse;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

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

    @RequestMapping("install/commodity")
    @ResponseBody
    public Result installCommodity()throws Exception{
        File file = new File("G:\\Downloads\\产品规格等级手册20160524.xls");
        List<CommodityVo> commodityVos =   CommodityExcelParse.parseEnquiryXLS(new FileInputStream(file));
        for(CommodityVo commodityVo : commodityVos){

            try {
                Commodity commodity = new Commodity();
                Integer spec = CodeEnum.findByTypeAndName(CodeEnum.Type.SPEC,commodityVo.getSpecName());
                Integer level = CodeEnum.findByTypeAndName(CodeEnum.Type.LEVEL,commodityVo.getLevel()+"");
                commodity.setSpec(spec);
                commodity.setLevel(level);
                //类别名称
                String categoryName = commodityVo.getCategoryName();
                if(StringUtils.isBlank(categoryName) ){
                    break;
                }
                //真实类别
                Category category = categoryService.findByNameAndLevel(categoryName,1);
                if(category==null){
                    break;
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
                commodity.setFactory(commodityVo.getFactoryStr());
                commodity.setExecutiveStandard(commodityVo.getExecutiveStandard());
                commodityService.create(commodity);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("info", GsonUtil.toJson(commodityVo));
            }

        }

        return new Result(true).info("数据导入完成!");
    }

}
