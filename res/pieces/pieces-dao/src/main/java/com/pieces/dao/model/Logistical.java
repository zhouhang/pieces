package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Logistical  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer orderId;
	
	private String code;
	
	//发货日期
	private Date shipDate;
	
	//商品总数
	private Integer total;
	
	//发货商品数
	private Integer shipNumber;
	
	private String remark;
	
	//创建时间
	private Date createDate;
	
	public Logistical(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(Integer shipNumber) {
		this.shipNumber = shipNumber;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}