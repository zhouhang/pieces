package com.jointown.zy.common.trace;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: TraceWhereEnum
 * @Description: 踪迹枚举，用于定于踪迹地点
 * @Author: robin.liu
 * @Date: 2015年8月19日
 * @Version: 1.0
 */
public enum TraceWhereEnum {
	
	LISTING_DETAIL("商品详情页"),
	LISTING_SEARCH("商品搜索页"),
	INDEX("首页");
	
	private String code;
	private String codeName;
	private TraceWhereEnum(){}
	private TraceWhereEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	private TraceWhereEnum(String codeName){
		this.code = name();
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (TraceWhereEnum item : TraceWhereEnum.values()) {
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
