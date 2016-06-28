package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 描述： 发票状态枚举<br/>
 * 
 * 日期： 2015年1月14日<br/>
 * 
 * 作者： Mr.songwei<br/>
 *
 * 版本： V1.0<br/>
 */
public enum BusiBillStateEnum {
	/** 不提供发票 */
	NOBILL("0", "不提供"),
	/** 提供发票 */
	HASBILL("1", "提供");
	
	private String code;
	private String codeName;
	private BusiBillStateEnum(){}
	private BusiBillStateEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiBillStateEnum item : BusiBillStateEnum.values()) {
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
