package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付订单对象
 * @author ldp
 * 2015-2-5
 */
public class PayOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -546274393143577962L;
	/**支付订单流水号*/
	private BigDecimal flowId;
	/**付款用户名Id*/
	private Integer userId;
	/**收款人ID*/
	private Integer recieveId;
	/**支付渠道  0-招行 1-银联B2B 2-银联代收*/
	private Integer payChannel;
	/**金额类型 0-保证金 1-尾款*/
	private Integer amtType;
	/**银行代码*/
	private String bankCode;
	/**币种*/
	private String currencyCode;
	/**交易订单号*/
	private String orderId;
	/**支付金额*/
	private BigDecimal amount;
	/**支付状态 0-未支付 1-支付成功 2-支付失败 3-处理中*/
	private Integer status;
	/**客户端IP*/
	private String clientIp;
	/**银行返回的交易索引号(交易查询流水号)*/
	private String queryId;
	/**银行返回状态码*/
	private String respCode;
	/**订单时间*/
	private Date createTime;
	/**付款时间*/
	private Date payTime;
	/**最后更新时间*/
	private Date updateTime;
	/**商品名称*/
	private String orderTitle;
	/**支付手续费（银行收取）*/
	private BigDecimal handingFee;
	/**系统标识*/
	private String sourceSys;
	
	/**支付凭证图片路径*/
	private String voucherPic;
	/**确认人ID(银行转账审核使用)*/
	private Integer confirmorId;
	/**确认时间*/
	private Date confirmTime;
	
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getVoucherPic() {
		return voucherPic;
	}
	public void setVoucherPic(String voucherPic) {
		this.voucherPic = voucherPic;
	}
	public Integer getConfirmorId() {
		return confirmorId;
	}
	public void setConfirmorId(Integer confirmorId) {
		this.confirmorId = confirmorId;
	}
	public BigDecimal getHandingFee() {
		return handingFee;
	}
	public void setHandingFee(BigDecimal handingFee) {
		this.handingFee = handingFee;
	}
	public String getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}
	public Integer getRecieveId() {
		return recieveId;
	}
	public void setRecieveId(Integer recieveId) {
		this.recieveId = recieveId;
	}
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAmtType() {
		return amtType;
	}
	public void setAmtType(Integer amtType) {
		this.amtType = amtType;
	}
	public BigDecimal getFlowId() {
		return flowId;
	}
	public void setFlowId(BigDecimal flowId) {
		this.flowId = flowId;
	}
	public Integer getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getQueryId() {
		return queryId;
	}
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
