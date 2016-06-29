/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiPurchaseStatusEnum
 * @Description: 采购交易状态枚举
 * @Author: 赵航
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public enum BusiPurchaseStatusEnum {

	/** 待审核 */
	AUDIT_WAITING("0", "待审核"),
	/** 待报价 */
	OFFER_WAITING("10", "待报价"),
	/** 洽谈中 */
	NEGOTIATING("20", "洽谈中"),
	/** 交易成功 */
	DEAL_SUCCESS("30", "交易成功"),
	/** 审核不通过 */
	AUDIT_FAILURE("-10", "审核不通过"),
	/** 已结束 */
	FINISHED("-20", "已结束");
	
	/** CODE */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiPurchaseStatusEnum(){}
	private BusiPurchaseStatusEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	/**
	 * @Description: 将枚举转化为Map
	 * @Author: 赵航
	 * @Date: 2015年10月12日
	 * @return
	 */
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiPurchaseStatusEnum item : BusiPurchaseStatusEnum.values()) {
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
