/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jointown.zy.common.dto;

import java.util.Date;

/**
 * 文章Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Article1Dto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private String acid;	
	private String title;	// 标题
	private String lmid;
	private Date dtm;//时间
	private String cont;//文章内容
	private String writer;//作者
	public Article1Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article1Dto(String acid, String title, String lmid, Date dtm,
			String cont, String writer) {
		super();
		this.acid = acid;
		this.title = title;
		this.lmid = lmid;
		this.dtm = dtm;
		this.cont = cont;
		this.writer = writer;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLmid() {
		return lmid;
	}
	public void setLmid(String lmid) {
		this.lmid = lmid;
	}
	public Date getDtm() {
		return dtm;
	}
	public void setDtm(Date dtm) {
		this.dtm = dtm;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}


