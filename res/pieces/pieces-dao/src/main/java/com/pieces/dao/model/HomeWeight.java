package com.pieces.dao.model;

import java.io.Serializable;


/**
 * 首页权重表
 */
public class HomeWeight  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//权重类型
	private String type;
	
	//权重名称
	private String name;
	
	//权重值
	private String value;
	
	//关联ID
	private Integer relevanceId;
	
	//图片URL
	private String pictureUrl;
	
	//排序
	private Integer sort;
	
	public HomeWeight(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(Integer relevanceId) {
		this.relevanceId = relevanceId;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}