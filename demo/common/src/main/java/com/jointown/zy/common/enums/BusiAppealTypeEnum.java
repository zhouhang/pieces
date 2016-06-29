/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ExamineStateEnum
 * @Description: 订单申诉类型枚举类
 * @Author: 赵航
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
public enum BusiAppealTypeEnum {
	/** 质量问题 */
	QUALITY_REASON("1", "质量问题"),
	/** 其他原因 */
	OTHER_REASON ("2", "其他原因");
	
	/** 订单申诉类型code值 */
	private String code;
	/** 订单申诉类型code名称 */
	private String codeName;
	private BusiAppealTypeEnum(){}
	private BusiAppealTypeEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	/**
	 * @Description: 将枚举转换成map
	 * @Author: 赵航
	 * @Date: 2015年4月14日
	 * @return 枚举map
	 */
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiAppealTypeEnum item : BusiAppealTypeEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	/**
	 * 获取订单申诉类型code值
	 * @return 订单申诉类型code值
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 设定订单申诉类型code值
	 * @param code 订单申诉类型code值
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 获取订单申诉类型code名称
	 * @return 订单申诉类型code名称
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定订单申诉类型code名称
	 * @param codeName 订单申诉类型code名称
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
}
