package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用来存储后台设置的seo策略信息
 * 动态设置前台对应页面的title 关键字等
 */
public class SeoSetting  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//类型：1基本设置,2商品列表3，商品详情4，搜索结果5，文章列表，文章详情
	private Integer type;
	
	//标题
	private String title;
	
	//关键字
	private String keyWord;
	
	//描述
	private String intro;
	
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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