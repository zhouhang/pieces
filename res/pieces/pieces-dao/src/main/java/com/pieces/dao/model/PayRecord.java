package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class PayRecord  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//支付流水号
	private String payCode;
	
	//订单编号
	private String orderCode;
	
	private Integer orderId;
	
	//应付金额
	private Float amountsPayable;
	
	//实付金额
	private Float actualPayment;
	
	//开户行
	private String openBank;
	
	//开户人
	private String openAccount;
	
	//银行卡号
	private String openBankCard;
	
	private String receiveBank;
	
	private String receiveAccount;
	
	private String receiveBankCard;
	
	//付款用户
	private Integer userId;
	
	//支付时间
	private Date paymentTime;
	
	//0审核中，1支付成功，2支付失败
	private Integer status;
	
	//创建时间
	private Date createTime;
	
	public PayRecord(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	
	public Float getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(Float actualPayment) {
		this.actualPayment = actualPayment;
	}
	
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
	
	public String getOpenBankCard() {
		return openBankCard;
	}

	public void setOpenBankCard(String openBankCard) {
		this.openBankCard = openBankCard;
	}
	
	public String getReceiveBank() {
		return receiveBank;
	}

	public void setReceiveBank(String receiveBank) {
		this.receiveBank = receiveBank;
	}
	
	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}
	
	public String getReceiveBankCard() {
		return receiveBankCard;
	}

	public void setReceiveBankCard(String receiveBankCard) {
		this.receiveBankCard = receiveBankCard;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}