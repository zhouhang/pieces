package com.pieces.dao.impl;

import com.pieces.dao.AreaDao;
import com.pieces.dao.model.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
@Repository
public class AreaDaoImpl extends BaseDaoImpl implements AreaDao{

    @Override
    public List<Area> findByParent(Integer parentId) {
        return getSqlSession().selectList("com.pieces.dao.AreaMapper.selectByParent", parentId);
    }

    @Override
    public List<Area> findByLevel(Integer level) {
        return getSqlSession().selectList("com.pieces.dao.AreaMapper.selectByLevel", level);
    }

    @Override
    public Area findById(Integer id) {
        return getSqlSession().selectOne("com.pieces.dao.AreaMapper.selectById", id);
    }


}
