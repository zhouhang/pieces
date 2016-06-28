package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用于定义各标志枚举。 譬如状态信息，类型信息 
 * @author Administrator
 *
 */
public enum FlagEnum {
	BOSS_PERMISSION_TYPE_SYSTEM("0", "待审核"),
	BOSS_PERMISSION_TYPE_CATALOG("1", "待审核"),
	BOSS_PERMISSION_TYPE_MENU("2", "待审核"),
	BOSS_PERMISSION_TYPE_BUTTON("3", "待审核");
	
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
	
	private FlagEnum(String code,String message){
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
