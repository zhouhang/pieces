package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class UserQualification  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer userId;
	
	//证件号
	private String number;
	
	//有效期
	private String term;
	
	//0:非长期，1：长期
	private Integer status;
	
	//证书类型（三证合一的要做区分）
	private Integer type;
	
	private Integer isCombine;
	
	//图片地址
	private String pictureUrl;
	
	private Date createTime;
	
	private Date updateTime;
	
	public UserQualification(){}
	
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
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getIsCombine() {
		return isCombine;
	}

	public void setIsCombine(Integer isCombine) {
		this.isCombine = isCombine;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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