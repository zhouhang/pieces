package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemitFlow implements Serializable {

	private static final long serialVersionUID = 1L;
	// 划账流水号(主键)
	private Long flowId;
	// 来源系统 0-交易系统
	private Integer sourcesys;
	// 交易订单号
	private String orderId;
	// 划账类型：0-订单完成分润 1-保证金处理
	private Integer remitType;
	// 划账渠道：0-招行 1-银联B2B 2-银联B2C 3-线下支付
	private Integer remitChannel;
	// 币种 CNY-人民币
	private String currencyCode;
	// 分润系数
	private String amountInfo;
	// 分润总金额
	private BigDecimal totalAmount;
	// 划账状态 0-未划账 1-划账成功 2-划账失败 3-处理中 4-划账异常
	private Integer status;
	// 客户端IP
	private String clientIp;
	// 返回码
	private String respcode;
	// 返回状态
	private Integer respstatus;
	// 失败原因
	private String respmsg;
	// 创建时间
	private Date createTime;
	// 划账完成时间
	private Date remitTime;
	// 最后更新时间
	private Date updateTime;
	// 操作人ID
	private Long createrid;
	// 卖家ID
	private Long sellerId;
	// 卖家划账金额
	private BigDecimal sellerAmount;
	// 买家ID
	private Long buyerId;
	// 卖家划账金额
	private BigDecimal buyerAmount;
	// 平台ID
	private Long platformId;
	// 平台划账金额
	private BigDecimal platformAmount;
	// 卖家收款机构名称
	private String sellerBank;
	// 卖家收款账户
	private String sellerAccount;
	// 卖家付款回单
	private String sellerVoucher;
	// 买家收款机构名称
	private String buyerBank;
	// 买家收款账户
	private String buyerAccount;
	// 买家付款回单
	private String buyerVoucher;
	// 备注
	private String memo;
	// 后台操作人
	private Long opraterId;
	// 操作人姓名
	private String userName;
	// 后台操作时间
	private Date opraterTime;
	// 解密字段无其他用处
	private String signdata;

	public RemitFlow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RemitFlow(Long flowId, Integer sourcesys, String orderId,
			Integer remitType, Integer remitChannel, String currencyCode,
			String amountInfo, BigDecimal totalAmount, Integer status,
			String clientIp, String respcode, Integer respstatus,
			String respmsg, Date createTime, Date remitTime, Date updateTime,
			Long createrid, Long sellerId, BigDecimal sellerAmount,
			Long buyerId, BigDecimal buyerAmount, Long platformId,
			BigDecimal platformAmount, String sellerBank, String sellerAccount,
			String sellerVoucher, String buyerBank, String buyerAccount,
			String buyerVoucher, String memo, Long opraterId, Date opraterTime,
			String signdata) {
		super();
		this.flowId = flowId;
		this.sourcesys = sourcesys;
		this.orderId = orderId;
		this.remitType = remitType;
		this.remitChannel = remitChannel;
		this.currencyCode = currencyCode;
		this.amountInfo = amountInfo;
		this.totalAmount = totalAmount;
		this.status = status;
		this.clientIp = clientIp;
		this.respcode = respcode;
		this.respstatus = respstatus;
		this.respmsg = respmsg;
		this.createTime = createTime;
		this.remitTime = remitTime;
		this.updateTime = updateTime;
		this.createrid = createrid;
		this.sellerId = sellerId;
		this.sellerAmount = sellerAmount;
		this.buyerId = buyerId;
		this.buyerAmount = buyerAmount;
		this.platformId = platformId;
		this.platformAmount = platformAmount;
		this.sellerBank = sellerBank;
		this.sellerAccount = sellerAccount;
		this.sellerVoucher = sellerVoucher;
		this.buyerBank = buyerBank;
		this.buyerAccount = buyerAccount;
		this.buyerVoucher = buyerVoucher;
		this.memo = memo;
		this.opraterId = opraterId;
		this.opraterTime = opraterTime;
		this.signdata = signdata;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public Integer getSourcesys() {
		return sourcesys;
	}

	public void setSourcesys(Integer sourcesys) {
		this.sourcesys = sourcesys;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getRemitType() {
		return remitType;
	}

	public void setRemitType(Integer remitType) {
		this.remitType = remitType;
	}

	public Integer getRemitChannel() {
		return remitChannel;
	}

	public void setRemitChannel(Integer remitChannel) {
		this.remitChannel = remitChannel;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getAmountInfo() {
		return amountInfo;
	}

	public void setAmountInfo(String amountInfo) {
		this.amountInfo = amountInfo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public String getRespcode() {
		return respcode;
	}

	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}

	public Integer getRespstatus() {
		return respstatus;
	}

	public void setRespstatus(Integer respstatus) {
		this.respstatus = respstatus;
	}

	public String getRespmsg() {
		return respmsg;
	}

	public void setRespmsg(String respmsg) {
		this.respmsg = respmsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRemitTime() {
		return remitTime;
	}

	public void setRemitTime(Date remitTime) {
		this.remitTime = remitTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreaterid() {
		return createrid;
	}

	public void setCreaterid(Long createrid) {
		this.createrid = createrid;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public BigDecimal getSellerAmount() {
		return sellerAmount;
	}

	public void setSellerAmount(BigDecimal sellerAmount) {
		this.sellerAmount = sellerAmount;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public BigDecimal getBuyerAmount() {
		return buyerAmount;
	}

	public void setBuyerAmount(BigDecimal buyerAmount) {
		this.buyerAmount = buyerAmount;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public BigDecimal getPlatformAmount() {
		return platformAmount;
	}

	public void setPlatformAmount(BigDecimal platformAmount) {
		this.platformAmount = platformAmount;
	}

	public String getSellerBank() {
		return sellerBank;
	}

	public void setSellerBank(String sellerBank) {
		this.sellerBank = sellerBank;
	}

	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}

	public String getSellerVoucher() {
		return sellerVoucher;
	}

	public void setSellerVoucher(String sellerVoucher) {
		this.sellerVoucher = sellerVoucher;
	}

	public String getBuyerBank() {
		return buyerBank;
	}

	public void setBuyerBank(String buyerBank) {
		this.buyerBank = buyerBank;
	}

	public String getBuyerAccount() {
		return buyerAccount;
	}

	public void setBuyerAccount(String buyerAccount) {
		this.buyerAccount = buyerAccount;
	}

	public String getBuyerVoucher() {
		return buyerVoucher;
	}

	public void setBuyerVoucher(String buyerVoucher) {
		this.buyerVoucher = buyerVoucher;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getOpraterId() {
		return opraterId;
	}

	public void setOpraterId(Long opraterId) {
		this.opraterId = opraterId;
	}

	public Date getOpraterTime() {
		return opraterTime;
	}

	public void setOpraterTime(Date opraterTime) {
		this.opraterTime = opraterTime;
	}

	public String getSigndata() {
		return signdata;
	}

	public void setSigndata(String signdata) {
		this.signdata = signdata;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
