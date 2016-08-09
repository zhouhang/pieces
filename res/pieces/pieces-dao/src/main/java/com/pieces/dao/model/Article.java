package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Article  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String title;
	
	//文章类别ID
	private Integer categoryId;
	
	//文章状态
	private Integer status;
	
	//文章内容
	private String content;
	
	private Integer createUser;
	
	private Date createTime;
	
	private Integer updateUser;
	
	private Date updateTime;
	
	private Integer sort;
	
	//1 帮助中心 2 新闻模块
	private Integer model;

	// 文章发布时间
	private Date publishedDate;
	
	public Article(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getContent() {
		if (content!=null) {
			content = content.replace("&lt","<").replace("&gt",">");
		}
		return content;
	}

	public void setContent(String content) {
		if (content!=null) {
			content = content.replace("&lt","<").replace("&gt",">");
		}
		this.content = content;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
}