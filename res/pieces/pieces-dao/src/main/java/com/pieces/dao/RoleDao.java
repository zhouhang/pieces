package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Role;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.vo.RoleVo;

import java.util.List;

@AutoMapper
public interface RoleDao extends ICommonDao<Role>{

    List<Role> findByCondition(RoleVo roleVo);


}
