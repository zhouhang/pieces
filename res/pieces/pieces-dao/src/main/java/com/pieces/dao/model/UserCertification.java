package com.pieces.dao.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;


public class UserCertification  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//用户信息
	private Integer userId;
	
	//企业名称
	@NotEmpty
	@Length(min = 4, max = 50)
	private String company;
	
	//法人
	@NotEmpty
	@Length(min = 4, max = 50)
	private String corporation;

	@NotEmpty
	@Length(min = 4, max = 150)
	private String address;
	
	//企业类型
	private Integer type;
	
	private Date createTime;
	
	private Date updateTime;
	
	public UserCertification(){}
	
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
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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