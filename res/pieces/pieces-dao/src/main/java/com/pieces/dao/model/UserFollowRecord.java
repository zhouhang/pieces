package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class UserFollowRecord  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//用户id
	private Integer userId;
	
	//跟进人id
	private Integer followId;
	
	private Date createTime;
	
	//跟进结果
	private String result;
	
	public UserFollowRecord(){}
	
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
	
	public Integer getFollowId() {
		return followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
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