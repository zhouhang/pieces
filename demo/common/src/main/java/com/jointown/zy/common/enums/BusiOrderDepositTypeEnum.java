/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiOrderDepositTypeEnum
 * @Description: 交易划账类型
 * @Author: 赵航
 * @Date: 2015年5月18日
 * @Version: 1.0
 */
public enum BusiOrderDepositTypeEnum {
	/** 订单完成划账 */
	ORDER_FINISHED_DEPOSIT("1", "订单完成划账"),
	/** 订单过期划账 */
	ORDER_OVERTIME_DEPOSIT("2", "订单过期划账"),
	/** 订单申退划账 */
	ORDER_REFUND_DEPOSIT("3", "订单申退划账");
	
	/** CODE值 */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiOrderDepositTypeEnum(){}
	private BusiOrderDepositTypeEnum(String code, String codeName){
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
		for (BusiOrderDepositTypeEnum item : BusiOrderDepositTypeEnum.values()) {
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
