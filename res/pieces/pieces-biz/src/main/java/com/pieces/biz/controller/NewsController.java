package com.pieces.biz.controller;

import com.github.inspektr.audit.annotation.Audit;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Article;
import com.pieces.dao.vo.ArticleVo;
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
@RequestMapping("news")
public class NewsController extends BaseController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        ArticleVo vo = new ArticleVo();
        vo.setModel(ModelEnum.news.getValue());
        List<ArticleVo> articleList = articleService.queryArticle(vo,1,5).getList();
        id = id == 0 ? articleList.get(0).getId() : id;
        Article article = articleService.findArticleById(id);
        // 回填前台显示
        model.put("articleList",articleList);
        model.put("article",article);
        return "article";
    }



}
