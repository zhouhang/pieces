package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum SyslogEnum {
	

	//EC54315("[EC54315]:","EC54315"),
	EC54315_SOLR("[EC54315_SOLR]:","EC54315_SOLR"),
	WMS_API("[WMS_API]:","WMS_API"),
	SUPPLY_API("[SUPPLY_API]:","SUPPLY_API"),
	EC54315_PAY("[EC54315][PAY]:","EC54315_PAY"),
	EC54315_BUSINESS("[EC54315][BUSINESS]:","EC54315_BUSINESS");
	
	
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private SyslogEnum(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (FlagEnum item : FlagEnum.values()) {
			map.put(item.getCode(), item.getMessage());
		}
		return map;
	} 

}
