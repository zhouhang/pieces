package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class CertifyRecord  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer userId;
	
	private Date createTime;
	
	//状态：0未处理，1审核通过，2审核不通过
	private Integer status;
	
	//跟进人id
	private Integer folllowId;
	
	//跟进时间
	private Date followTime;
	
	//不通过时的理由
	private String result;
	
	public CertifyRecord(){}
	
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
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getFolllowId() {
		return folllowId;
	}

	public void setFolllowId(Integer folllowId) {
		this.folllowId = folllowId;
	}
	
	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}