package com.ms.dao.model;

import java.io.Serializable;



public class SpecialCommodity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//专场id
	private Integer specialId;
	
	//商品id
	private Integer commodityId;
	
	public SpecialCommodity(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSpecialId() {
		return specialId;
	}

	public void setSpecialId(Integer specialId) {
		this.specialId = specialId;
	}
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	
}