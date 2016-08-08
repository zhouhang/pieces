package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.service.ArticleService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.ModelEnum;
import com.pieces.service.enums.StatusEnum;
import com.pieces.tools.utils.Reflection;
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
 * 8/5/16.
 * 帮助中心
 */
@Controller
@RequestMapping("cms/")
public class CMSController {

    @Autowired
    ArticleService articleService;

    /**
     * 文章列表页面
     * model 1=help,2=news
     * @return
     */
    @RequestMapping(value = "article/index", method = RequestMethod.GET)
    public String index(ArticleVo articleVo, Integer pageSize, Integer pageNum, ModelMap model){

        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<ArticleVo> pageInfo = articleService.queryArticle(articleVo, pageNum, pageSize);

        List<ArticleCategory> categories = articleService.getCategoryByModelId(articleVo.getModel(), StatusEnum.enable.getValue());
        model.put("categorys",categories);
        model.put("pageNum", pageNum);
        model.put("pageSize", pageSize);
        model.put("pageInfo", pageInfo);
        model.put("vo", articleVo);
        model.put("param", Reflection.serialize(articleVo));

        return "help";
    }

    /**
     * 文章详情页
     * @return
     */
    @RequestMapping(value = "article/add", method = RequestMethod.GET)
    public String add(Integer model, ModelMap modelMap){
        List<ArticleCategory> categories = articleService.getCategoryByModelId(model, StatusEnum.enable.getValue());
        modelMap.put("categorys",categories);

        return "help-detail";
    }


    @RequestMapping(value = "article/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id){
        return "help-detail";
    }



    @RequestMapping(value = "article/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete (@PathVariable("id") Integer id){
        return "help-detail";
    }

    @RequestMapping(value = "article/save", method = RequestMethod.GET)
    @ResponseBody
    public String save (Article article){
        return "help-detail";
    }

    /**
     * 类别列表页面
     * model 1=help,2=news
     * @return
     */
    @RequestMapping(value = "category/index", method = RequestMethod.GET)
    public String categoryIndex(Integer  model){

        return "help-category";
    }


    /**
     * 类别详情页
     * @return
     */
    @RequestMapping(value = "category/detail", method = RequestMethod.GET)
    public String categoryDetail(Integer model){
        return "help-category-detail";
    }


//    public Result save


}
