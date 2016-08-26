package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class CommodityCollect  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//商品收藏
	private Integer commodityId;
	
	//收藏用户
	private Integer userId;
	
	//创建时间
	private Date createTime;
	
	public CommodityCollect(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
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
	
}