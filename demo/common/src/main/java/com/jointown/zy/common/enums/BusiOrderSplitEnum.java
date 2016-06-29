package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: BusiOrderSplitEnum
 * @Description: 订单分割标识枚举类
 * @Author: fanyuna
 * @Date: 2015年9月11日
 * @Version: 1.0
 */
public enum BusiOrderSplitEnum {

	/** 订单未分割  */
	ORDER_NOT_SPLIT("1","未分割"),
	/** 订单分割  */
	ORDER_SPLIT("2","已分割");
	
	/** CODE值 */
	private String code;
	
	/** CODE名称 */
	private String codeName;
	
	private BusiOrderSplitEnum(){}
	private BusiOrderSplitEnum(String code, String codeName){
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
