package com.pieces.dao.model;

import java.io.Serializable;


/**
 * 物流商品表
 */
public class LogisticalCommodity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//物流id
	private Integer logisticalId;
	
	//订单商品id
	private Integer orderCommodityId;
	
	//数量
	private Integer amount;
	
	public LogisticalCommodity(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getLogisticalId() {
		return logisticalId;
	}

	public void setLogisticalId(Integer logisticalId) {
		this.logisticalId = logisticalId;
	}
	
	public Integer getOrderCommodityId() {
		return orderCommodityId;
	}

	public void setOrderCommodityId(Integer orderCommodityId) {
		this.orderCommodityId = orderCommodityId;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}