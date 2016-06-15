/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: BossOrderDepositQueryDto
 * @Description: 订单划账查询DTO
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public class BossOrderDepositQueryDto extends BaseDto{

	private static final long serialVersionUID = 4165366536416023706L;
	
	/** 订单编号 */
	private String orderId;
	
	/** 买家名称 */
	private String buyerName;
	
	/** 卖家名称 */
	private String sellerName;
	
	/** 状态 */
	private String depositState;
	
	/** 划账类型 */
	private String depositType;
	
	/** 订单时间（起） */
	private String orderDateStart;
	
	/** 订单时间（止） */
	private String orderDateEnd;
	
	//add by fanyuna 2015.07.30  业务员名称 
	
	private String salesmanName;
	
	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

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
	 * 获取划账类型
	 * @return 划账类型
	 */
	public String getDepositType() {
	    return depositType;
	}

	/**
	 * 设定划账类型
	 * @param depositType 划账类型
	 */
	public void setDepositType(String depositType) {
	    this.depositType = depositType;
	}

	/**
	 * 获取订单时间（起）
	 * @return 订单时间（起）
	 */
	public String getOrderDateStart() {
	    return orderDateStart;
	}

	/**
	 * 设定订单时间（起）
	 * @param orderDateStart 订单时间（起）
	 */
	public void setOrderDateStart(String orderDateStart) {
	    this.orderDateStart = orderDateStart;
	}

	/**
	 * 获取订单时间（止）
	 * @return 订单时间（止）
	 */
	public String getOrderDateEnd() {
	    return orderDateEnd;
	}

	/**
	 * 设定订单时间（止）
	 * @param orderDateEnd 订单时间（止）
	 */
	public void setOrderDateEnd(String orderDateEnd) {
	    this.orderDateEnd = orderDateEnd;
	}

}
