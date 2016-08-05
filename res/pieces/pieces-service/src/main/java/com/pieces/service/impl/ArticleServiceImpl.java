package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ArticleCategoryDao;
import com.pieces.dao.ArticleDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.vo.ArticleCategoryVo;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Author: koabs
 * 8/5/16.
 */
public class ArticleServiceImpl extends AbsCommonService<Article> implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    ArticleCategoryDao articleCategoryDao;

    @Override
    public ICommonDao<Article> getDao() {
        return articleDao;
    }

    @Override
    public PageInfo<ArticleCategory> queryCategory(ArticleCategory category, int pageNum, int pageSize) {
        return articleCategoryDao.findByParam(category,pageNum,pageSize);
    }

    @Override
    public void saveOrUpdateArticle(Article article) {
        if (article.getId() == null) {
            articleDao.create(article);
        } else {
            articleDao.update(article);
        }
    }

    @Override
    public PageInfo<ArticleVo> queryArticle(ArticleVo articleVo, int pageNum, int pageSize) {
        return articleDao.findByParam(articleVo,pageNum,pageSize);
    }

    @Override
    public Article findArticleById(Integer id) {
        return articleDao.findById(id);
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleDao.deleteById(id);
    }

    @Override
    public List<ArticleCategoryVo> getCategoryListByModelId(Integer modelId) {
        return articleCategoryDao.findVoByModelId(modelId);
    }

    @Override
    public void saveOrUpdateCategory(ArticleCategory category) {
        if (category.getId() == null) {
            articleCategoryDao.create(category);
        } else {
            articleCategoryDao.update(category);
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        articleCategoryDao.deleteById(id);
    }
}
