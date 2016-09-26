package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Article;
import com.pieces.dao.vo.ArticleVo;

import java.util.List;
@AutoMapper
public interface ArticleDao extends ICommonDao<Article>{


    public List<ArticleVo> findByParam(ArticleVo articleVo);

    public List<ArticleVo>  findByModel(Integer model);

}
