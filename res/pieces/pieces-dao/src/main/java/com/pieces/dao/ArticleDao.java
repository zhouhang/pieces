package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Article;
import com.pieces.dao.vo.ArticleVo;

import java.util.List;

public interface ArticleDao extends ICommonDao<Article>{

    public PageInfo<ArticleVo> findByParam(ArticleVo articleVo, Integer pageNum, Integer pageSize);

    public List<ArticleVo> findByParam(ArticleVo articleVo);

    public PageInfo<ArticleVo>  findByModel(Integer model, Integer pageNum, Integer pageSize);

}
