package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ArticleDao;
import com.pieces.dao.model.Article;
import com.pieces.dao.vo.ArticleVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ArticleDaoImpl extends BaseDaoImpl implements ArticleDao {

    @Override
    public Article findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.ArticleMapper.findById", id);
    }


    @Override
    public List<Article> findAll() {
        return getSqlSession().selectList("com.pieces.dao.ArticleMapper.findAll");
    }

    @Override
    public PageInfo<Article> find(int pageNum, int pageSize) {
        List<Article> list = getSqlSession().selectList("com.pieces.dao.ArticleMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.ArticleMapper.deleteById", id);
    }

    @Override
    public int create(Article article) {
        return getSqlSession().insert("com.pieces.dao.ArticleMapper.create", article);
    }

    @Override
    public int update(Article article) {
        return getSqlSession().update("com.pieces.dao.ArticleMapper.update", article);
    }

    @Override
    public PageInfo<ArticleVo> findByParam(ArticleVo articleVo, Integer pageNum, Integer pageSize) {
        List<ArticleVo> list = getSqlSession().selectList("com.pieces.dao.ArticleMapper.findByParam", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<ArticleVo> findByParam(ArticleVo articleVo) {
        return getSqlSession().selectList("com.pieces.dao.ArticleMapper.findByParam");
    }
}
