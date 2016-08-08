package com.pieces.biz.controller;

import com.pieces.dao.model.Article;
import com.pieces.dao.vo.ArticleCategoryVo;
import com.pieces.service.ArticleService;
import com.pieces.service.enums.ModelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: koabs
 * 8/8/16.
 */
@Controller
@RequestMapping("help")
public class HelpController extends BaseController{
    @Autowired
    ArticleService articleService;

    @RequestMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        List<ArticleCategoryVo> articleCategorylist = articleService.getCategoryListByModelId(ModelEnum.help.getValue());
        id = id == 0 ? articleCategorylist.get(0).getArticles().get(0).getId() : id;
        Article article = articleService.findArticleById(id);
        model.put("articleCategorylist",articleCategorylist);
        model.put("article",article);
        // 回填帮助中心前台
        return "helper";
    }
}
