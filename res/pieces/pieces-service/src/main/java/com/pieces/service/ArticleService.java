package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.vo.ArticleCategoryVo;
import com.pieces.dao.vo.ArticleVo;

import java.util.List;

/**
 * Author: koabs
 * 8/3/16.
 * 文章管理
 */
public interface ArticleService extends ICommonService<Article> {


    /**
     * 保存文章分类信息
     * @param article
     */
    public void saveOrUpdateArticle(Article article, Integer memberId);

    /**
     * 根据传入的参数查询文章信息
     * @param articleVo
     * @return
     */
    public PageInfo<ArticleVo> queryArticle(ArticleVo articleVo, int pageNum, int pageSize);

    /**
     * 根据ID获取文章详细信息
     * @param id
     * @return
     */
    public Article findArticleById(Integer id);

    /**
     * 根据ID删除文章
     * @param id
     */
    public void deleteArticleById(Integer id);

    /**
     * 根据模块ID 获取模块的类别列表和下属文章的相关信息
     * @param modelId
     * @return
     */
    public List<ArticleCategoryVo> getCategoryListByModelId(Integer modelId);

    /**
     * 保存文章分类信息
     * @param category
     */
    public void saveOrUpdateCategory(ArticleCategory category, Integer memberId);

    /**
     * 删除文章分类 By ID
     * @param id
     */
    public void deleteCategory(Integer id);


    /**
     * 根据传入的参数查询文章分类信息
     * @param category
     * @return
     */
    public PageInfo<ArticleCategory> queryCategory(ArticleCategory category, int pageNum, int pageSize);


    /**
     * 根据模块id 获取模块下所有分类
     * @param modelId
     * @return
     */
    public List<ArticleCategory> getCategoryByModelId(Integer modelId, Integer status);


    public PageInfo<ArticleVo> findByModel(int model, int pageNum, int pageSize);

    /**
     * 根据id获取类别信息
     * @param id
     * @return
     */
    public ArticleCategory getCategoryById(Integer id);

}
