package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: UcUserDealRoleEnum
 * @Description: 会员经营身份
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public enum UcUserDealRoleEnum {
	
	PRODUCT_AREA("1","产地经营户"),
	MARKET_LARGER("2","市场经营大户"),
	SLICES_FACTORY("3","中药饮片厂"),
	MEDICINE_FACTORY("4","中成药厂"),
	GROWERS_COOPERATIVE("5"," 种植合作社"),
	HERBALIST("6","药农"),
	OTHERS("7","其他");
	
	private String type;
	private String typeTitle;
	public String getType() {
		return type;
	}
	public String getTypeTitle() {
		return typeTitle;
	}
	private UcUserDealRoleEnum(){}
	private UcUserDealRoleEnum(String type, String typeTitle) {
		this.type = type;
		this.typeTitle = typeTitle;
	}
	
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(UcUserDealRoleEnum item : UcUserDealRoleEnum.values()){
			map.put(item.getType(), item.getTypeTitle());
		}
		return map;
	}

}
