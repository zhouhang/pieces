/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiOrderTypeEnum
 * @Description: 订单类型
 * @Author: 赵航
 * @Date: 2015年9月10日
 * @Version: 1.0
 */
public enum BusiOrderTypeEnum {
	/** 普通订单 */
	ORDINARY_ORDER("1", "普通订单"),
	/** 账期订单 */
	ACCOUNTING_ORDER("2", "账期订单"),
	/** 全款订单 */
	FULLPAY_ORDER("3", "全款订单");
	

	/** CODE值 */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiOrderTypeEnum(){}
	private BusiOrderTypeEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	/**
	 * @Description: 枚举转换成MAP
	 * @Author: 赵航
	 * @Date: 2015年5月18日
	 * @return MAP
	 */
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiOrderTypeEnum item : BusiOrderTypeEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}

	/**
	 * 获取CODE值
	 * @return CODE值
	 */
	public String getCode() {
	    return code;
	}

	/**
	 * 设定CODE值
	 * @param code CODE值
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
