package com.jointown.zy.common.dto;

import java.io.Serializable;

/**
 * 后台 资金流水
 * 
 * @author Calvin.Wang
 * 
 */
public class PayFlowListDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 1867997757235643028L;

	/**
	 * 收款人账号
	 */
	private String payeeAccount;
	/**
	 * 收款人名称
	 */
	private String payeeName;
	/**
	 * 付款人账号
	 */
	private String payerAccount;
	/**
	 * 付款人名称
	 */
	private String payerName;

	/**
	 * 支付时间
	 */
	private String payStartDate;

	private String payEndDate;

	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 流水号
	 */
	private String payFlowId;
	/**
	 * 支付状态
	 */
	private String payStatus;
	/**
	 *支付频道 
	 */
	private String payChannel;
	/**
	 * 支付金额
	 * @return
	 */
	private String amount;

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayStartDate() {
		return payStartDate;
	}

	public void setPayStartDate(String payStartDate) {
		this.payStartDate = payStartDate;
	}

	public String getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(String payEndDate) {
		this.payEndDate = payEndDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayFlowId() {
		return payFlowId;
	}

	public void setPayFlowId(String payFlowId) {
		this.payFlowId = payFlowId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
