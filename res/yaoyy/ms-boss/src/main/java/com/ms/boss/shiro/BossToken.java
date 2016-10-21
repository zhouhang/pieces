package com.ms.boss.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *  BossToken
 */
public class BossToken extends UsernamePasswordToken {


	private String validationCode;

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	
	public BossToken(String username, String password, boolean rememberMe,
                     String host, String validationCode) {
		super(username, password, rememberMe, host);
		this.validationCode = validationCode;
	}


}
