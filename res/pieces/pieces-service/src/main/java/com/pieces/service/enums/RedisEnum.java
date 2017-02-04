package com.pieces.service.enums;

import org.apache.commons.lang.StringUtils;

public enum RedisEnum {
	KEY_PREFIX_SHIRO_REDIS_CACHE("shiro_cache:"),
	MEMBER_SESSION_BOSS("member_session_boss"),
	USER_SESSION_BIZ("user_session_biz"),
	//用户认证基础信息
	USER_SESSION_CERTIFICATION("user_session_certification"),
	SITE_TAG_PINYIN_CATEGORY("site_tag_pinyin_category"),
	KEY_MOBILE_CAPTCHA("mobile_captcha"), // 注册验证码
	KEY_MOBILE_FINDPASSWORD_CAPTCHA("mobile_password_captcha"),
	KEY_MOBILE_CAPTCHA_INTERVAL("mobile_captcha_interval"),
	KEY_MOBILE_TIMER("mobile_timer"),
	NOT_HANDLE_ID_MAP("not_handle_map_");



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
