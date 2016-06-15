/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiOrderDepositResultEnum
 * @Description: 交易划账结果
 * @Author: 赵航
 * @Date: 2015年5月18日
 * @Version: 1.0
 */
public enum BusiOrderDepositResultEnum {
	/** 划账成功 */
	DEPOSIT_SUCCESS("1", "申请划账成功"),
	/** 划账失败 */
	DEPOSIT_FAILURE("2", "申请划账失败");
	
	/** CODE值 */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiOrderDepositResultEnum(){}
	private BusiOrderDepositResultEnum(String code, String codeName){
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
		for (BusiOrderDepositResultEnum item : BusiOrderDepositResultEnum.values()) {
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
