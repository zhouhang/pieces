package com.pieces.dao;

import com.pieces.dao.model.RoleResources;

public interface RoleResourcesDao extends ICommonDao<RoleResources>{


    int deleteByRoleId(int roleId);
	
}
