package com.pieces.biz.controller;

import com.pieces.dao.vo.ArticleCategoryVo;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.service.ArticleService;
import com.pieces.service.enums.ModelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String detail(@PathVariable("id") Integer id) {
        List<ArticleCategoryVo> list = articleService.getCategoryListByModelId(ModelEnum.help.getValue());
        articleService.findArticleById(id);
        // 回填帮助中心前台
        return "";
    }
}
