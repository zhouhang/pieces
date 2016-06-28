/**
 * @author guoyb
 * 2015年3月19日 下午3:43:39
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author guoyb
 * 2015年3月19日 下午3:43:39
 */
public enum BusiParamEnum {
	//TODO 将文字统一用资源文件处理
	BUSI_DEPOSIT_RATE("dep_rate","0.2"),//保证金比例
	BUSI_DEPOSIT_DEFAULT("dep_default","100"),//保证金默认值(单位元)
	BUSI_DEPOSIT_DELAY("dep_delay","10"),//保证金后尾款延迟（day）
	BUSI_LOAN_DELAY("loan_delay","30"),//贷款后归还延迟（day）
	BUSI_PLATFORM_FINISHED_RATE("platform_finished_rate","0.005"),//订单完成划账平台所得比例
	BUSI_PLATFORM_OVERTIME_RATE("platform_overtime_rate","0.5")//订单过期划账平台所得比例
	;
	
	private String type;
	private String info;
	
	BusiParamEnum(String type,String info) {
		this.type = type;
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiParamEnum item : BusiParamEnum.values()) {
			map.put(item.getType(), item.getInfo());
		}
		return map;
	}
	
}
