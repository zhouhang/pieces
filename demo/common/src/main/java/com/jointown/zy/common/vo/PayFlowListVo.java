package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台 资金流水
 * @author Calvin.Wang
 *
 */
public class PayFlowListVo implements Serializable {
	
	private static final long serialVersionUID = 1867997757235643028L;
	
	/**
	 * 付款人Id
	 */
	private Integer payerId;
	
	/**
	 * 收款人Id
	 */
	private Integer payeeId;
	
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
	 * 金额
	 */
	private BigDecimal amount;
	
	/**
	 * 手续费
	 */
	private BigDecimal handlingFee;
	/**
	 * 应收金额
	 */
	private BigDecimal receivable;
	
	/**创建时间*/
	private Date createTime;
	/**
	 * 支付时间
	 */
	private Date payDate;
	/**
	 * 类型
	 */
	private String payType;
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
	 * 支付频道
	 */
	private int payChannel;
	
	/**支付凭证*/
	private String payVoucher;
	/**确认者ID*/
	private int confirmorId;
	/**确认者账号*/
	private String confirmor;
	/**确认时间*/
	private Date confirmTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getConfirmor() {
		return confirmor;
	}
	public void setConfirmor(String confirmor) {
		this.confirmor = confirmor;
	}
	public String getPayVoucher() {
		return payVoucher;
	}
	public void setPayVoucher(String payVoucher) {
		this.payVoucher = payVoucher;
	}
	public int getConfirmorId() {
		return confirmorId;
	}
	public void setConfirmorId(int confirmorId) {
		this.confirmorId = confirmorId;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
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
	public BigDecimal getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(BigDecimal handlingFee) {
		this.handlingFee = handlingFee;
	}
	public BigDecimal getReceivable() {
		return receivable;
	}
	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable;
	}
	public Integer getPayerId() {
		return payerId;
	}
	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}
	public Integer getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}
	public int getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}
	
}
