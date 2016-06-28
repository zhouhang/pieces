package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: BusiAtmTypeEnum
 * @Description: 金额类型(款项)：0-保证金 1-尾款 2-全款
 * @Author: guoyb
 * @Date: 2015年4月13日
 * @Version: 1.0
 */
public enum BusiAmtTypeEnum {
	/** 买家已下单 */
	BUSI_PAY_DEPOSIT("0","保证金"),
	/** 交易已完成 */
	BUSI_PAY_FINAL("1","尾款"),
	/** 买家已经取消 */
	BUSI_PAY_ALL("2","全款");
	
	private String code;
	private String type;
	private BusiAmtTypeEnum(){}
	private BusiAmtTypeEnum(String code, String type){
		this.code = code;
		this.type = type;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiAmtTypeEnum item : BusiAmtTypeEnum.values()) {
			map.put(item.getCode(), item.getType());
		}
		return map;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
