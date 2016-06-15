package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 描述： 收藏状态枚举<br/>
 * 
 * 日期： 2015年1月9日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public enum BusiCollectionStateEnum {
	/** 已收藏 */
	COLLECTION("0", "已收藏"),
	/** 收藏取消 */
	COLLECTION_CANCEL("1", "收藏取消");
	
	private String code;
	private String codeName;
	private BusiCollectionStateEnum(){}
	private BusiCollectionStateEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiCollectionStateEnum item : BusiCollectionStateEnum.values()) {
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
