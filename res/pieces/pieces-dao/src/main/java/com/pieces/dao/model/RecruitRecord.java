package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理商招募的跟进记录
 */
public class RecruitRecord  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//招募记录id
	private Integer recruitAgentId;
	
	//跟进人id
	private Integer followId;
	
	private Date createTime;
	
	//跟踪记录
	private String result;
	
	public RecruitRecord(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRecruitAgentId() {
		return recruitAgentId;
	}

	public void setRecruitAgentId(Integer recruitAgentId) {
		this.recruitAgentId = recruitAgentId;
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