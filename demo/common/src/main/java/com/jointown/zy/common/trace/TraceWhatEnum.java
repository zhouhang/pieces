package com.jointown.zy.common.trace;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: TraceWhatEnum
 * @Description: 踪迹枚举，用于定于踪迹事件
 * @Author: robin.liu
 * @Date: 2015年8月19日
 * @Version: 1.0
 */
public enum TraceWhatEnum {
	ENTER("进入"),
	EXIT("离开"),
	SEARCH_KEYWORDS("搜索关键字");
	
	private String code;
	private String codeName;
	private TraceWhatEnum(){}
	
	private TraceWhatEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	private TraceWhatEnum(String codeName){
		this.code = name();
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (TraceWhatEnum item : TraceWhatEnum.values()) {
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
