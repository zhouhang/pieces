package com.jointown.zy.common.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PayVoucherAddDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5869731346171134766L;
	
	/**系统来源*/
	private String sourceSys;
	/**付款类型（保证金、尾款、全款）*/
	private String amtType;
	/**交易订单号*/
	private String orderId;
	/**收款人账号*/
	private String payeeAccount;
	/**收款人名称*/
	private String payeeName;
	/**付款人账号*/
	private String payerAccount;
	/**付款人名称*/
	private String payerName;
	/**付款金额*/
	private String amount;
	/**支付流水号*/
	private String payFlowId;
	/**支付渠道*/
	private String payChannel;
	/**付款时间*/
	private String payDate;
	/**付款凭证*/
	private String payVoucher;
	/**付款状态（支付成功、支付失败）*/
	private String payStatus;
	public String getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}
	public String getAmtType() {
		return amtType;
	}
	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayFlowId() {
		return payFlowId;
	}
	public void setPayFlowId(String payFlowId) {
		this.payFlowId = payFlowId;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayVoucher() {
		return payVoucher;
	}
	public void setPayVoucher(String payVoucher) {
		this.payVoucher = payVoucher;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

}
