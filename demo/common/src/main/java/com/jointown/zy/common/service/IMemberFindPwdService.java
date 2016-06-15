package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.UcUser;

/**
 * 会员前端找回密码（手机找回、邮箱找回）
 * author ldp
 * 2014年12月11日
 * @useBy 微信项目：guoyb：WxMemberFindPwdController,...;
 */
public interface IMemberFindPwdService {
	
	/**
	 * 获取手机短信验证码
	 * @author ldp
	 * @return
	 */
	public String getMobileCode(String mobileNo,String mobileCode);
	
	/**
	 * 根据邮箱找回密码
	 * @author ldp
	 * @param userName
	 * @return
	 */
	public String findPasswordByEmail(String userName);
	
	/**
	 * 根据邮件发送的链接，跳转到修改密码页面
	 * @param userName
	 * @param token
	 * @return
	 */
	public String verityEmail(String key);
	
	/**
	 * 根据会员名修改密码
	 * @param userName
	 * @return
	 */
	public String modifyPwd(String userName,String newPassword);
	
	/**
	 * 判断手机号是否存在
	 * @param mobile
	 * @return
	 */
	public List<UcUser> findMemberMobile(String mobile);
	
	public List<UcUser> findMemberByMobileAndUsername(Map<Object,Object> queryMap);
}
