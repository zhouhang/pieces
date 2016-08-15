package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class OrderRemark  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//备注的用户
	private Integer userId;
	
	//备注内容
	private String content;
	
	//订单id
	private Integer orderId;
	
	//评论时间
	private Date createrTime;
	
	public OrderRemark(){}
	
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}
	
}