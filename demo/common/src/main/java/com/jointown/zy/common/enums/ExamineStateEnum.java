/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ExamineStateEnum
 * @Description: 订单申诉状态枚举类
 * @Author: 赵航
 * @Date: 2015年4月13日
 * @Version: 1.0
 */
public enum ExamineStateEnum {
	/** 待审核 */
	WAIT_AUDIT("1", "待审核"),
	/** 已通过 */
	FASSED("2", "已通过"),
	/** 已驳回 */
	REJECTED("3", "已驳回");
	
	/** 订单申诉状态code值 */
	private String code;
	/** 订单申诉状态code名称 */
	private String codeName;
	private ExamineStateEnum(){}
	private ExamineStateEnum(String code, String codeName){
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
		for (ExamineStateEnum item : ExamineStateEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	/**
	 * 获取订单申诉状态code值
	 * @return 订单申诉状态code值
	 */
	public String getCode() {
	    return code;
	}
	
	/**
	 * 获取订单申诉状态code值--short
	 * @return (short)订单申诉状态code值
	 */
	public Short getShortCode() {
		return Short.parseShort(code);
	}
	
	/**
	 * 设定订单申诉状态code值
	 * @param code 订单申诉状态code值
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 获取订单申诉状态code名称
	 * @return 订单申诉状态code名称
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定订单申诉状态code名称
	 * @param codeName 订单申诉状态code名称
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
}
