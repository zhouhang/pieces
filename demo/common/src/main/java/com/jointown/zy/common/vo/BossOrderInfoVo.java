/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BossOrderInfoVo
 * @Description: 后台订单查询列表-订单详情VO
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public class BossOrderInfoVo implements Serializable{

	private static final long serialVersionUID = -1033153270966599208L;
	
	/** 挂牌编号 */
	private String listingId;
	
	/** 订单编号 */
	private String orderId;
	
	/** 摘牌时间 */
	private Date orderDate;
	
	/** 商品图片 */
	private String goodsPic;
	
	/** 商品标题 */
	private String goodsTitle;
	
	/** 订单单价 */
	private BigDecimal unitPrice;
	
	/** 订单数量 */
	private BigDecimal amount;
	
	/** 成交数量 */
	private BigDecimal volume;
	
	/** 计量单位 */
	private String wlunit;
	
	/** 订单总价 */
	private BigDecimal totalPrice;
	
	/** 订单实际付款 */
	private BigDecimal actualPayment;
	
	/** 订单状态 */
	private String orderState;

	/**
	 * 获取挂牌编号
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
	 * 获取摘牌时间
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
	 * 获取商品图片
	 * @return 商品图片
	 */
	public String getGoodsPic() {
	    return goodsPic;
	}

	/**
	 * 设定商品图片
	 * @param goodsPic 商品图片
	 */
	public void setGoodsPic(String goodsPic) {
	    this.goodsPic = goodsPic;
	}

	/**
	 * 获取商品标题
	 * @return 商品标题
	 */
	public String getGoodsTitle() {
	    return goodsTitle;
	}

	/**
	 * 设定商品标题
	 * @param goodsTitle 商品标题
	 */
	public void setGoodsTitle(String goodsTitle) {
	    this.goodsTitle = goodsTitle;
	}

	/**
	 * 获取订单单价
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
	 * 获取订单数量
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
	 * 获取成交数量
	 * @return 成交数量
	 */
	public BigDecimal getVolume() {
	    return volume;
	}

	/**
	 * 设定成交数量
	 * @param volume 成交数量
	 */
	public void setVolume(BigDecimal volume) {
	    this.volume = volume;
	}

	/**
	 * 获取计量单位
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
	 * 获取订单总价
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
	 * 获取订单实际付款
	 * @return 订单实际付款
	 */
	public BigDecimal getActualPayment() {
	    return actualPayment;
	}

	/**
	 * 设定订单实际付款
	 * @param actualPayment 订单实际付款
	 */
	public void setActualPayment(BigDecimal actualPayment) {
	    this.actualPayment = actualPayment;
	}

	/**
	 * 获取订单状态
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

}
