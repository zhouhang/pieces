package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述： 微信订单状态枚举<br/>
 */
public enum WxOrderStateEnum {
	/** 已关闭 */
	CLOSED_ORDER("-1","已关闭"),
	/** 买家已下单,待付保证金 */
	PlACED_ORDER("0","已下单"),
	/** 交易已完成 */
	COMPLETED_ORDER("1","交易完成"),
	/** 买家已经取消 */
	CANCELED_ORDER("2","已取消"),
	/** 买家已付保证金,待平台备货 */
	PREPAID_DEPOSIT("3","待平台备货"),
	///** 买家已付款,待分割仓单 */
	//PAYED_ORDER("4","已付款"),
	/** 已备货,待付尾款*/
	READY_WARE("5","平台已备货");
	
	private String code;
	private String codeName;
	
	private WxOrderStateEnum(){}
	private WxOrderStateEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String,String> map = new LinkedHashMap<String, String>();
		for (WxOrderStateEnum item : WxOrderStateEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	/**
	 * 取得code
	 * @return code
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 设定code
	 * @param code code
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 取得codeName
	 * @return codeName
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定codeName
	 * @param codeName codeName
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
}
