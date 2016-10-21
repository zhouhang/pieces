package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Role;
import com.ms.dao.vo.RoleVo;

import java.util.List;

@AutoMapper
public interface RoleDao extends ICommonDao<Role>{

    List<Role> findByCondition(RoleVo roleVo);


}
