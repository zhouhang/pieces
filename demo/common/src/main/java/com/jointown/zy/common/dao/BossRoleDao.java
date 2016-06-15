package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossRoleVO;


/**
 * 后台角色Dao
 * @author biran
 * 2014-11-27
 */
public interface BossRoleDao {
	/**
	 * 新增角色
	 * @author biran
	 * @param bossRole
	 */
	public void addBossRole(BossRole bossRole);
	
	/**
	 * 更新角色，通过角色名称，用于更新已删除角色
	 * @author biran
	 * @param roleName
	 */
	public void UpdateBossRoleByName(BossRole bossRole);

	/**
	 * 更新角色，通过角色ID
	 * @author biran
	 * @param roleName
	 */
	public void UpdateBossRole(BossRole bossRole);
	/**
	 * 删除角色，通过角色ID
	 * @author biran
	 * @param roleName
	 */
	public void deleteBossRoleById(BossRole bossRole);
	
	
	/**
	 * 查找所有角色
	 * @author biran
	 * @return
	 */
	public List<BossRole> findBossRoles(BossRoleVO bossRole) ;
	
	/**
	 * 查找所有角色，没有参数
	 * @author biran
	 * @return
	 */
	public List<BossRole> findBossRoles() ;
	

	/**
	 * 查找所有角色，参数为page
	 * @author biran
	 * @return
	 */
	public List<BossRole> findBossRoles(Page page) ;
	
	/**
	 * 通过主键找角色
	 * @author biran
	 * @return
	 */
	public BossRole findBossRoleByid(Integer id);
	/**
	 * 通过用户名找角色
	 * @author biran
	 * @return
	 */
	public  List<BossRole>  findBossRoleByUserCode(String UserCode);
	/**
	 * 通过角色名称找角色
	 * @author biran
	 * @return
	 */
	public BossRole  findBossRoleByRoleName(String RoleName);
	/**
	 * 通过角色ID找角色
	 * @author biran
	 * @return
	 */
	public BossRole  findBossRoleByRoleId(String roleId);


}
