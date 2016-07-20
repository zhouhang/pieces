package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Area;
import com.pieces.service.AreaService;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/7/1.
 */
@Controller
@RequestMapping(value = "demo")
public class DemoController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private CommoditySearchService commoditySearchService;

    /**
     * 测试分页
     * @param request
     * @param response
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("page")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer pageNum,
                        Integer pageSize,
                        ModelMap modelMap){

        pageNum = pageNum==null?1:pageNum;
        pageSize = pageSize==null?10:pageSize;

        PageInfo<Area> areaPage =  areaService.findByPage(1,pageNum,pageSize);

        modelMap.put("areaPage",areaPage);
        return "customers_test";
    }


    @RequestMapping("create/index/all")
    public void searchIndexCreate(HttpServletRequest request,
                                  HttpServletResponse response){
        commoditySearchService.createAllCommodityDoc();
        WebUtil.print(response,new Result(true).info("索引创建成功"));
    }




}
