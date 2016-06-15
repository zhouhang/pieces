/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dto;

import java.math.BigDecimal;

/**
 * @ClassName: BossUpdateAmountDto
 * @Description: 后台订单修改数量用DTO
 * @Author: 赵航
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public class BossUpdateAmountDto extends BaseDto{

	private static final long serialVersionUID = -677379782842253268L;

	/** 订单编号 */
	private String orderId;
	
	/** 订单单价 */
	private String unitPrice;
	
	/** 订单成交数量 */
	private String volume;
	
	/** 订单总价（变更后） */
	private BigDecimal totalPrice;

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
	 * 获取订单单价
	 * @return 订单单价
	 */
	public String getUnitPrice() {
	    return unitPrice;
	}

	/**
	 * 设定订单单价
	 * @param unitPrice 订单单价
	 */
	public void setUnitPrice(String unitPrice) {
	    this.unitPrice = unitPrice;
	}


	/**
	 * 获取订单成交数量
	 * @return 订单成交数量
	 */
	public String getVolume() {
	    return volume;
	}

	/**
	 * 设定订单成交数量
	 * @param volume 订单成交数量
	 */
	public void setVolume(String volume) {
	    this.volume = volume;
	}

	/**
	 * 获取订单总价（变更后）
	 * @return 订单总价（变更后）
	 */
	public BigDecimal getTotalPrice() {
	    return totalPrice;
	}

	/**
	 * 设定订单总价（变更后）
	 * @param totalPrice 订单总价（变更后）
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
	    this.totalPrice = totalPrice;
	}
}
