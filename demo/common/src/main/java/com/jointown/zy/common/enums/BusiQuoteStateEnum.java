package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiQuoteStateEnum
 * @Description: 报价状态枚举
 * @Author: shangcuijuan
 * @Date: 2015年10月13日
 * @Version: 1.0
 */
public enum BusiQuoteStateEnum {

	/** 洽谈中 */
	NEGOTIATING("0", "洽谈中"),
	/** 达成交易 */
	DEAL_SUCCESS("10", "达成交易"),
	/** 采购已结束 */
	FINISHED("-10", "采购已结束");
	
	
	/** CODE */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiQuoteStateEnum(){}
	private BusiQuoteStateEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	/**
	 * @Description: 将枚举转化为Map
	 * @Author: shangcuijuan
	 * @Date: 2015年10月13日
	 * @return
	 */
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiQuoteStateEnum item : BusiQuoteStateEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	/**
	 * 获取CODE
	 * @return CODE
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 设定CODE
	 * @param code CODE
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 获取CODE名称
	 * @return CODE名称
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定CODE名称
	 * @param codeName CODE名称
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
}