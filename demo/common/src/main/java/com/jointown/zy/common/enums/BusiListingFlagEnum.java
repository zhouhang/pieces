package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 描述： 挂牌标志枚举<br/>
 * 
 * 日期： 2015年1月9日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public enum BusiListingFlagEnum {
	/** 待审核 */
	AUDIT_WAITING("0", "待审核"),
	/** 审核失败 */
	AUDIT_FAILURE("1", "审核失败"),
	/** 挂牌中 */
	LISTING("2", "挂牌中"),
	/** 已完成 */
	LISTING_SOLDOUT("3", "已完成"),
	/** 已取消 */
	LISTING_CANCEL("4", "已取消");
	
	private String code;
	private String codeName;
	private BusiListingFlagEnum(){}
	private BusiListingFlagEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiListingFlagEnum item : BusiListingFlagEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	public static String getCodeNameByCode(String code){
		for(BusiListingFlagEnum flag:BusiListingFlagEnum.values()){
			if(flag.getCode().equals(code)){
				return flag.getCodeName();
			}
		}
		return null;
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
