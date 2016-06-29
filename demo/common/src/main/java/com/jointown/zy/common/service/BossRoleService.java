package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossRoleVO;


/**
 * 后台角色Service
 * @author biran
 * 2014-11-27
 */
public interface BossRoleService {
	/**
	 *  查找所有角色,参数为page
	 * @author biran 
	 * @return
	 */
	public List<BossRole> findBossRoles(Page page);
	/**
	 *  查找所有角色
	 * @author biran 
	 * @return
	 */
	public List<BossRole> findBossRoles(BossRoleVO bossRole) ;

	/**
	 *  查找所有角色，无can
	 * @author biran 
	 * @return
	 */
	public List<BossRole> findBossRoles() ;
	/**
	 *  查找用户对应角色（可多个）
	 * @author biran 
	 * @return
	 */
	public List<BossRole> findBossRoleByUserCode(String UserCode) ;
	/**
	 * 添加BossRole
	 * @author biran
	 * @param bossUser
	 */
	public void addBossRole(String newRoleName);
	
	/**
	 * 更新BossRole
	 * @author biran
	 * @param bossUser
	 */
	public void deleteBossRoleById(Integer roleId);
	
	/**
	 * 删除BossRole
	 * @author biran
	 * @param bossUser
	 */
	public void updateBossRole(Integer roleId,String roleName);
	
	/**
	 * 验证角色名称是否存在
	 * @author biran
	 * @return
	 */
	public String validateRoleName(String roleName);
	
	/**
	 * 验证角色状态
	 * @author biran
	 * @return
	 */
	public String validateRoleStatus(String roleId);
	
	/**
	 * 删除时验证角色
	 * @author biran
	 * @return
	 */
	public String validateRoleDele(String roleId);
}
