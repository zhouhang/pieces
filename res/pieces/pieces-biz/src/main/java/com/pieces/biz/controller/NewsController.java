package com.pieces.biz.controller;

import com.github.inspektr.audit.annotation.Audit;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.service.ArticleService;
import com.pieces.service.enums.ModelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String detail(@PathVariable("id") Integer id) {
        ArticleVo vo = new ArticleVo();
        vo.setModel(ModelEnum.news.getValue());
        articleService.queryArticle(vo,1,5);
        articleService.findArticleById(id);
        // 回填前台显示

        return "";
    }
}
