package com.ms.service.enums;

import org.apache.commons.lang.StringUtils;

public enum RedisEnum {
	KEY_PREFIX_SHIRO_REDIS_CACHE("shiro_cache:"),
	MEMBER_SESSION_BOSS("member_session_boss"),
	USER_SESSION_BIZ("user_session_biz"),

	KEY_MOBILE_CAPTCHA_REGISTER("mobile_captcha_register"),
	KEY_MOBILE_CAPTCHA_LOGIN("mobile_captcha_login"),
	KEY_MOBILE_CAPTCHA_INTERVAL("mobile_captcha_interval"),
	KEY_MOBILE_RESET_PASSWORD("mobile_reset_password"),
	KEY_MOBILE_TIMER("mobile_timer");

	@Override
	public String toString(){
		return getValue();
	}
	
	private String value;
	RedisEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getValue(String customedValue){
		if(StringUtils.isEmpty(customedValue)){
			return this.value;
		}
		return this.value+customedValue;
	}
}
