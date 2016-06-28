package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.UcUser;

public interface UcUserService {
	
	/**
	 * 微信添加用户记录
	 * @param ucser
	 * @author lichenxiao
	 * @see UcUserService
	 * @param 
	 * @time 2015年7月15日
	 */
	public void wxAddUcUser(UcUser ucuser);
	/**
	 * 添加记录
	 * 
	 * @param userInfo userInfo
	 * @return PrimaryKey
	 */
	public void addUcUser(UcUser ucuser);

	/**
	 * 根据name获取记录
	 * 
	 * @param userName
	 * @param ignoreStatus 是否忽略状态，默认不忽略只取有效的
	 * @return UserInfo
	 */
	public UcUser findUcUserByUserName(String userName,boolean...ignoreStatus);
	
	/**
	 * 根据id获取记录
	 * 
	 * @param id
	 * @return UserInfo
	 */
	public UcUser findUcUserById(Integer id);
	
	/**
	 * 根据手机获取记录
	 * @author zhouji
	 * @param mobile
	 * @return
	 */
	public List<UcUser> findUcUserByMobile(String mobile);
	
	/**
	 * 修改密码
	 * @author zhouji
	 * @param ucuser
	 */
	public void updateUcUserPassword(UcUser ucuser);
	
	/**
	 * 修改前台会员手机号码
	 * @param ucuser
	 */
	public void updateUcUserMobile(UcUser ucuser);
	
	/**
	 * 修改前台会员公司名或姓名
	 * @param ucuser
	 */
	public void updateUcUserCompany(UcUser ucuser);
	
	/**
	 * 修改邮箱
	 * @author zhouji
	 * @param ucuser
	 */
	public void updateUcUserEmail(UcUser ucuser);
	
	/**
	 * 修改用户资料
	 * @author zhouji
	 * @param ucuser
	 */
	public void updateUcUserInfo(UcUser ucuser);
	
	/**
	 * 修改用户手机号 邮箱
	 * 前台 用户身份认证 提供
	 * @param ucuser
	 */
	public int updatePhoneAndEmail(UcUser ucuser);
	
	/**
	 * 修改用户登录资料
	 * @author biran
	 * @param ucuser
	 */
	public void updateUcUserLoginInfo(UcUser ucuser);
	
	
	/**
	 * 删除用户(逻辑删除,更改状态)
	 * @author zhouji
	 * @param ucuser
	 */
	public void deleteUcUser(UcUser ucuser);
	
	/**
	 * 
	 * @Description: 发送短信
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @param userId
	 * @param message
	 * @param logPrefix
	 */
	public void sendMessage(String userId,String message,String...logErrorPrefix);
	
	/**
	 *   为用户绑定业务员
	 * @author biran
	 * @param bossUser
	 */
	public void updateMemberSalesMan(UcUser ucuser);
	
	/**
	 * 
	 * @Description: 根据用户ID获取用户实名认证的名称
	 * @Author: fanyuna
	 * @Date: 2015年8月10日
	 * @param userId 用户ID
	 * @return
	 */
	public String getCertifyNameByUserId(Long userId);
	
}
