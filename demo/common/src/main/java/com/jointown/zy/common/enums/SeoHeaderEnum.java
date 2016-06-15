package com.jointown.zy.common.enums;
/**
 * @ClassName: SeoHeaderEnum
 * @Description: SEO 头部优化枚举类 类型判断区分
 * @Author: Calvin.wh
 * @Date: 2015-10-27
 * @Version: 1.0
 */
public enum SeoHeaderEnum {
	//搜索列表头部
	SEARCH_LIST_HEADER (0,0),
	//产品品类页面 左侧一级
	PRODUCT_TYPE_FIRST_LEVEL(1,1),
	//产品品类页面头部 左侧二级
	PRODUCT_TYPE_SECOND_LEVEL(2,2),
	//产品详情页头部
	PRODUCT_DETAIL_HEADER(3,3);
	
	private Integer key;
	private Integer value;
	
	private SeoHeaderEnum(Integer key, Integer value) {
		this.key = key;
		this.value = value;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
