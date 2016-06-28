package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户性别类枚举
 * @ClassName: UcUserSexEnum
 * @Author: Calvin.wh
 * @Date: 2015-10-20
 * @Version: 1.0
 */
public enum UcUserSexEnum {
	
	MAN("1","先生"),
	WOMAN("0","女士");
	
	private String key;
	private String val;
	
	private UcUserSexEnum(){}
	private UcUserSexEnum(String key, String val) {
		this.key = key;
		this.val = val;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(UcUserSexEnum item:UcUserSexEnum.values()){
			map.put(item.getKey(), item.getVal());
		}
		return map;
	}

}
