package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossUserOrgRoleVO;

public interface BossUserService {
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
	public void updateBossUser(BossUser bossUser);
	
	/**
	 * @param bossUser 
	 * @Description: 后台-锁定工作账号
	 * @param BossUser bossUser<命名不规范> 表示前端传过来的界面DTO对象，即用户填写用户的基本参数
	 * @Author: 宋威
	 * @Date: 2015年6月16日 16:19
	 */
	public void updateBossUserByLock(BossUser bossUser);
	
	
	/**
	 * 根据组织ID查询user用户集
	 * @author zhouji
	 * @param orgId
	 * @return
	 */
	public List<BossUser> getBossUsersByOrgId(Integer orgId,String status);
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
	 * @param ignoreStatus 是否忽略用户状态，默认不忽略，只取有效的
	 * @return
	 */
	public BossUser findBossUserByUserCode(String userCode,boolean...ignoreStatus);
	/**
	 * 密码重置
	 * @author zhouji
	 * @param userId
	 * @return
	 */
	public int secretReset(String userId);
	
	/**
	 * 根据账户名查询用户角色、组织名称
	 * @author ldp
	 * @param userCode
	 * @return
	 */
	public BossUserOrgRoleVO getBossUserOrgRole(String userCode);
	
	/**
	 * 管理员修改账号密码
	 * @author ldp
	 * @param userCode
	 * @return
	 */
	public String modifyBossUserSecret(String userCode,String oldPassword,String newPassword);
	
}
