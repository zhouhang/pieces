package com.pieces.dao.vo;

import com.pieces.dao.model.Article;

/**
 * Author: koabs
 * 8/3/16.
 */
public class ArticleVo extends Article {

    private String articleCategoryName;

    public String getArticleCategoryName() {
        return articleCategoryName;
    }

    public void setArticleCategoryName(String articleCategoryName) {
        this.articleCategoryName = articleCategoryName;
    }
}
