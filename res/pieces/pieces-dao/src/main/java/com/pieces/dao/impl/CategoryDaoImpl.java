package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CategoryDao;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.CategoryVo;

import com.pieces.dao.vo.HomeCategoryVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

    @Override
    public Category findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.CategoryMapper.findById", id);
    }


    @Override
    public List<Category> findAll() {
        return getSqlSession().selectList("com.pieces.dao.CategoryMapper.findAll");
    }

    @Override
    public PageInfo<Category> find(int pageNum, int pageSize) {
        List<Category> list = getSqlSession().selectList("com.pieces.dao.CategoryMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.CategoryMapper.deleteById", id);
    }

    @Override
    public int create(Category category) {
        return getSqlSession().insert("com.pieces.dao.CategoryMapper.create", category);
    }

    @Override
    public int update(Category category) {
        return getSqlSession().update("com.pieces.dao.CategoryMapper.update", category);
    }


    @Override
    public PageInfo<Category> findClassify(CategoryVo t, int pageNum, int pageSize) {
        List<Category> list = getSqlSession().selectList("com.pieces.dao.CategoryMapper.findClassify", t, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<Category> findClassify(CategoryVo t) {
        List<Category> list = getSqlSession().selectList("com.pieces.dao.CategoryMapper.findClassify", t);
        return list;
    }


    @Override
    public PageInfo<CategoryVo> findBreed(CategoryVo vo, int pageNum, int pageSize) {
        List<CategoryVo> list = getSqlSession().selectList("com.pieces.dao.CategoryMapper.findBreed", vo, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }


    @Override
    public List<Category> findBreedByName(String breedName) {
        CategoryVo vo = new CategoryVo();
        vo.setName(breedName);
        return getSqlSession().selectList("com.pieces.dao.CategoryMapper.findBreed", vo);
    }


    @Override
    public List<CategoryVo> findBreedByParentId(Integer parentId) {
        return getSqlSession().selectList("com.pieces.dao.CategoryMapper.findBreedByParentId", parentId);
    }


    @Override
    public List<CategoryVo> findBreedNoPage(CategoryVo categoryVo) {
        return getSqlSession().selectList("com.pieces.dao.CategoryMapper.findBreedNoPage", categoryVo);
    }

    @Override
    public List<CategoryVo> findByLevelAndPinyin(CategoryVo categoryVo,Integer pageSize) {
        List<CategoryVo> categoryVoList = getSqlSession().selectList("com.pieces.dao.CategoryMapper.findByLevelAndPinyin", categoryVo,new RowBounds(1, pageSize));
        return categoryVoList;
    }

    @Override
    public List<Category> findByIds(Collection<Integer> collection) {
        return getSqlSession().selectList("com.pieces.dao.CategoryMapper.findByIds", new ArrayList<>(collection));
    }

    @Override
    public List<HomeCategoryVo> findHomeCategoryByIds(Collection<Integer> collection) {
        return getSqlSession().selectList("com.pieces.dao.CategoryMapper.findByHomeCategoryIds", new ArrayList<>(collection));
    }

}
