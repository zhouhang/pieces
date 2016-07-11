package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Role;
import com.pieces.dao.vo.RoleVo;

public interface RoleDao extends ICommonDao<Role>{

    PageInfo<Role> findByCondition(RoleVo roleVo, Integer pageNum, Integer pageSize);

}
