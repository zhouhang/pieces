package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: UcUserDealInScaleEnum
 * @Description: 经营规模枚举
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public enum UcUserDealInScaleEnum {
	
	SCALE_LOW100W("1","100万以下/年"),
	SCALE_100W_AND_500W("2","100万—500万/年"),
	SCALE_500W_AND_1000W("3","500万—1000万/年"),
	SCALE_1000W_AND_5000W("4","1000万—5000万/年"),
	SCALE_BIG5000W("5","5000万以上/年");
	
	private String type;
	private String typeTitle;
	public String getType() {
		return type;
	}
	public String getTypeTitle() {
		return typeTitle;
	}
	private UcUserDealInScaleEnum(){}
	private UcUserDealInScaleEnum(String type, String typeTitle) {
		this.type = type;
		this.typeTitle = typeTitle;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(UcUserDealInScaleEnum item:UcUserDealInScaleEnum.values()){
			map.put(item.getType(), item.getTypeTitle());
		}
		return map;
	}
	
}
