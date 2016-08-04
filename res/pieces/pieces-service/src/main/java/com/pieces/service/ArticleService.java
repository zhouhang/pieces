package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.ArticleCategory;

/**
 * Author: koabs
 * 8/3/16.
 * 文章管理
 */
public interface ArticleService extends ICommonService<Area> {



    /**
     * 保存文章分类信息
     * @param category
     */
    public void saveOrUpdateCategory(ArticleCategory category);

    /**
     * 删除文章分类 By ID
     * @param id
     */
    public void deleteCategory(Integer id);


    /**
     * 根据传入的参数查询文章分类信息
     * @param commodity
     * @return
     */
    public PageInfo<ArticleCategory> queryCategory(ArticleCategory commodity, int pageNum, int pageSize);
}
