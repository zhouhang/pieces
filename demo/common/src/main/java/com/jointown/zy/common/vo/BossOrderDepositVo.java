/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BossOrderDepositVo
 * @Description: 订单划账列表显示VO
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public class BossOrderDepositVo implements Serializable{

	private static final long serialVersionUID = -2149141122951062923L;

	/** 订单编号 */
	private String orderId;
	
	/** 商品名称 */
	private String title;
	
	/** 买家名称 */
	private String buyerName;
	
	/** 卖家名称 */
	private String sellerName;
	
	/** 状态 */
	private String depositState;
	
	/** 划账总金额 */
	private BigDecimal depositAmount;
	
	/** 卖家金额 */
	private BigDecimal sellerAmount;
	
	/** 买家金额 */
	private BigDecimal buyerAmount;
	
	/** 平台金额 */
	private BigDecimal platformAmount;
	
	/** 订单时间 */
	private Date orderUpd;
	
	/**
	 * add by Mr.song 2015.7.2 17:50
	 * 划账完成时间
	 */
	private Date payedTime;
	
	/**
	 * add by Mr.song 2015.7.2 17:50
	 * 操作人
	 */
	private String operator;
	
	/**
	 * add by fanyuna 2015.07.30 增加订单买家、卖家业务员名称 start
	 */
	private String buyerSalesmanName;
	
	private String sellerSalesmanName;
	
	/** 应收金额 */
	private BigDecimal totalAmount;
	
	
	
	public String getBuyerSalesmanName() {
		return buyerSalesmanName;
	}

	public void setBuyerSalesmanName(String buyerSalesmanName) {
		this.buyerSalesmanName = buyerSalesmanName;
	}

	public String getSellerSalesmanName() {
		return sellerSalesmanName;
	}

	public void setSellerSalesmanName(String sellerSalesmanName) {
		this.sellerSalesmanName = sellerSalesmanName;
	}

	
	/**
	 * add by fanyuna 2015.07.30 增加订单买家、卖家业务员名称 end 
	 */

	/**
	 * 获取订单编号
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
	 * 获取商品名称
	 * @return 商品名称
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * 设定商品名称
	 * @param title 商品名称
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 获取买家名称
	 * @return 买家名称
	 */
	public String getBuyerName() {
	    return buyerName;
	}

	/**
	 * 设定买家名称
	 * @param buyerName 买家名称
	 */
	public void setBuyerName(String buyerName) {
	    this.buyerName = buyerName;
	}

	/**
	 * 获取卖家名称
	 * @return 卖家名称
	 */
	public String getSellerName() {
	    return sellerName;
	}

	/**
	 * 设定卖家名称
	 * @param sellerName 卖家名称
	 */
	public void setSellerName(String sellerName) {
	    this.sellerName = sellerName;
	}

	/**
	 * 获取状态
	 * @return 状态
	 */
	public String getDepositState() {
	    return depositState;
	}

	/**
	 * 设定状态
	 * @param depositState 状态
	 */
	public void setDepositState(String depositState) {
	    this.depositState = depositState;
	}

	/**
	 * 获取划账总金额
	 * @return 划账总金额
	 */
	public BigDecimal getDepositAmount() {
	    return depositAmount;
	}

	/**
	 * 设定划账总金额
	 * @param depositAmount 划账总金额
	 */
	public void setDepositAmount(BigDecimal depositAmount) {
	    this.depositAmount = depositAmount;
	}

	/**
	 * 获取卖家金额
	 * @return 卖家金额
	 */
	public BigDecimal getSellerAmount() {
	    return sellerAmount;
	}

	/**
	 * 设定卖家金额
	 * @param sellerAmount 卖家金额
	 */
	public void setSellerAmount(BigDecimal sellerAmount) {
	    this.sellerAmount = sellerAmount;
	}

	/**
	 * 获取买家金额
	 * @return 买家金额
	 */
	public BigDecimal getBuyerAmount() {
	    return buyerAmount;
	}

	/**
	 * 设定买家金额
	 * @param buyerAmount 买家金额
	 */
	public void setBuyerAmount(BigDecimal buyerAmount) {
	    this.buyerAmount = buyerAmount;
	}

	/**
	 * 获取平台金额
	 * @return 平台金额
	 */
	public BigDecimal getPlatformAmount() {
	    return platformAmount;
	}

	/**
	 * 设定平台金额
	 * @param platformAmount 平台金额
	 */
	public void setPlatformAmount(BigDecimal platformAmount) {
	    this.platformAmount = platformAmount;
	}

	/**
	 * 获取订单时间
	 * @return 订单时间
	 */
	public Date getOrderUpd() {
	    return orderUpd;
	}

	/**
	 * 设定订单时间
	 * @param orderUpd 订单时间
	 */
	public void setOrderUpd(Date orderUpd) {
	    this.orderUpd = orderUpd;
	}
	
	/**
	 * 获取操作人
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作人
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取划账时间
	 * @return the operate
	 */
	public Date getPayedTime() {
		return payedTime;
	}

	/**
	 * 设置划账时间
	 * @param payedTime the payedTime to set
	 */
	public void setPayedTime(Date payedTime) {
		this.payedTime = payedTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
