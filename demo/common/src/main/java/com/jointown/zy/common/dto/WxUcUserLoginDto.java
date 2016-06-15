package com.jointown.zy.common.dto;

/**
 * 
 * 描述： 微信前台会员中心-登录DTO
 * 
 * 日期： 2015年3月19日
 * 
 * 作者： 李陈孝
 *
 * 版本： V1.0
 */
public class WxUcUserLoginDto extends BaseDto{

	private static final long serialVersionUID = -7300126834249222286L;
	
	/** 用户名 */
	private String username;
	
	/** 密码 */
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "WxUcUserLoginDto [username=" + username + ", password="
				+ password + "]";
	}

	
}
