package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class AnonFollowRecord  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//匿名询价id
	private Integer anonEnquiryId;
	
	//跟进者id
	private Integer followerId;
	
	private Date createTime;
	
	//跟进结果
	private String result;
	
	public AnonFollowRecord(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAnonEnquiryId() {
		return anonEnquiryId;
	}

	public void setAnonEnquiryId(Integer anonEnquiryId) {
		this.anonEnquiryId = anonEnquiryId;
	}
	
	public Integer getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Integer followerId) {
		this.followerId = followerId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}