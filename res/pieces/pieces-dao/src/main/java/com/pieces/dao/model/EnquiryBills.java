package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class EnquiryBills  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//询价单号
	private String code;
	
	//客户ID
	private Integer userId;
	
	//管理员ID
	private Integer memberId;
	
	//状态(0:未报价,1:已报价 2:已过期(根据报价有效期来判断))
	private Integer status;
	
	//创建时间
	private Date createTime;

	// 最后更新时间
	private Date updateTime;

	// 最后更新用户
	private Integer updateUser;

	// 最后报价时间
	private Date quotedTime;

	// 报价截止日期
	private Date expireDate;

	// 0 已读 1未读
	private Integer type;

	private List<EnquiryCommoditys> enquiryCommoditys;

	public EnquiryBills(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Date getQuotedTime() {
		return quotedTime;
	}

	public void setQuotedTime(Date quotedTime) {
		this.quotedTime = quotedTime;
	}

	public List<EnquiryCommoditys> getEnquiryCommoditys() {
		return enquiryCommoditys;
	}

	public void setEnquiryCommoditys(List<EnquiryCommoditys> enquiryCommoditys) {
		this.enquiryCommoditys = enquiryCommoditys;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}