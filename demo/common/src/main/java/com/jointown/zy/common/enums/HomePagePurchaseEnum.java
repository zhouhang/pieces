package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: HomePagePurchaseEnum
 * @Description: 首页改版2.0 珍药采购枚举
 * @Author: Calvin.wh
 * @Date: 2015-11-2
 * @Version: 1.0
 */
public enum HomePagePurchaseEnum {
	
	HOMEPAGE_PURCHASE_LARGEAMOUNT("20","大货采购(2.0)")
	;
	
	
	private String type;//类型
	private String detail;//说明
	
	private HomePagePurchaseEnum(String type, String detail) {
		this.type = type;
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (HomePagePurchaseEnum item : HomePagePurchaseEnum.values()) {
			map.put(item.getType(), item.getDetail());
		}
		return map;
	}
}
