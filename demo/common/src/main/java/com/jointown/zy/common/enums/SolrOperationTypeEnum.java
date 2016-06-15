package com.jointown.zy.common.enums;

/**
 * 
 * 描述： 为solr提供元数据时的操作方式<br/>
 * 
 * 日期： 2015年1月23日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public enum SolrOperationTypeEnum {
	/** 添加 */
	ADD("add"),
	/** 更新 */
	UPDATE("update"),
	/** 删除 */
	DELETE("delete");
	
	private String name;
	private SolrOperationTypeEnum(){}
	private SolrOperationTypeEnum(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
