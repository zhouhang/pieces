package com.jointown.zy.common.model;

import java.math.BigDecimal;

/**
 * 划账信息
 * @author zhouji
 * @date 2015年5月15日 下午3:51:24
 */
public class RemitAccountInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//交易订单号
	private String payOrder;
	//平台ID(默认为0)
	private Long platformId;
	//平台划账金额
	private BigDecimal platformAmount;
	//卖家ID
	private Long sellerId;
	//卖家划账金额
	private BigDecimal sellerAmount;
	//买家ID
	private Long buyerId;
	//买家划账金额
	private BigDecimal buyerAmount;
	//划账总金额 
	private BigDecimal depositAmount;
	//分润类型
	private int remit_type;
	//操作人
	private Long createrid;
	//来源系统
	private int source_sys;
	//IP
	private String client_ip;
	private String signdata;
	public RemitAccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RemitAccountInfo(String payOrder, Long platformId,
			BigDecimal platformAmount, Long sellerId, BigDecimal sellerAmount,
			Long buyerId, BigDecimal buyerAmount, BigDecimal depositAmount,
			int remit_type, Long createrid, int source_sys, String client_ip,
			String signdata) {
		super();
		this.payOrder = payOrder;
		this.platformId = platformId;
		this.platformAmount = platformAmount;
		this.sellerId = sellerId;
		this.sellerAmount = sellerAmount;
		this.buyerId = buyerId;
		this.buyerAmount = buyerAmount;
		this.depositAmount = depositAmount;
		this.remit_type = remit_type;
		this.createrid = createrid;
		this.source_sys = source_sys;
		this.client_ip = client_ip;
		this.signdata = signdata;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
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
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getRemit_type() {
		return remit_type;
	}
	public void setRemit_type(int remit_type) {
		this.remit_type = remit_type;
	}
	public Long getCreaterid() {
		return createrid;
	}
	public void setCreaterid(Long createrid) {
		this.createrid = createrid;
	}
	public int getSource_sys() {
		return source_sys;
	}
	public void setSource_sys(int source_sys) {
		this.source_sys = source_sys;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
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
	
	
}
