package com.jointown.zy.common.redis;

import org.apache.commons.lang.StringUtils;

public enum RedisEnum {
	/**************************************************************
	 ***************************** Shiro session信息********************
	 *************************************************************/
	KEY_PREFIX_SHIRO_REDIS_SESSION("shiro_session:"),
	
	SESSION_USER_UC("ucUser"),
	SESSION_USER_BOSS("user"),
	SESSION_USER_BOSS_RULE("rules"),

	/** 微信使用的redis用户前缀 add by guoyb */
	SESSION_USER_WX("wxucUser"),
	SESSION_USER_BOSS_WX("wxUser"),
	SESSION_USER_BOSS_RULE_WX("wxrules"),
	
	/**************************************************************
	 ***************************** Shiro 缓存信息********************
	 *************************************************************/
	KEY_PREFIX_SHIRO_REDIS_CACHE("shiro_cache:"),
	KEY_PREFIX_SHIRO_REDIS_RETRY_COUNTER("retry_counter:"),
	KEY_PREFIX_SHIRO_REDIS_CACHE_AUTHENTICATION_INFO("authen:"),
	KEY_PREFIX_SHIRO_REDIS_CACHE_AUTHORIZATION_INFO("author:"),
	
	/**************************************************************
	 ***************************** CAS tickets 信息********************
	 *************************************************************/
	KEY_PREFIX_CAS_TICKETS("cas_ticket:"),

	KEY_LOGIN_SUCCESS_BACKURL("backUrl"),
	
	/*************************************************************
	 * ***********************wms api 接口类型************************
	 ************************************************************/
	KEY_PREFIX_WMS_API_FLAG("wms_api_fail_message"),
	
	/*************************************************************
	 * ***********************会员安全信息************************
	 ************************************************************/
	USER_SECURITY_INFO_EMAIL_OPT("user_security_email_opt:"),
	USER_SECURITY_INFO_MOBILE_OPT("user_security_mobile_opt:"),
	
	/*************************************************************
	 * ***********************人员组织结构缓存************************
	 ************************************************************/
	ORGLIST_CACHE("org_list_cache"),// name
	
	ORGLIST_KEY("org_list_key")// key
	
	;
	;
	
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
