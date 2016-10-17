package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Gradient  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer commodityId;

	//起步数量
	private Float start;

	// 终止数量
	private Float end;

	// 价格
	private Float price;

	// 单位: 斤 条 个
	private String unit;
	
	private Date createTime;
	
	private Date updateTime;
	
	public Gradient(){}
	
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
	
	public Float getStart() {
		return start;
	}

	public void setStart(Float start) {
		this.start = start;
	}
	
	public Float getEnd() {
		return end;
	}

	public void setEnd(Float end) {
		this.end = end;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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