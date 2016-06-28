package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ListingTimeLimitEnum {
	/****delete(删除周期为一天的挂牌期限) by ldp 2015-07-28 start*****************/
	//FIRST("1","1天"),
	/****delete(删除周期为一天的挂牌期限) by ldp 2015-07-28 end*****************/
	SENVEN("7","7天"),
	FOURTEEN("14","14天"),
	THIRTY("30","30天"),
	SIXTY("60","60天"),
	NINETY("90","90天");
	
	
	private String code;
	private String codeName;
	private ListingTimeLimitEnum(){}
	private ListingTimeLimitEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (ListingTimeLimitEnum item : ListingTimeLimitEnum.values()) {
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
