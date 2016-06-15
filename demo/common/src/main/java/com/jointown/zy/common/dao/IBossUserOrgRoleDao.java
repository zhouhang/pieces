package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BossUserOrg;
import com.jointown.zy.common.model.BossUserRole;

public interface IBossUserOrgRoleDao {

	/**
	 * 根据用户ID查询用户角色
	 * @author ldp	
	 * @param userId
	 * @return
	 */
	public List<BossUserRole> selectBossUserRole(String userId);
	
	/**
	 * 根据用户账号查询用户组织
	 * @author ldp
	 * @param userCode
	 * @return
	 */
	public List<BossUserOrg> selectBossUserOrg(String userCode);
	
	/**
	 * 根据管理员用户账号获取密码信息
	 * @author ldp
	 * @param userCode
	 * @return
	 */
	public BossUser getBossUserPasswordInfo(String userCode);
	
	/**
	 * 根据管理员用户账号修改密码
	 * @param userCode
	 * @return
	 */
	public int modifyBossUserSecret(Map<String,String> map);
	
}
