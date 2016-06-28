package com.jointown.zy.common.enums;

/**
 * 
 * 描述： 根据solr中的collection的字段来源不同（主要是来自不同的表），划定不同区分，以标定push的内容是针对哪些字段<br/>
 * 
 * 日期： 2015年1月23日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public enum SolrContentTypeEnum {
	/** 采购批次相关信息 */
	PURCHASE_BATCH("purchaseBatch"),
	/** 采购相关信息 */
	PURCHASE("purchase"),
	/** 挂牌相关信息 */
	LISTING("listing"),
	/** 品种相关信息 */
	BREED("breed"),
	/** 仓单相关信息 */
	WHLIST("whlist"),
	/** 数量单位相关信息 */
	WLUNIT("wlunit");
	
	private String name;
	private SolrContentTypeEnum(){}
	private SolrContentTypeEnum(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
