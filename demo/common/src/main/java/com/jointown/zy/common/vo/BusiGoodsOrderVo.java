package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BusiGoodsOrderVo implements Serializable{

	private static final long serialVersionUID = 8936582354848597914L;
	
	/** 订单ID */
	private String orderid;
	
	/** 购买用户 */
	private String buyerName;
	
	/** 购买数量 */
	private BigDecimal buyAmount;
	
	/** 单价 */
	private String unitprice;
	
	/** 计量单位 */
	private String unitName;
	
	/** 购买申请时间 */
	private String buyTime;
	
	/** 状态 */
	private String orderState;

	/**
	 * 取得订单ID
	 * @return 订单ID
	 */
	public String getOrderid() {
	    return orderid;
	}

	/**
	 * 设定订单ID
	 * @param orderid 订单ID
	 */
	public void setOrderid(String orderid) {
	    this.orderid = orderid;
	}

	/**
	 * 取得购买用户
	 * @return 购买用户
	 */
	public String getBuyerName() {
	    return buyerName;
	}

	/**
	 * 设定购买用户
	 * @param buyerName 购买用户
	 */
	public void setBuyerName(String buyerName) {
	    this.buyerName = buyerName;
	}

	/**
	 * 取得购买数量
	 * @return 购买数量
	 */
	public BigDecimal getBuyAmount() {
	    return buyAmount;
	}

	/**
	 * 设定购买数量
	 * @param buyAmount 购买数量
	 */
	public void setBuyAmount(BigDecimal buyAmount) {
	    this.buyAmount = buyAmount;
	}

	/**
	 * 取得单价
	 * @return 单价
	 */
	public String getUnitprice() {
	    return unitprice;
	}

	/**
	 * 设定单价
	 * @param unitprice 单价
	 */
	public void setUnitprice(String unitprice) {
	    this.unitprice = unitprice;
	}

	/**
	 * 取得计量单位
	 * @return 计量单位
	 */
	public String getUnitName() {
	    return unitName;
	}

	/**
	 * 设定计量单位
	 * @param unitName 计量单位
	 */
	public void setUnitName(String unitName) {
	    this.unitName = unitName;
	}

	/**
	 * 取得购买申请时间
	 * @return 购买申请时间
	 */
	public String getBuyTime() {
	    return buyTime;
	}

	/**
	 * 设定购买申请时间
	 * @param buyTime 购买申请时间
	 */
	public void setBuyTime(String buyTime) {
	    this.buyTime = buyTime;
	}

	/**
	 * 取得状态
	 * @return 状态
	 */
	public String getOrderState() {
	    return orderState;
	}

	/**
	 * 设定状态
	 * @param orderState 状态
	 */
	public void setOrderState(String orderState) {
	    this.orderState = orderState;
	}

}
