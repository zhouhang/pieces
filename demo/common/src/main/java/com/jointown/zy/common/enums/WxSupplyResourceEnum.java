package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: WxSupplyResourceEnum
 * @Description: 供应信息来源枚举
 * @Author: wangjunhu
 * @Date: 2015年6月16日
 * @Version: 1.0
 */
public enum WxSupplyResourceEnum {

	WX("0","微信"),
	EAST("1","东方中药材"),
	BACK("2","客服");
	
	private String code;
	private String codeName;
	private WxSupplyResourceEnum(){}
	private WxSupplyResourceEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WxSupplyResourceEnum item : WxSupplyResourceEnum.values()) {
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
