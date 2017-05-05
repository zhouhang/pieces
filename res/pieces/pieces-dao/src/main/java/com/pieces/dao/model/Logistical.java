package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 物流信息表
 */
public class Logistical  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	// 订单Id
	private Integer orderId;

	// 快递单号
	private String code;
	
	//发货日期
	private Date shipDate;
	
	//商品总数
	private Integer total;
	
	//发货商品数
	private Integer shipNumber;

	// 备注时间
	private String remark;
	
	//创建时间
	private Date createDate;

	//配送方式 1快递 2自提 3货运部发货
	private Integer type;

	//预计到货时间 或者提货时间
	private Date receivingDate;

	//司机姓名
	private String driverName;

	//联系电话
	private String driverTel;

	//提货地点
	private String pickUp;

	//快递公司编码
	private String companyCode;

	// 管理员Id
	private Integer memId;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getReceivingDate() {
		return receivingDate;
	}

	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}
}