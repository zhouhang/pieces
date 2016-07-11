package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.RoleResourcesDao;
import com.pieces.dao.model.RoleResources;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RoleResourcesDaoImpl extends BaseDaoImpl implements RoleResourcesDao {

    @Override
    public RoleResources findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.RoleResourcesMapper.findById", id);
    }


    @Override
    public List<RoleResources> findAll() {
        return getSqlSession().selectList("com.pieces.dao.RoleResourcesMapper.findAll");
    }

    @Override
    public PageInfo<RoleResources> find(int pageNum, int pageSize) {
        List<RoleResources> list = getSqlSession().selectList("com.pieces.dao.RoleResourcesMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.RoleResourcesMapper.deleteById", id);
    }

    @Override
    public int create(RoleResources roleResources) {
        return getSqlSession().insert("com.pieces.dao.RoleResourcesMapper.create", roleResources);
    }

    @Override
    public int update(RoleResources roleResources) {
        return getSqlSession().update("com.pieces.dao.RoleResourcesMapper.update", roleResources);
    }

    @Override
    public int deleteByRoleId(int roleId) {
        return getSqlSession().delete("com.pieces.dao.RoleResourcesMapper.deleteByRoleId", roleId);
    }

    @Override
    public List<RoleResources> findByRole(int roleId) {
        return getSqlSession().selectList("com.pieces.dao.RoleResourcesMapper.findByRoleId", roleId);
    }
}
