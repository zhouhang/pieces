package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class RecruitAgent  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//联系人
	private String name;
	
	//电话
	private String phone;
	
	//区域id
	private Integer areaId;
	
	//申请时间
	private Date createTime;
	
	//最后一次跟进时间
	private Date lastFollowTime;
	
	//跟进人
	private Integer lastFollowId;
	
	//状态0未处理1已处理
	private Integer status;
	
	public RecruitAgent(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}
	
	public Integer getLastFollowId() {
		return lastFollowId;
	}

	public void setLastFollowId(Integer lastFollowId) {
		this.lastFollowId = lastFollowId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}