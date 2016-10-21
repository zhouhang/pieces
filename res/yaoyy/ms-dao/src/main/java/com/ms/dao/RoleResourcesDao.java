package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.RoleResources;

import java.util.List;

@AutoMapper
public interface RoleResourcesDao extends ICommonDao<RoleResources>{

    int deleteByRoleId(int roleId);

    List<RoleResources> findByRoleId(int roleId);
	
}
