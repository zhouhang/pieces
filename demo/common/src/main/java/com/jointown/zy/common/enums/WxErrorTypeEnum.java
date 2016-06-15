package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum WxErrorTypeEnum {
	
	ORDER_IS_NULL("101","订单号为空"),
	ORDER_ERROR("102","订单无效");
	
	private String error;
	private String errorName;
	private WxErrorTypeEnum(){}
	private WxErrorTypeEnum(String error, String errorName){
		this.error = error;
		this.errorName = errorName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WxErrorTypeEnum item : WxErrorTypeEnum.values()) {
			map.put(item.getError(), item.getErrorName());
		}
		return map;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
}
