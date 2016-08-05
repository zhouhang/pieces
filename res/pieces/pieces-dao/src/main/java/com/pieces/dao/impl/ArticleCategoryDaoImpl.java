package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ArticleCategoryDao;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.vo.ArticleCategoryVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ArticleCategoryDaoImpl extends BaseDaoImpl implements ArticleCategoryDao {

    @Override
    public ArticleCategory findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.ArticleCategoryMapper.findById", id);
    }


    @Override
    public List<ArticleCategory> findAll() {
        return getSqlSession().selectList("com.pieces.dao.ArticleCategoryMapper.findAll");
    }

    @Override
    public PageInfo<ArticleCategory> find(int pageNum, int pageSize) {
        List<ArticleCategory> list = getSqlSession().selectList("com.pieces.dao.ArticleCategoryMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.ArticleCategoryMapper.deleteById", id);
    }

    @Override
    public int create(ArticleCategory articleCategory) {
        return getSqlSession().insert("com.pieces.dao.ArticleCategoryMapper.create", articleCategory);
    }

    @Override
    public int update(ArticleCategory articleCategory) {
        return getSqlSession().update("com.pieces.dao.ArticleCategoryMapper.update", articleCategory);
    }

    @Override
    public List<ArticleCategoryVo> findVoByModelId(Integer modelId) {
        return getSqlSession().selectList("com.pieces.dao.ArticleCategoryMapper.findVoByModelId", modelId);
    }

    @Override
    public PageInfo<ArticleCategory> findByParam(ArticleCategory category, Integer pageNum, Integer pageSize) {
        List<ArticleCategory> list = getSqlSession().selectList("com.pieces.dao.ArticleCategoryMapper.findByParam", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }
}
