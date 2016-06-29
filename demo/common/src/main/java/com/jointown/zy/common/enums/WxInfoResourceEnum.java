package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: WxInfoResourceEnum
 * @Description: 微信信息来源枚举
 * @Author: aizhengdong
 * @Date: 2015年6月15日
 * @Version: 1.0
 */
public enum WxInfoResourceEnum {

	WX("0","微信"),
	//EAST("1","东方中药材"),
	ZYC("珍药材","珍药材"),
	BACK("客服","客服");
	
	private String code;
	private String codeName;
	private WxInfoResourceEnum(){}
	private WxInfoResourceEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WxInfoResourceEnum item : WxInfoResourceEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCodeName() {
		return codeName;
	}
	
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}
