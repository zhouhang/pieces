package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.vo.ArticleCategoryVo;

import java.util.List;

public interface ArticleCategoryDao extends ICommonDao<ArticleCategory>{

    public List<ArticleCategoryVo> findVoByModelId(Integer modelId);

    public PageInfo<ArticleCategory> findByParam(ArticleCategory category, Integer pageNum, Integer pageSize);

}
