package com.pieces.biz.controller;

import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.service.CommoditySearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/7/18.
 */
@Controller
@RequestMapping(value = "/pro")
public class ProController extends BaseController{

    @Autowired
    private CommoditySearchService commoditySearchService;


    @RequestMapping(value = "search")
    public String proResult(HttpServletRequest request,
                            HttpServletResponse response,
                            Integer pageNum,
                            Integer pageSize,
                            ModelMap model,
                            String keyword){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?2:pageSize;
        if(StringUtils.isNotBlank(keyword)){
            Page<CommodityDoc> commodityDocPage= commoditySearchService.findByNameOrCategoryName(pageNum,pageSize,keyword);
            model.put("commodityDocPage",commodityDocPage);
            model.put("keyword",keyword);
        }
        return "product_search_result";
    }




}
