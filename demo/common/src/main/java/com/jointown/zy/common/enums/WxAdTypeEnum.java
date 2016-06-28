package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum WxAdTypeEnum {
	/** 类型：0-文字 1-图片 2-动画 3-声音 4-视频 */
	zero("0","文字"),
	one("1","图片"),
	tow("2","动画"),
	three("3","声音"),
	four("4","视频");
	
	private String code;
	private String codeName;
	private WxAdTypeEnum(){}
	private WxAdTypeEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WxAdTypeEnum item : WxAdTypeEnum.values()) {
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
