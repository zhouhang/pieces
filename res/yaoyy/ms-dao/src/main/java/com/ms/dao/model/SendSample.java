package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class SendSample  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//user表主键
	private Integer userId;
	
	//寄样单号
	private String code;
	
	//寄样商品
	private String intention;
	
	//上下架状态 0：下架，1：上架
	private Integer status;
	
	private Date updateTime;
	
	private Date createTime;
	
	public SendSample(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}