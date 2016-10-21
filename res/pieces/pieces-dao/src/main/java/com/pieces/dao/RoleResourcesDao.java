package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.RoleResources;

import java.util.List;

@AutoMapper
public interface RoleResourcesDao extends ICommonDao<RoleResources>{

    int deleteByRoleId(int roleId);

    List<RoleResources> findByRoleId(int roleId);
	
}
