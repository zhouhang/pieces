package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
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
import com.pieces.service.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 8/5/16.
 */
@Service
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
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleCategory> list =articleCategoryDao.findByParam(category);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    @Transactional
    public void saveOrUpdateArticle(Article article, Integer memberId) {
        if (article.getId() == null) {
            article.setCreateUser(memberId);
            article.setCreateTime(new Date());
            articleDao.create(article);
        } else {
            article.setUpdateUser(memberId);
            article.setUpdateTime(new Date());
            articleDao.update(article);
        }
    }

    @Override
    public PageInfo<ArticleVo> queryArticle(ArticleVo articleVo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list = articleDao.findByParam(articleVo);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public Article findArticleById(Integer id) {
        return articleDao.findById(id);
    }

    @Override
    @Transactional
    public void deleteArticleById(Integer id) {
        articleDao.deleteById(id);
    }

    @Override
    public List<ArticleCategoryVo> getCategoryListByModelId(Integer modelId) {
        return articleCategoryDao.findVoByModelId(modelId);
    }

    @Override
    @Transactional
    public void saveOrUpdateCategory(ArticleCategory category, Integer memberId) {
        category.setCreateTime(new Date());
        category.setCreateUser(memberId);
        if (category.getStatus()== null) {
            category.setStatus(StatusEnum.enable.getValue());
        }
        if (category.getId() == null) {
            articleCategoryDao.create(category);
        } else {
            articleCategoryDao.update(category);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        articleCategoryDao.deleteById(id);
    }

    @Override
    public List<ArticleCategory> getCategoryByModelId(Integer modelId, Integer status) {
        ArticleCategory category = new ArticleCategory();
        category.setModel(modelId);
        category.setStatus(status);
        List<ArticleCategory> list = this.queryCategory(category, 1,20).getList();
        return list;
    }

    @Override
    public PageInfo<ArticleVo> findByModel(int model, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleVo> list = articleDao.findByModel(model);
        PageInfo page = new PageInfo(list);
        return page;
    }



    @Override
    public ArticleCategory getCategoryById(Integer id) {
        return articleCategoryDao.findById(id);
    }

    @Override
    public List<ArticleVo> getArticleByCategoryId(Integer categoryId) {
        ArticleVo vo = new ArticleVo();
        vo.setCategoryId(categoryId);

        return articleDao.findByParam(vo);
    }
}
