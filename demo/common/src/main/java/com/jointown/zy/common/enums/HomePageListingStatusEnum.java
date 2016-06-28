/**
 * @author guoyb
 * 2015年3月22日 下午7:43:12
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author guoyb
 * 2015年3月22日 下午7:43:12
 */
public enum HomePageListingStatusEnum {
	
	HOMEPAGE_LISTING_TYPE_DELETE("1","删除"),//同时这个1对应了数据库中删除标签的1
	HOMEPAGE_LISTING_TYPE_USING("0","正在使用"),
	HOMEPAGE_LISTING_TYPE_CURPREVIEW("2","当前预览"),
	HOMEPAGE_LISTING_TYPE_PREINDEX("3","上次页面")
	;
	
	private String type;
	private String detail;
	
	HomePageListingStatusEnum(String type,String detail) {
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
		for (HomePageListingStatusEnum item : HomePageListingStatusEnum.values()) {
			if (!(item.getType().equals("1")||item.getType().equals("3"))) {
				map.put(item.getType(), item.getDetail());
			}
		}
		return map;
	}
}
