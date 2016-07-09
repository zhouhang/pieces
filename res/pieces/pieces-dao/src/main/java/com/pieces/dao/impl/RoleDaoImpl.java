package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.RoleDao;
import com.pieces.dao.model.Role;
import com.pieces.dao.vo.RoleVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

    @Override
    public Role findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.RoleMapper.findById", id);
    }


    @Override
    public List<Role> findAll() {
        return getSqlSession().selectList("com.pieces.dao.RoleMapper.findAll");
    }

    @Override
    public PageInfo<Role> find(int pageNum, int pageSize) {
        List<Role> list = getSqlSession().selectList("com.pieces.dao.RoleMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.RoleMapper.deleteById", id);
    }

    @Override
    public int create(Role role) {
        return getSqlSession().insert("com.pieces.dao.RoleMapper.create", role);
    }

    @Override
    public int update(Role role) {
        return getSqlSession().update("com.pieces.dao.RoleMapper.update", role);
    }

    @Override
    public PageInfo<Role> findByCondition(RoleVo roleVo, Integer pageNum, Integer pageSize) {
        List<Role> list = getSqlSession().selectList("com.pieces.dao.RoleMapper.findByCondition", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }
}
