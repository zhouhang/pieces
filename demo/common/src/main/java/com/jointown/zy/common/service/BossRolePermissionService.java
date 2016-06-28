package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BossPermission;

/**
 * 后台角色权限Service
 * @author biran
 * 2014-12-05
 */
public interface BossRolePermissionService {

	/**
	 *  更新后台角色权限
	 * @author biran 
	 * @return
	 */
	 public void saveRolePermission(Integer parentId,Integer roleId,String[] permissionIds);
	 
}
