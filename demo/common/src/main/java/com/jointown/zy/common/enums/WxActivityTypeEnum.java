package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum WxActivityTypeEnum {

	zero("0","活动"),
	one("1","卖药材介绍");
	
	private String code;
	private String codeName;
	private WxActivityTypeEnum(){}
	private WxActivityTypeEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WxActivityTypeEnum item : WxActivityTypeEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	/**
	 * 取得code
	 * @return code
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 设定code
	 * @param code code
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 取得codeName
	 * @return codeName
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定codeName
	 * @param codeName codeName
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
}
