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
	
	//寄样商品(历史表中数据)
	private String intention;
	
	//处理状态：
	private Integer status;

	//是否废弃
	private Integer abandon;

	//联系人姓名
	private String nickname;

	//联系电话
	private String phone;

	//地区
	private String area;
	
	private Date updateTime;
	
	private Date createTime;
	
	public SendSample(){}

	public Integer getAbandon() {
		return abandon;
	}

	public void setAbandon(Integer abandon) {
		this.abandon = abandon;
	}

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}