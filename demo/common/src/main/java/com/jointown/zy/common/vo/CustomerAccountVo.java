package com.jointown.zy.common.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @ClassName: CustomerAccountVo
 * @Description: 客户账务统计报表vo
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
public class CustomerAccountVo implements Serializable {

	/** TODO */
	private static final long serialVersionUID = -485596870141472707L;
	/**会员名*/
	private String userName;
	/**公司、真实姓名*/
	private String realName;
	/**单位*/
	private String unit;
	/**仓单总量*/
	private String wlTotal;
	/**入库总量*/
	private String inWLTotal;
	/**仓单笔数*/
	private String wlNums;
	/**仓单品种数*/
	private String wlBreeds;
	/**挂牌总量*/
	private String listingAmount;
	/**挂牌笔数*/
	private String listingNum;
	/**挂牌品种数*/
	private String listingBreedNum;
	/**销售总量*/
	private String orderTotalAmt;
	/**销售笔数*/
	private String orderNum;
	/**销售品种数*/
	private String orderBreedNum;
	/**销售金额*/
	private String orderPayment;
	/**采购总量*/
	private String purchaseAmount;
	/**采购品种数*/
	private String purchaseBreedNum;
	/**采购笔数*/
	private String purchaseOrderNum;
	/**采购金额*/
	private String purchaseOrderAmt;
	/**大区*/
	private String orgName;
	/**业务人员*/
	private String salsManName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWlTotal() {
		return wlTotal;
	}
	public void setWlTotal(String wlTotal) {
		this.wlTotal = wlTotal;
	}
	public String getInWLTotal() {
		return inWLTotal;
	}
	public void setInWLTotal(String inWLTotal) {
		this.inWLTotal = inWLTotal;
	}
	public String getWlNums() {
		return wlNums;
	}
	public void setWlNums(String wlNums) {
		this.wlNums = wlNums;
	}
	public String getWlBreeds() {
		return wlBreeds;
	}
	public void setWlBreeds(String wlBreeds) {
		this.wlBreeds = wlBreeds;
	}
	public String getListingAmount() {
		return listingAmount;
	}
	public void setListingAmount(String listingAmount) {
		this.listingAmount = listingAmount;
	}
	public String getListingNum() {
		return listingNum;
	}
	public void setListingNum(String listingNum) {
		this.listingNum = listingNum;
	}
	public String getListingBreedNum() {
		return listingBreedNum;
	}
	public void setListingBreedNum(String listingBreedNum) {
		this.listingBreedNum = listingBreedNum;
	}
	public String getOrderTotalAmt() {
		return orderTotalAmt;
	}
	public void setOrderTotalAmt(String orderTotalAmt) {
		this.orderTotalAmt = orderTotalAmt;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderBreedNum() {
		return orderBreedNum;
	}
	public void setOrderBreedNum(String orderBreedNum) {
		this.orderBreedNum = orderBreedNum;
	}
	public String getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public String getPurchaseBreedNum() {
		return purchaseBreedNum;
	}
	public void setPurchaseBreedNum(String purchaseBreedNum) {
		this.purchaseBreedNum = purchaseBreedNum;
	}
	public String getPurchaseOrderNum() {
		return purchaseOrderNum;
	}
	public void setPurchaseOrderNum(String purchaseOrderNum) {
		this.purchaseOrderNum = purchaseOrderNum;
	}
	public String getPurchaseOrderAmt() {
		return purchaseOrderAmt;
	}
	public void setPurchaseOrderAmt(String purchaseOrderAmt) {
		this.purchaseOrderAmt = purchaseOrderAmt;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSalsManName() {
		return salsManName;
	}
	public void setSalsManName(String salsManName) {
		this.salsManName = salsManName;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

}
