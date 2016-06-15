package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: AmtTypeEnum
 * @Description: 金额款项枚举
 * @Author: ldp
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public enum AmtTypeEnum {
	
	PAY_DEPOSIT(0,"支付保证金"),
	PAY_BALANCE_PAYMENT(1,"支付尾款"),
	PAY_FULL_PAYMENT(2,"支付全款");
	
	
	private int code;
	private String title;
	
	
	private AmtTypeEnum(){}

	private AmtTypeEnum(int code, String title) {
		this.code = code;
		this.title = title;
	}
	
	public int getCode() {
		return code;
	}
	public String getTitle() {
		return title;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(AmtTypeEnum item : AmtTypeEnum.values()){
			map.put(String.valueOf(item.getCode()), item.getTitle());
		}
		return map;
	}
	
}
