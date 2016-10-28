package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Article;
import com.ms.dao.vo.ArticleVo;

import java.util.List;
@AutoMapper
public interface ArticleDao extends ICommonDao<Article>{

    public List<ArticleVo> findByParams(ArticleVo articleVo);

}
