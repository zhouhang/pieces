package com.pieces.dao;

import com.pieces.dao.model.RoleResources;

import java.util.List;

public interface RoleResourcesDao extends ICommonDao<RoleResources>{

    int deleteByRoleId(int roleId);

    List<RoleResources> findByRole(int roleId);
	
}
