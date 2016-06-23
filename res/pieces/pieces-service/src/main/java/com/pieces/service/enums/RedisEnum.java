package com.pieces.service.enums;

import org.apache.commons.lang.StringUtils;

public enum RedisEnum {
	KEY_PREFIX_SHIRO_REDIS_SESSION("shiro_session:"),
	KEY_PREFIX_SHIRO_REDIS_CACHE("shiro_cache:")
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
