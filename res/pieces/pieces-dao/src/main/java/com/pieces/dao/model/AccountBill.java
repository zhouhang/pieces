package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class AccountBill  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//账单编号
	private String code;


	private Integer userId;

	//订单号
	private Integer orderId;
	
	//应付
	private Double amountsPayable;
	
	//已付
	private Double alreadyPayable;

	//账单时间
	private Integer billTime;


	//未付
	private Double unPayable;
	
	//状态(-1:拒绝,0:s申请中,1:未完结,2:已完结)
	private Integer status;


	//还款时间
	private Date repayTime;

	private Integer memberId;

	private Date operateTime;

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

	public Double getAmountsPayable() {
		return amountsPayable;
	}

	public void setAmountsPayable(Double amountsPayable) {
		this.amountsPayable = amountsPayable;
	}

	public Double getAlreadyPayable() {
		return alreadyPayable;
	}

	public void setAlreadyPayable(Double alreadyPayable) {
		this.alreadyPayable = alreadyPayable;
	}

	public Double getUnPayable() {
		return unPayable;
	}

	public void setUnPayable(Double unPayable) {
		this.unPayable = unPayable;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public Integer getBillTime() {
		return billTime;
	}

	public void setBillTime(Integer billTime) {
		this.billTime = billTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
}