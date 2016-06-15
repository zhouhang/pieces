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
public enum HomepageListingEnum {
	/**
	 * update by Mr.song 2015.4.16 11:38 
	 * 1.增加HOMEPAGE_LISTING_1("1","火爆品种","6"), 
	 * 2.沿用老的HOMEPAGE_LISTING_4("4","低价特卖","6"),
	 * 3.修改HOMEPAGE_LISTING_RECOM("8","猜你喜欢","4") --> HOMEPAGE_LISTING_RECOM("8","热门推荐","4")
	 *
	 */
	/**首页1.0*/
//	HOMEPAGE_LISTING_1("1","火爆品种","6"),
//	HOMEPAGE_LISTING_4("4","低价特卖","6"),
	HOMEPAGE_LISTING_RECOM("8","热门推荐","5"),
//	HOMEPAGE_LISTING_9("9","主推品种","5"),
//	HOMEPAGE_LISTING_2("2","大宗品种","5"),
//	HOMEPAGE_LISTING_3("3","常用品种","5"),
//	HOMEPAGE_LISTING_6("5","涨价药材","4"),
//	HOMEPAGE_LISTING_7("6","跌价药材","4"),
//	HOMEPAGE_LISTING_8("7","紧俏药材","4"),
	
	/**首页2.0 add by Calvin.wh*/
	HOMEPAGE_LISTING_RESOURCE("20","大户资源","3"),
	HOMEPAGE_LISTING_WAREHOUSE("21","全国大仓","18"),
	HOMEPAGE_LISTING_ORGIN_NORTH("22","道地药材-北方大区","4"),
	HOMEPAGE_LISTING_ORGIN_NORTHWEST("23","道地药材-西北大区","4"),
	HOMEPAGE_LISTING_ORGIN_EAST("24","道地药材-华东大区","4")	,
	HOMEPAGE_LISTING_ORGIN_MIDSOUTH("25","道地药材-中南大区","4"),
	HOMEPAGE_LISTING_ORGIN_SOUTHWEST("26","道地药材-西南大区","4"),
	HOMEPAGE_LISTING_ORGIN_SOUTH("27","道地药材-华南大区","4")

	;
	
	private String type;
	private String detail;
	private String length;//显示个数
	
	HomepageListingEnum(String type,String detail,String length) {
		this.type = type;
		this.detail = detail;
		this.length = length;
	}

	public String getLength() {
		return length;
	}


	public void setLength(String length) {
		this.length = length;
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
		for (HomepageListingEnum item : HomepageListingEnum.values()) {
			map.put(item.getType(), item.getDetail());
		}
		return map;
	}
	
	public static Map<String, String> getTypeLengMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (HomepageListingEnum item : HomepageListingEnum.values()) {
			map.put(item.getType(), item.getLength());
		}
		return map;
	}
}
