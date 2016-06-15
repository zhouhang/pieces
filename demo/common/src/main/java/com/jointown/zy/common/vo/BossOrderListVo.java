/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BossOrderListVo
 * @Description: 后台-订单查询列表VO
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public class BossOrderListVo implements Serializable{

	private static final long serialVersionUID = 2725131713678993644L;
	
	/** 订单编号 */
	private String orderId;
	
	/** 挂牌编号 */
	private String listingId;
	
	/** 订单总价 */
	private BigDecimal totalPrice;
	
	/** 订单数量 */
	private BigDecimal amount;
	
	/** 实际数量 */
	private BigDecimal volume;
	
	/** 订单单价 */
	private BigDecimal unitPrice;
	
	/** 实际付款 */
	private BigDecimal actualPayment;
	
	/** 计量单位 */
	private String wlunit;
	
	/** 买家用户名 */
	private String buyerName;
	
	/** 买家真实名称 */
	private String buyerRealName;
	
	/** 买家联系方式 */
	private String buyerMobile;
	
	/** 卖家用户名 */
	private String salerName;
	
	/** 卖家真实名称 */
	private String salerRealName;
	
	/** 卖家联系方式 */
	private String salerMobile;
	
	/** 摘牌时间 */
	private Date orderDate;
	
	/** 订单状态 */
	private String orderState;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 买方业务员名称 */
	private String buyerSalemanName;
	
	/** 卖方业务员名称 */
	private String sellerSalemanName;
	
	/** 订单过期时间 */
	private Date expireTime;
	
	/** 保证金 */
	private BigDecimal deposit;
	
	/** 标题 */
	private String title;
	
	/** 申诉状态 */
	private String examineState;
	
	/** 订单类型 */
	private String orderType;
	
	/** 账期订单结束时间 */
	private Date endTime;

	/**品种*/
  	private String breedname;
	/**
	 * 取得订单编号
	 * @return 订单编号
	 */
	public String getOrderId() {
	    return orderId;
	}

	/**
	 * 设定订单编号
	 * @param orderId 订单编号
	 */
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}

	/**
	 * 取得挂牌编号
	 * @return 挂牌编号
	 */
	public String getListingId() {
	    return listingId;
	}

	/**
	 * 设定挂牌编号
	 * @param listingId 挂牌编号
	 */
	public void setListingId(String listingId) {
	    this.listingId = listingId;
	}

	/**
	 * 取得订单总价
	 * @return 订单总价
	 */
	public BigDecimal getTotalPrice() {
	    return totalPrice;
	}

	/**
	 * 设定订单总价
	 * @param totalPrice 订单总价
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
	    this.totalPrice = totalPrice;
	}

	/**
	 * 取得订单数量
	 * @return 订单数量
	 */
	public BigDecimal getAmount() {
	    return amount;
	}

	/**
	 * 设定订单数量
	 * @param amount 订单数量
	 */
	public void setAmount(BigDecimal amount) {
	    this.amount = amount;
	}

	/**
	 * 取得实际数量
	 * @return 实际数量
	 */
	public BigDecimal getVolume() {
	    return volume;
	}

	/**
	 * 设定实际数量
	 * @param volume 实际数量
	 */
	public void setVolume(BigDecimal volume) {
	    this.volume = volume;
	}

	/**
	 * 取得订单单价
	 * @return 订单单价
	 */
	public BigDecimal getUnitPrice() {
	    return unitPrice;
	}

	/**
	 * 设定订单单价
	 * @param unitPrice 订单单价
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
	    this.unitPrice = unitPrice;
	}

	/**
	 * 取得实际付款
	 * @return 实际付款
	 */
	public BigDecimal getActualPayment() {
	    return actualPayment;
	}

	/**
	 * 设定实际付款
	 * @param actualPayment 实际付款
	 */
	public void setActualPayment(BigDecimal actualPayment) {
	    this.actualPayment = actualPayment;
	}

	/**
	 * 取得计量单位
	 * @return 计量单位
	 */
	public String getWlunit() {
	    return wlunit;
	}

	/**
	 * 设定计量单位
	 * @param wlunit 计量单位
	 */
	public void setWlunit(String wlunit) {
	    this.wlunit = wlunit;
	}

	/**
	 * 取得买家用户名
	 * @return 买家用户名
	 */
	public String getBuyerName() {
	    return buyerName;
	}

	/**
	 * 设定买家用户名
	 * @param buyerName 买家用户名
	 */
	public void setBuyerName(String buyerName) {
	    this.buyerName = buyerName;
	}

	/**
	 * 取得买家真实名称
	 * @return 买家真实名称
	 */
	public String getBuyerRealName() {
	    return buyerRealName;
	}

	/**
	 * 设定买家真实名称
	 * @param buyerRealName 买家真实名称
	 */
	public void setBuyerRealName(String buyerRealName) {
	    this.buyerRealName = buyerRealName;
	}

	/**
	 * 取得买家联系方式
	 * @return 买家联系方式
	 */
	public String getBuyerMobile() {
	    return buyerMobile;
	}

	/**
	 * 设定买家联系方式
	 * @param buyerMobile 买家联系方式
	 */
	public void setBuyerMobile(String buyerMobile) {
	    this.buyerMobile = buyerMobile;
	}

	/**
	 * 取得卖家用户名
	 * @return 卖家用户名
	 */
	public String getSalerName() {
	    return salerName;
	}

	/**
	 * 设定卖家用户名
	 * @param salerName 卖家用户名
	 */
	public void setSalerName(String salerName) {
	    this.salerName = salerName;
	}

	/**
	 * 取得卖家真实名称
	 * @return 卖家真实名称
	 */
	public String getSalerRealName() {
	    return salerRealName;
	}

	/**
	 * 设定卖家真实名称
	 * @param salerRealName 卖家真实名称
	 */
	public void setSalerRealName(String salerRealName) {
	    this.salerRealName = salerRealName;
	}

	/**
	 * 取得卖家联系方式
	 * @return 卖家联系方式
	 */
	public String getSalerMobile() {
	    return salerMobile;
	}

	/**
	 * 设定卖家联系方式
	 * @param salerMobile 卖家联系方式
	 */
	public void setSalerMobile(String salerMobile) {
	    this.salerMobile = salerMobile;
	}

	/**
	 * 取得摘牌时间
	 * @return 摘牌时间
	 */
	public Date getOrderDate() {
	    return orderDate;
	}

	/**
	 * 设定摘牌时间
	 * @param orderDate 摘牌时间
	 */
	public void setOrderDate(Date orderDate) {
	    this.orderDate = orderDate;
	}

	/**
	 * 取得订单状态
	 * @return 订单状态
	 */
	public String getOrderState() {
	    return orderState;
	}

	/**
	 * 设定订单状态
	 * @param orderState 订单状态
	 */
	public void setOrderState(String orderState) {
	    this.orderState = orderState;
	}

	/**
	 * 取得更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
	    return updateTime;
	}

	/**
	 * 设定更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
	    this.updateTime = updateTime;
	}

	/**
	 * 取得买方业务员名称
	 * @return 买方业务员名称
	 */
	public String getBuyerSalemanName() {
	    return buyerSalemanName;
	}

	/**
	 * 设定买方业务员名称
	 * @param buyerSalemanName 买方业务员名称
	 */
	public void setBuyerSalemanName(String buyerSalemanName) {
	    this.buyerSalemanName = buyerSalemanName;
	}

	/**
	 * 取得卖方业务员名称
	 * @return 卖方业务员名称
	 */
	public String getSellerSalemanName() {
	    return sellerSalemanName;
	}

	/**
	 * 设定卖方业务员名称
	 * @param sellerSalemanName 卖方业务员名称
	 */
	public void setSellerSalemanName(String sellerSalemanName) {
	    this.sellerSalemanName = sellerSalemanName;
	}

	/**
	 * 取得订单过期时间
	 * @return 订单过期时间
	 */
	public Date getExpireTime() {
	    return expireTime;
	}

	/**
	 * 设定订单过期时间
	 * @param expireTime 订单过期时间
	 */
	public void setExpireTime(Date expireTime) {
	    this.expireTime = expireTime;
	}

	/**
	 * 取得保证金
	 * @return 保证金
	 */
	public BigDecimal getDeposit() {
	    return deposit;
	}

	/**
	 * 设定保证金
	 * @param deposit 保证金
	 */
	public void setDeposit(BigDecimal deposit) {
	    this.deposit = deposit;
	}

	/**
	 * 取得标题
	 * @return 标题
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * 设定标题
	 * @param title 标题
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 取得申诉状态
	 * @return 申诉状态
	 */
	public String getExamineState() {
	    return examineState;
	}

	/**
	 * 设定申诉状态
	 * @param examineState 申诉状态
	 */
	public void setExamineState(String examineState) {
	    this.examineState = examineState;
	}

	/**
	 * 取得订单类型
	 * @return 订单类型
	 */
	public String getOrderType() {
	    return orderType;
	}

	/**
	 * 设定订单类型
	 * @param orderType 订单类型
	 */
	public void setOrderType(String orderType) {
	    this.orderType = orderType;
	}

	/**
	 * 取得账期订单结束时间
	 * @return 账期订单结束时间
	 */
	public Date getEndTime() {
	    return endTime;
	}

	/**
	 * 设定账期订单结束时间
	 * @param endTime 账期订单结束时间
	 */
	public void setEndTime(Date endTime) {
	    this.endTime = endTime;
	}

	public String getBreedname() {
		return breedname;
	}

	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}
	
}
