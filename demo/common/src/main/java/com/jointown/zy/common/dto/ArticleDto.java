/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jointown.zy.common.dto;

/**
 * 文章Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class ArticleDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private String id;	
	private String title;	// 标题
	private String categoryId;
	public ArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticleDto(String id, String title, String categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}


