package com.jointown.zy.common.dto;

public class FinanceReportDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 订单号
	private String orderid;
	// 挂牌编号
	private String listingid;
	// 仓单编号
	private String wlid;
	// 品种名/编码
	private String breedInfo;
	// 买方
	private String buyer;
	// 卖方
	private String seller;
	// 大区
	private String org;
	// 业务员类型
	private String salesManType;
	// 业务员
	private String salesMan;
	// 变动类型
	private String payType;
	// 资金变动时间
	private String startTime;
	private String endTime;

	// 资金变动时间

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getBreedInfo() {
		return breedInfo;
	}

	public void setBreedInfo(String breedInfo) {
		this.breedInfo = breedInfo;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSalesManType() {
		return salesManType;
	}

	public void setSalesManType(String salesManType) {
		this.salesManType = salesManType;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
