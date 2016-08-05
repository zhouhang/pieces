package com.pieces.dao.vo;

import com.pieces.dao.model.Article;
import com.pieces.dao.model.ArticleCategory;

import java.util.List;

/**
 * Author: koabs
 * 8/3/16.
 * 文章分类VO
 */
public class ArticleCategoryVo extends ArticleCategory {

    /**
     * 分类下的文章列表
     */
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
