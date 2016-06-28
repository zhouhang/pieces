package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceReportModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/********************************* 订单信息 *********************************/
	// 订单号
	private String orderId;
	// 订单状态
	private String orderState;
	// 挂牌id
	private String listingid;
	// 仓单id
	private String wlid;
	// 品种名称
	private String breedName;
	// 品种编码
	private String breedCode;
	// 数量
	private BigDecimal amount;
	// 成交数量
	private BigDecimal volume;
	// 单位
	private String unit;
	// 单价
	private BigDecimal unitPrice;
	// 成交总价
	private BigDecimal totalPrice;

	/********************************* 买家信息 *********************************/
	// 付款人账号
	private String buyerCode;
	// 付款人名称
	private String buyerName;
	// 付款人联系方式
	private String buyerMobile;
	// 流水号
	private String buyerFlowId;
	// 支出类型
	private Integer buyerType;
	// 支出金额
	private BigDecimal buyerAmount;
	// 支出时间
	private Date buyerPayTime;
	// 支出渠道
	private Integer buyerPayChannel;

	/********************************* 卖家信息 *********************************/
	// 收款人账号
	private String sellerCode;
	// 收款人名称
	private String sellerName;
	// 收款人联系方式
	private String sellerMobile;
	// 流水号
	private String sellerFlowId;
	// 收入类型
	private Integer sellerType;
	// 收入金额
	private BigDecimal sellerAmount;
	// 收入时间
	private Date sellerPayTime;
	// 收入渠道
	private Integer sellerPayChannel;
	/********************************* 平台信息 *********************************/
	// 平台流水号
	private String platformFlowId;
	// 平台收入类型
	private Integer platformType;
	// 平台收入金额
	private BigDecimal platformAmount;
	// 平台收入时间
	private Date platformPayTime;
	// 平台收入渠道
	private Integer platformPayChannel;

	/********************************* 手续费 *********************************/
	private BigDecimal handlingCharge;

	/********************************* 买方业务人员 *********************************/
	private String buyerOrg;
	private String buyerSalesMan;
	/********************************* 卖方业务人员 *********************************/
	private String sellerOrg;
	private String sellerSalesMan;
	/********************************* 资金操作人 *********************************/
	private String follower;
	private String accountant;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getListingid() {
		return listingid;
	}

	public void setListingid(String listingid) {
		this.listingid = listingid;
	}

	public String getWlid() {
		return wlid;
	}

	public void setWlid(String wlid) {
		this.wlid = wlid;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getBreedCode() {
		return breedCode;
	}

	public void setBreedCode(String breedCode) {
		this.breedCode = breedCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerFlowId() {
		return buyerFlowId;
	}

	public void setBuyerFlowId(String buyerFlowId) {
		this.buyerFlowId = buyerFlowId;
	}

	public Integer getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(Integer buyerType) {
		this.buyerType = buyerType;
	}

	public BigDecimal getBuyerAmount() {
		return buyerAmount;
	}

	public void setBuyerAmount(BigDecimal buyerAmount) {
		this.buyerAmount = buyerAmount;
	}

	public Date getBuyerPayTime() {
		return buyerPayTime;
	}

	public void setBuyerPayTime(Date buyerPayTime) {
		this.buyerPayTime = buyerPayTime;
	}

	public Integer getBuyerPayChannel() {
		return buyerPayChannel;
	}

	public void setBuyerPayChannel(Integer buyerPayChannel) {
		this.buyerPayChannel = buyerPayChannel;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerFlowId() {
		return sellerFlowId;
	}

	public void setSellerFlowId(String sellerFlowId) {
		this.sellerFlowId = sellerFlowId;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public BigDecimal getSellerAmount() {
		return sellerAmount;
	}

	public void setSellerAmount(BigDecimal sellerAmount) {
		this.sellerAmount = sellerAmount;
	}

	public Date getSellerPayTime() {
		return sellerPayTime;
	}

	public void setSellerPayTime(Date sellerPayTime) {
		this.sellerPayTime = sellerPayTime;
	}

	public Integer getSellerPayChannel() {
		return sellerPayChannel;
	}

	public void setSellerPayChannel(Integer sellerPayChannel) {
		this.sellerPayChannel = sellerPayChannel;
	}

	public String getPlatformFlowId() {
		return platformFlowId;
	}

	public void setPlatformFlowId(String platformFlowId) {
		this.platformFlowId = platformFlowId;
	}

	public Integer getPlatformType() {
		return platformType;
	}

	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

	public BigDecimal getPlatformAmount() {
		return platformAmount;
	}

	public void setPlatformAmount(BigDecimal platformAmount) {
		this.platformAmount = platformAmount;
	}

	public Date getPlatformPayTime() {
		return platformPayTime;
	}

	public void setPlatformPayTime(Date platformPayTime) {
		this.platformPayTime = platformPayTime;
	}

	public Integer getPlatformPayChannel() {
		return platformPayChannel;
	}

	public void setPlatformPayChannel(Integer platformPayChannel) {
		this.platformPayChannel = platformPayChannel;
	}

	public BigDecimal getHandlingCharge() {
		return handlingCharge;
	}

	public void setHandlingCharge(BigDecimal handlingCharge) {
		this.handlingCharge = handlingCharge;
	}

	public String getBuyerOrg() {
		return buyerOrg;
	}

	public void setBuyerOrg(String buyerOrg) {
		this.buyerOrg = buyerOrg;
	}

	public String getBuyerSalesMan() {
		return buyerSalesMan;
	}

	public void setBuyerSalesMan(String buyerSalesMan) {
		this.buyerSalesMan = buyerSalesMan;
	}

	public String getSellerOrg() {
		return sellerOrg;
	}

	public void setSellerOrg(String sellerOrg) {
		this.sellerOrg = sellerOrg;
	}

	public String getSellerSalesMan() {
		return sellerSalesMan;
	}

	public void setSellerSalesMan(String sellerSalesMan) {
		this.sellerSalesMan = sellerSalesMan;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getAccountant() {
		return accountant;
	}

	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	public String getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

}
