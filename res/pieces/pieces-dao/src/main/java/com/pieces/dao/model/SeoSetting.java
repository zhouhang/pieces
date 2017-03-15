package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class SeoSetting  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//类型：1基本设置,2商品列表3，商品详情4，搜索结果5，文章列表，文章详情
	private Integer type;
	
	//1,标题2，关键字，3，描述
	private Integer place;
	
	//内容
	private String content;
	
	private Date createTime;
	
	private Date updateTime;
	
	public SeoSetting(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}