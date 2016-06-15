package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 品种账务统计
 * 
 * @ClassName:BreedAccountModel
 * @author:Calvin.Wangh
 * @date:2015-10-8下午2:05:30
 * @version V1.0
 * @Description:
 */
public class BreedAccountModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String breedId;
	// 品种编码
	private String breedCode;
	// 品种
	private String breedName;
	// 单位
	private String unit;
	// 仓单总量
	private String whlistTotal;
	// 入仓用户
	private String whlistUserAmount;
	// 仓单笔数
	private String whlistAmount;
	// 挂牌总量
	private String listingTotal;
	// 挂牌用户
	private String listingUserAmount;
	// 挂牌笔数
	private String listingAmount;
	// 交易总量
	private String orderTotal;
	// 交易总金额
	private BigDecimal orderTotalMoney;
	// 交易用户
	private String orderUserAmount;
	// 交易笔数
	private String orderAmount;

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWhlistTotal() {
		return whlistTotal;
	}

	public void setWhlistTotal(String whlistTotal) {
		this.whlistTotal = whlistTotal;
	}

	public String getWhlistUserAmount() {
		return whlistUserAmount;
	}

	public void setWhlistUserAmount(String whlistUserAmount) {
		this.whlistUserAmount = whlistUserAmount;
	}

	public String getWhlistAmount() {
		return whlistAmount;
	}

	public void setWhlistAmount(String whlistAmount) {
		this.whlistAmount = whlistAmount;
	}

	public String getListingTotal() {
		return listingTotal;
	}

	public void setListingTotal(String listingTotal) {
		this.listingTotal = listingTotal;
	}

	public String getListingUserAmount() {
		return listingUserAmount;
	}

	public void setListingUserAmount(String listingUserAmount) {
		this.listingUserAmount = listingUserAmount;
	}

	public String getListingAmount() {
		return listingAmount;
	}

	public void setListingAmount(String listingAmount) {
		this.listingAmount = listingAmount;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public BigDecimal getOrderTotalMoney() {
		return orderTotalMoney;
	}

	public void setOrderTotalMoney(BigDecimal orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
	}

	public String getOrderUserAmount() {
		return orderUserAmount;
	}

	public void setOrderUserAmount(String orderUserAmount) {
		this.orderUserAmount = orderUserAmount;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getBreedCode() {
		return breedCode;
	}

	public void setBreedCode(String breedCode) {
		this.breedCode = breedCode;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

}
