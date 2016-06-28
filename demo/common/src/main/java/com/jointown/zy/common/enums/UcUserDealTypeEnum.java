package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: UcUserDealTypeEnum
 * @Description: 会员业务类型
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public enum UcUserDealTypeEnum {

	BUY_TYPE(1,"我买药材"),
	SELL_TYPE(2,"我卖药材"),
	BUYANDSELL_TYPE(3,"我既买药材，也卖药材");

	private int type;
	private String typeTitle;
	
	private UcUserDealTypeEnum(){}
	public int getType() {
		return type;
	}
	public String getTypeTitle() {
		return typeTitle;
	}
	private UcUserDealTypeEnum(int type, String typeTitle) {
		this.type = type;
		this.typeTitle = typeTitle;
	}
	
	public static Map<String,String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(UcUserDealTypeEnum item:UcUserDealTypeEnum.values()){
			map.put(String.valueOf(item.getType()), item.getTypeTitle());
		}
		return map;
	}
	
}
