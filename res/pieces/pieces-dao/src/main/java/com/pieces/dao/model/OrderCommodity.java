package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class OrderCommodity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private String spec;
	
	private String level;
	
	private String originOf;
	
	private Date expectDate;
	
	private Integer amount;
	
	private Double price;
	
	private Double subtotal;
	
	private Integer enquiryCommodityId;
	
	private Integer orderId;
	
	public OrderCommodity(){}
	
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
	
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getOriginOf() {
		return originOf;
	}

	public void setOriginOf(String originOf) {
		this.originOf = originOf;
	}
	
	public Date getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(Date expectDate) {
		this.expectDate = expectDate;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	public Integer getEnquiryCommodityId() {
		return enquiryCommodityId;
	}

	public void setEnquiryCommodityId(Integer enquiryCommodityId) {
		this.enquiryCommodityId = enquiryCommodityId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}