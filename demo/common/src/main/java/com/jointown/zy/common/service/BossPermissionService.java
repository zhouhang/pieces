package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.Page;

/**
 * 后台角色Service
 * @author biran
 * 2014-11-26
 */
public interface BossPermissionService {

	/**
	 * 查询所有权限
	 * @author lichenxiao
	 * @param params 
	 * @param permissionSearchDto
	 * @return
	 */
	public List<BossPermission> findAllBossPermission(Page page);

	/**
	 * 通过父权限ID号查询到父权限名称
	 * @author lichenxiao
	 * @param parentId
	 * @return
	 */
	public String findParentNameByParentId(Integer parentId);
	
	/**
	 * 通过父权限名称查询到父权限ID号
	 * @author lichenxiao
	 * @param parentName
	 * @return
	 */
	public List<BossPermission> findBossPermissionByParentName(String parentName);
	
	public BossPermission findBossPermissionById(int Id);

	/**
	 * 修改权限
	 * @author lichenxiao
	 * @param bossPermission
	 */
	public String updateBossPermission(BossPermission bossPermission,String path);
	
	public void updateBossPermissionTypeAndPath(BossPermission bossPermission);

	/**
	 * 查找子节点个数
	 * @author lichenxiao
	 * @param parseInt
	 * @return
	 */
	public int findBossPermissionByNoteNumber(int permissionId);

	/**
	 * 在权限与角色中间表中查找权限是否已被角色使用
	 * @author lichenxiao
	 * @param parseInt
	 * @return
	 */
	public int findBossPermissionByRoleNumber(int permissionId);

	/**
	 * 删除权限
	 * @author lichenxiao
	 * @param parseInt
	 * @return
	 */
	public int bossPermissionByUpdateStatus(int permissionId);
	
	
	
	/**
	 *  通过后台人员ID 查找对应权限（所有）
	 * @author biran 
	 * @return
	 */
	public List<BossPermission> findBossPermissionByUserCode(String UserCode) ;
	
	/**
	 *  通过后台人员ID 查找对应权限（所有）BossRealm 使用
	 * @author biran 
	 * @return
	 */
	public List<BossPermission> findBossPermissionByUserCode4BossRealm(String UserCode) ;
	
	
	/**
	 *  查找所有0级菜单，分配权限使用
	 * @author biran 
	 * @return
	 */
	
	public List<BossPermission> findAllLevel0BossPermission();
	/**
	 *  通过0级父ID，查找下面几级菜单权限，包括1级目录，2级菜单，3级按钮
	 * @author biran 
	 * @return
	 */
	 public List<BossPermission> findBossPermissionByLevel0Id(Integer parentId,Integer roleId);
	 
	 
	 /**
	  *  通过后台用户编码查询其所有权限信息
	  * @param userCode
	  * @return
	  */
	 public List<BossPermission> findAllBossPermissionsByUserCode(String userCode);
	 
	 /**
	  *  /通过用户名，查找对应的3级按钮权限，格式：菜单名-按钮名
	  * @param userCode
	  * @return
	  */
	 public List<BossPermission> findBossPermissionLeve3ByUserCode(String userCode) ;
	 /**
	  *  通过资源URL查询Permission
	  * @param userCode
	  * @return
	  */
	 public BossPermission findBossPermissionByResource(String Resource) ;

	public BossPermission findBossPermissionByPermissionCode(String permissionCode);

	public List<BossPermission> findBossPermissionByPermissionCodeForUpdate(
			String permissionCode,String permissionId);
	/**
	 * 无条件查询所有权限
	 * @return
	 */
	public List<BossPermission> findAllBossPermission();

	/**
	 * 添加权限
	 * @author lichenxiao
	 * @param bossPermission
	 */
	public String addBossPermission(BossPermission bossPermission);

	/**
	 * 得到最大ID
	 * @author lichenxiao
	 * @param
	 * @return
	 */
	public Integer getIdMax();
	/**
	 * 
	 * @author biran
	 * @param
	 * @return
	 */

	public String updateBossPermissionUnusual(String sql);
	 
}
