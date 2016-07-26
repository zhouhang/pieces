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
	
	//状态(0:未报价,1:已报价)
	private Integer status;
	
	//创建时间
	private Date createTime;

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

	public List<EnquiryCommoditys> getEnquiryCommoditys() {
		return enquiryCommoditys;
	}

	public void setEnquiryCommoditys(List<EnquiryCommoditys> enquiryCommoditys) {
		this.enquiryCommoditys = enquiryCommoditys;
	}
}