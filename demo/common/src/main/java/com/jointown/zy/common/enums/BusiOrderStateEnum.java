package com.jointown.zy.common.enums;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * 描述： 订单状态枚举<br/>
 * 
 * 日期： 2015年1月9日<br/>
 * updatetime 2015.4.8
 * 
 * 作者： 赵航<br/>
 * updater:guoyb
 *
 * 版本： V1.0<br/>
 */
public enum BusiOrderStateEnum {
//	/** 洽谈中 */
//	CHATING("0", "洽谈中"),
//	/** 已完成 */
//	FINISHED("1", "已完成"),
//	/** 已取消 */
//	CANCELED("2", "已取消");
	//0.买家已下单，1.交易已完成，2. 买家已经取消，3. 买家已付保证金，4. 买家已付款
	/** 已关闭 */
	CLOSED_ORDER("-1","已关闭",7),
	/** 买家已下单,待付保证金 */
	PlACED_ORDER("0","已下单",1),
	/** 交易已完成 */
	COMPLETED_ORDER("1","交易已完成",5),
	/** 买家已经取消 */
	CANCELED_ORDER("2","已取消",6),
	/** 买家已付保证金,待平台备货 */
	PREPAID_DEPOSIT("3","已付保证金",2),
	/** 买家已付款,待分割仓单 */
	PAYED_ORDER("4","已付款",4),
	/** 已备货,待付尾款*/
	READY_WARE("5","已备货",3);
	
	private String code;
	private String codeName;
	private int order;
	
	private BusiOrderStateEnum(){}
	private BusiOrderStateEnum(String code, String codeName, int...orders){
		this.code = code;
		this.codeName = codeName;
		this.order = ArrayUtils.isEmpty(orders)?this.ordinal():orders[0];
	}
	
	public static Map<String, String> toMap(){
		Map<String,String> map = new LinkedHashMap<String, String>();
		for (BusiOrderStateEnum item : BusiOrderStateEnum.sortedValues()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	public static String obtainCodeName(String code){
		String codeName = "";
		if(StringUtils.isNotBlank(code)){
			for (BusiOrderStateEnum item : BusiOrderStateEnum.sortedValues()) {
				if(code.equals(item.getCode())){
					codeName = item.getCodeName();
				}
			}
		}
		return codeName;
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	//排序了的枚举
	public static Collection<BusiOrderStateEnum> sortedValues(){
		Map<BusiOrderStateEnum,BusiOrderStateEnum> map = new TreeMap<BusiOrderStateEnum,BusiOrderStateEnum>(new Comparator<BusiOrderStateEnum>() {
			@Override
			public int compare(BusiOrderStateEnum o1, BusiOrderStateEnum o2) {
				return o1.getOrder()-o2.getOrder();
			}
		});
		for (BusiOrderStateEnum item : BusiOrderStateEnum.values()) {
			map.put(item, item);
		}
		return map.values();
	}
	
}
