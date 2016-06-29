/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiOrderDepositStateEnum
 * @Description: 交易划账状态
 * @Author: 赵航
 * @Date: 2015年5月18日
 * @Version: 1.0
 */
public enum BusiOrderDepositStateEnum {
	/** 待处理 */
	DEPOSIT_UNTREATED("1", "待处理"),
	/** 处理中 */
	DEPOSIT_PROCESSING("3", "处理中"),
	/** 已处理 */
	DEPOSIT_TREATED("2", "已处理"),
	/** 已拒绝 */
	DEPOSIT_REFUSED("4", "已拒绝");
	
	/** CODE值 */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiOrderDepositStateEnum(){}
	private BusiOrderDepositStateEnum(String code, String codeName){
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
		for (BusiOrderDepositStateEnum item : BusiOrderDepositStateEnum.values()) {
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
