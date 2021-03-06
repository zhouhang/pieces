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

	private Integer accountBillId;

	private Integer orderId;

	private Integer paymentId;
	
	//应付金额
	private Double amountsPayable;
	
	//实付金额
	private Double actualPayment;

	//支付保证金
	private Double deposit;
	
	//开户行
	private String payBank;
	
	//开户人
	private String payAccount;
	
	//银行卡号
	private String payBankCard;
	
	//收款银行
	private String receiveBank;
	
	//收款账户
	private String receiveAccount;
	
	//收款银行卡
	private String receiveBankCard;
	
	//付款用户
	private Integer userId;

	private Integer agentId;
	
	//支付时间
	private Date paymentTime;
	
	//0审核中，1支付成功，2支付失败
	private Integer status;

	private Integer payType;
	
	//失败原因
	private String failReason;
	
	//操作人
	private Integer memberId;
	
	//操作时间
	private Date operationTime;
	
	//创建时间
	private Date createTime;
	
	public PayRecord(){}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

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

	public Double getAmountsPayable() {
		return amountsPayable;
	}

	public void setAmountsPayable(Double amountsPayable) {
		this.amountsPayable = amountsPayable;
	}

	public Double getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(Double actualPayment) {
		this.actualPayment = actualPayment;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	
	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	
	public String getPayBankCard() {
		return payBankCard;
	}

	public void setPayBankCard(String payBankCard) {
		this.payBankCard = payBankCard;
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
	
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAccountBillId() {
		return accountBillId;
	}

	public void setAccountBillId(Integer accountBillId) {
		this.accountBillId = accountBillId;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
}