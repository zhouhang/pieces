package com.jointown.zy.common.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.Page;

/**
 * 后台权限Dao
 * @author biran
 * 2014-11-26
 */
public interface BossPermissionDao {
	/**
	 * 权限列表
	 * @author lichenxiao
	 * @param params 
	 * @param bossPermission
	 */
	public List<BossPermission> listBossPermission(Page page);	
	
	/**
	 * 按父ID查询父名称
	 * @author lichenxiao
	 * @param parentId
	 * @return
	 */
	public String findParentNameByParentId(Integer parentId);
	
	/**
	 * 按父名称查询父ID
	 * @author lichenxiao
	 * @param parentName
	 * @return
	 */
	public List<BossPermission> findBossPermissionByParentName(String parentName);
	
	/**
	 * 主键找权限
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findBossPermissionByid(Integer id);
	
	/**
	 * 新增权限
	 * @author biran
	 * @param bossPermission
	 */
	public String addBossPermission(BossPermission bossPermission);

	/**
	 * 修改权限
	 * @author lichenxiao
	 * @param bossPermission
	 */
	public String updateBossPermission(BossPermission bossPermission);
	
	public void updateBossPermissionTypeAndPath(BossPermission bossPermission);
	
	/**
	 * 权限删除
	 * @author lichenxiao
	 * @param permissionId
	 * @return
	 */
	public int bossPermissionByUpdateStatus(int permissionId);	
	
	
	
	/**
	 * 通过后台人员ID 查找对应权限（所有） 
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findBossPermissionByUserCode(String UserCode);
	
	/**
	 * 通过后台人员ID 查找对应权限（所有） BossRealm使用
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findBossPermissionByUserCode4BossRealm(String UserCode);
	
	
	/**
	 * 通过后台人员ID 查找对应0级权限（系统）
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findLevel0BossPermissionByUserCode(String UserCode);
	/**
	 * 通过父ID 查找对应下级权限
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findBossPermissionByParentid(Integer id);
	/**
	 * 通过0级（系统） 查找对应的2级菜单
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findLevel2BossPermissionByParentid(String UserCode,Integer id);
	
	/**
	 * 通过0级查找对应的1级菜单
	 * @author ldp
	 * @param userCode
	 * @param id
	 * @return
	 */
	public List<BossPermission> findLevel1BossPermissionByParentid(String userCode,Integer id);
	
	/**
	 *查找所有0级权限（系统）
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findAllLevel0BossPermission();
	
	
	/**
	 *通过等级，父ID，查找权限
	 * @author biran
	 * @return
	 */
	public List<BossPermission> findBossPermissionByParentidAndType(Integer parentId,Integer type,Integer roleId);
	/**
	  *  /通过用户名，查找对应的3级按钮权限，格式：菜单名-按钮名
	  * @param userCode
	  * @return
	  */
	 public List<BossPermission> findBossPermissionLeve3ByUserCode(String userCode) ;
	 /**
	  *   通过资源URL查询Permission
	  * @param Resource
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
	public List<BossPermission> finAllBossPermission();

	/**
	 * 得到ID最大值
	 * @return
	 */
	public Integer getIdMax();

	/**
	 * 根据权限Id获得权限对象
	 * @author lichenxiao
	 * @param permissionId
	 * @return
	 */
	public BossPermission findBossPermissionById(int Id);

	/**
	 * 查找子结点个数
	 * @author lichenxiao
	 * @param permissionId
	 * @return
	 */
	public int findBossPermissionByNoteNumber(int permissionId);

	/**
	 * 通过权限与角色中间表查询该权限是否已被角色使用
	 * @author lichenxiao
	 * @param permissionId
	 * @return
	 */
	public int findBossPermissionByRoleNumber(int permissionId);



	/**
	 * 
	 * @author biran	
	 * @param 
	 * @return
	 * @throws SQLException 
	 */
	public String updateBossPermissionUnusual(String sql) throws SQLException;





}
