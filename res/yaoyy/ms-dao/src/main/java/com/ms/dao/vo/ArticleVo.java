package com.ms.dao.vo;

import com.ms.dao.model.Article;

public class ArticleVo extends Article{

    /**
     * 访问文章的url
     */
    private String url;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}