package com.jointown.zy.web.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 用于承载token
 * @author LiuPiao
 *
 */
public class BossToken extends UsernamePasswordToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String validationCode;

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	// 省略 getter 和 setter 方法
	public BossToken(String username, String password, boolean rememberMe,
			String host, String validationCode) {
		super(username, password, rememberMe, host);
		this.validationCode = validationCode;
	}
}
