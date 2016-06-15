/**
 * @author guoyb
 * 2015年3月19日 下午3:43:25
 */
package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * homepage_ad
 * category 大分类：1-首页，2-活动，3-
 * type     类型：11-首顶广告大_1,12-首顶广告大_2,13-首顶广告大_3,
 *              21-热销大品种大,22-热销大品种左,
 * 			    31-珍稀小品种大,32-珍稀小品种左,
 * 			    42-低价特卖左
 * 			    51-药企采购大
 * @author guoyb
 * 2015年3月19日 下午3:43:25
 */
public enum HomepageAdEnum {
	//TODO 将文字统一用资源文件处理
	/**首页1.0*/
//	HOMEPAGE_AD_11("0","首页banner"),
//	HOMEPAGE_AD_23("5","主推品种"),
//	HOMEPAGE_AD_12("1","大宗品种"), 
//	HOMEPAGE_AD_13("2","常用品种"), 
//	//update by  Mr.song 2015.5.18 9:24 弃用此类型
//	//HOMEPAGE_AD_21("3","低价特卖"), 
//	HOMEPAGE_AD_22("4","药企采购"),
//	
	/**首页2.0*/
	HOMEPAGE_AD_BANNER_BIG("20","大幅banner"),
	HOMEPAGE_AD_BANNER_SPACIAL("21","专题banner"),
	HOMEPAGE_AD_STRAIGHTPIN("22","合作社直销"),
	HOMEPAGE_AD_ORGIN_NORTH("23","道地药材-北方大区"),
	HOMEPAGE_AD_ORGIN_NORTHWEST("24","道地药材-西北大区"),
	HOMEPAGE_AD_ORGIN_EAST("25","道地药材-华东大区"),
	HOMEPAGE_AD_ORGIN_MIDSOUTH("26","道地药材-中南大区"),
	HOMEPAGE_AD_ORGIN_SOUTHWEST("27","道地药材-西南大区"),
	HOMEPAGE_AD_ORGIN_SOUTH("28","道地药材-华南大区"),
	HOMEPAGE_AD_BIDS("29","药厂招标")
	;
	
	
	
		
	private String type;
	private String detail;

	private HomepageAdEnum() {
	}

	private HomepageAdEnum(String type, String detail) {
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
		for (HomepageAdEnum item : HomepageAdEnum.values()) {
			map.put(item.getType(), item.getDetail());
		}
		return map;
	}
	
	/**
	 * 道地药材 珍药采购类型
	 */
	static String ddType[] = {"23","24","25","26","27","28","29"};
	
	public static Map<String,String> getDaoDiTypeMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (HomepageAdEnum item : HomepageAdEnum.values()) {
			for(String type : ddType){
				if(item.getType().equals(type)){
					map.put(item.getType(), item.getDetail());
				}
			}
		}
		return map;
	}
}
