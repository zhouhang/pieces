package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.HomeWeightDao;
import com.pieces.dao.model.HomeWeight;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HomeWeightDaoImpl extends BaseDaoImpl implements HomeWeightDao{

    @Override
    public HomeWeight findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.HomeWeightMapper.findById", id);
    }


    @Override
    public List<HomeWeight> findAll() {
        return getSqlSession().selectList("com.pieces.dao.HomeWeightMapper.findAll");
    }

    @Override
    public PageInfo<HomeWeight> find(int pageNum, int pageSize) {
        List<HomeWeight> list = getSqlSession().selectList("com.pieces.dao.HomeWeightMapper.findAll", null,new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.HomeWeightMapper.deleteById",id);
    }

    @Override
    public int create(HomeWeight homeWeight) {
        return getSqlSession().insert("com.pieces.dao.HomeWeightMapper.create",homeWeight);
    }

    @Override
    public int update(HomeWeight homeWeight) {
        return getSqlSession().update("com.pieces.dao.HomeWeightMapper.update",homeWeight);
    }

    @Override
    public List<HomeWeight> findByParams(HomeWeight homeWeight) {
        return getSqlSession().selectList("com.pieces.dao.HomeWeightMapper.findByParams",homeWeight);
    }
}
