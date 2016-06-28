package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.jointown.zy.common.model.BossRolePermission;


/**
 * 后台角色权限Dao
 * @author biran
 * 2014-12-05
 */
public interface BossRolePermissionDao {
	/**
	 * 新增角色权限
	 * @author biran
	 * @param bossRolePermission
	 */
	public void addBossRolePermission(BossRolePermission bossRolePermission);
	
	/**
	 * 	 删除该用户下，该0级权限下，所有下级权限（1,2,3级）
	 * @author biran
	 * @param roleId
	 * @param parentId
	 */
	public void deleBossRolePermissionByLevel0Id(Integer roleId,Integer parentId);
	
	/**
	 * 	根据主键，权限ID,角色ID查找角色权限
	 * @author biran
	 * @param roleId
	 * @param parentId
	 */
	public BossRolePermission findBossRolePermissionByPK(Integer permissionId,Integer roleId);
	
	/**
	 * 	根据主键，权限ID,角色ID更新角色权限
	 * @author biran
	 * @param BossRolePermission
	 */
	public void UpdateBossRolePermissionByPK(BossRolePermission rolePermission) ;
	
	/**
	 * 	第2级菜单，找到第1级目录,参数：0级系统ID,角色ID
	 * @author biran
	 * @param roleId
	 * @param parentId
	 */
	public List<BossRolePermission> findBossRolePermissionLevel(Integer permissionId,Integer roleId,Integer sonType);
	
}
