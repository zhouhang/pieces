package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class AccountBill  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//账单编号
	private String code;
	
	//订单号
	private Integer orderId;
	
	//应付
	private Float amountsPayable;
	
	//已付
	private Float alreadyPayable;
	
	//未付
	private Float unPayable;
	
	//状态(-1:拒绝,0:s申请中,1:未完结,2:已完结)
	private String status;
	
	//还款时间
	private Date repayTime;
	
	private Date createDate;
	
	public AccountBill(){}
	
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
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Float getAmountsPayable() {
		return amountsPayable;
	}

	public void setAmountsPayable(Float amountsPayable) {
		this.amountsPayable = amountsPayable;
	}
	
	public Float getAlreadyPayable() {
		return alreadyPayable;
	}

	public void setAlreadyPayable(Float alreadyPayable) {
		this.alreadyPayable = alreadyPayable;
	}
	
	public Float getUnPayable() {
		return unPayable;
	}

	public void setUnPayable(Float unPayable) {
		this.unPayable = unPayable;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}