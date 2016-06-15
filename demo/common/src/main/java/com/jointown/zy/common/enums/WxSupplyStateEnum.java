package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: WxSupplyStateEnum
 * @Description: 微信供求信息状态枚举
 * @Author: wangjunhu
 * @Date: 2015年5月13日
 * @Version: 1.0
 */
public enum WxSupplyStateEnum {

	WATING("0","待审核"),
	SUCCESS("1","审核通过"),
	FAILURE("2","审核未通过"),
	DELETE("3","已撤销");
	
	private String code;
	private String codeName;
	private WxSupplyStateEnum(){}
	private WxSupplyStateEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WxSupplyStateEnum item : WxSupplyStateEnum.values()) {
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
