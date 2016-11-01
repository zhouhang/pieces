package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Article;
import com.ms.dao.vo.ArticleVo;

public interface ArticleService extends ICommonService<Article>{

    public PageInfo<ArticleVo> findByParams(ArticleVo articleVo,Integer pageNum,Integer pageSize);

    public void changeStatus(Integer id, Integer status);

    public void save(Article article);
}
