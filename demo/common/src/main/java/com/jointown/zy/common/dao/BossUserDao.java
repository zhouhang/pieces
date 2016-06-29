package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BossUserRole;
import com.jointown.zy.common.model.Page;

public interface BossUserDao {
	/**
	 * 添加BossUser
	 * @author zhouji
	 * @param bossUser
	 */
	public void addBossUser(BossUser bossUser);
	/**
	 * 删除BossUser(修改状态)
	 * @author zhouji
	 * @param bossUser
	 */
	public void deleteBossUser(BossUser bossUser);
	/**
	 * 修改BossUser
	 * @author zhouji
	 * @param bossUser
	 */
	public int alterBossUser(BossUser bossUser);
	/**
	 * 根据组织ID查询user用户集
	 * @author zhouji
	 * @param orgId
	 * @return
	 */
	public List<BossUser> getBossUsersByOrgId(Integer orgId);
	
	/**
	 * 根据组织ID查询当前及下级所有组织的user用户集
	 * @author zhouji
	 * @param orgId
	 * @return
	 */
	public List<BossUser> getCurAndSubBossUsersByOrgId(Integer orgId);
	
	/**
	 * 根据条件查询user用户集(无条件查询所有)
	 * @author zhouji
	 * @param bossUser
	 * @return
	 */
	public List<BossUser> getBossUsersByCondition(Page page);

	/**
	 * 根据用户名查询用户
	 * @param userCode
	 * @param ignoreStatus 是否忽略用户状态，默认不忽略
	 * @return
	 */
	public BossUser findBossUserByUserCode(String userCode,boolean...ignoreStatus);
	
	/**
	 * 根据Id查询用户
	 * @author zhouji
	 * @param userId
	 * @return
	 */
	public BossUser findBossUserByUserId(String userId);
	/**
	 * 添加用户角色
	 * @author zhouji
	 * @param userRole
	 */
	public void addBossUserRole(BossUserRole userRole);
	/**
	 * 修改用户角色
	 * @author zhouji
	 * @param userRole
	 */
	public void alertBossUserRole(BossUserRole userRole);
	/**
	 * 根据userId,roleId,status查询bossUserRole对象
	 * @author zhouji
	 * @param bossUserRole
	 * @return
	 */
	public BossUserRole findBossUserRoleByPK(BossUserRole bossUserRole);
	/**
	 * 根据roleId查询bossUserRole对象
	 *  删除角色时使用，查询是否有引用该角色的用户 
	 * @author biran
	 * @param bossUserRole
	 * @return
	 */
	public List<BossUserRole> findBossUserRoleByRoleID(BossUserRole bossUserRole);
	
	/**
	 * 
	 * @Description: 根据用户ID查询用户信息 
	 * @Author: fanyuna
	 * @Date: 2015年7月29日
	 * @param userId 用户ID
	 * @param ignoreStatus 是否忽略用户状态，默认不忽略
	 * @return
	 */
	public BossUser getBossUserByUserId(Long userId,boolean...ignoreStatus);
	
	
}
