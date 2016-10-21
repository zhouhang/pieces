package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.vo.ArticleCategoryVo;

import java.util.List;

@AutoMapper
public interface ArticleCategoryDao extends ICommonDao<ArticleCategory>{

    public List<ArticleCategoryVo> findVoByModelId(Integer modelId);

    public List<ArticleCategory> findByParam(ArticleCategory category);

}
