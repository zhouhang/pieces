/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BusiOrderDeposit
 * @Description: 订单划账MODLE
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public class BusiOrderDeposit implements Serializable{

	private static final long serialVersionUID = -6759818834470995219L;

	/** 订单划账ID */
	private Long id;
	
	/** 订单ID */
	private String orderId;
	
	/** 仓单ID */
	private String wlid;
	
	/** 挂牌ID */
	private String listingId;
	
	/** 卖家ID */
	private Long sellerId;
	
	/** 买家ID */
	private Long buyerId;
	
	/** 划账类型 */
	private Short depositType;
	
	/** 划账总金额 */
	private BigDecimal depositAmount;
	
	/** 订单更新时间 */
	private Date orderUpd;
	
	/** 划账状态 */
	private Short depositState;
	
	/** 卖家金额 */
	private BigDecimal sellerAmount;
	
	/** 买家金额 */
	private BigDecimal buyerAmount;
	
	/** 平台金额 */
	private BigDecimal platformAmount;
	
	/** 创建时间 */
	private Date createtime;
	
	/** 更新时间 */
	private Date updatetime;
	
	/** 划账完成时间 */
	private Date payedTime;

	/**
	 * 取得 订单划账ID
	 * @return 订单划账ID
	 */
	public Long getId() {
	    return id;
	}

	/**
	 * 設定 订单划账ID
	 * @param id 订单划账ID
	 */
	public void setId(Long id) {
	    this.id = id;
	}

	/**
	 * 取得 订单ID
	 * @return 订单ID
	 */
	public String getOrderId() {
	    return orderId;
	}

	/**
	 * 設定 订单ID
	 * @param orderId 订单ID
	 */
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}

	/**
	 * 取得 仓单ID
	 * @return 仓单ID
	 */
	public String getWlid() {
	    return wlid;
	}

	/**
	 * 設定 仓单ID
	 * @param wlid 仓单ID
	 */
	public void setWlid(String wlid) {
	    this.wlid = wlid;
	}

	/**
	 * 取得 挂牌ID
	 * @return 挂牌ID
	 */
	public String getListingId() {
	    return listingId;
	}

	/**
	 * 設定 挂牌ID
	 * @param listingId 挂牌ID
	 */
	public void setListingId(String listingId) {
	    this.listingId = listingId;
	}

	/**
	 * 取得 卖家ID
	 * @return 卖家ID
	 */
	public Long getSellerId() {
	    return sellerId;
	}

	/**
	 * 設定 卖家ID
	 * @param sellerId 卖家ID
	 */
	public void setSellerId(Long sellerId) {
	    this.sellerId = sellerId;
	}

	/**
	 * 取得 买家ID
	 * @return 买家ID
	 */
	public Long getBuyerId() {
	    return buyerId;
	}

	/**
	 * 設定 买家ID
	 * @param buyerId 买家ID
	 */
	public void setBuyerId(Long buyerId) {
	    this.buyerId = buyerId;
	}

	/**
	 * 取得 划账类型
	 * @return 划账类型
	 */
	public Short getDepositType() {
	    return depositType;
	}

	/**
	 * 設定 划账类型
	 * @param depositType 划账类型
	 */
	public void setDepositType(Short depositType) {
	    this.depositType = depositType;
	}

	/**
	 * 取得 划账总金额
	 * @return 划账总金额
	 */
	public BigDecimal getDepositAmount() {
	    return depositAmount;
	}

	/**
	 * 設定 划账总金额
	 * @param depositAmount 划账总金额
	 */
	public void setDepositAmount(BigDecimal depositAmount) {
	    this.depositAmount = depositAmount;
	}

	/**
	 * 取得 订单更新时间
	 * @return 订单更新时间
	 */
	public Date getOrderUpd() {
	    return orderUpd;
	}

	/**
	 * 設定 订单更新时间
	 * @param orderUpd 订单更新时间
	 */
	public void setOrderUpd(Date orderUpd) {
	    this.orderUpd = orderUpd;
	}

	/**
	 * 取得 划账状态
	 * @return 划账状态
	 */
	public Short getDepositState() {
	    return depositState;
	}

	/**
	 * 設定 划账状态
	 * @param depositState 划账状态
	 */
	public void setDepositState(Short depositState) {
	    this.depositState = depositState;
	}

	/**
	 * 取得 卖家金额
	 * @return 卖家金额
	 */
	public BigDecimal getSellerAmount() {
	    return sellerAmount;
	}

	/**
	 * 設定 卖家金额
	 * @param sellerAmount 卖家金额
	 */
	public void setSellerAmount(BigDecimal sellerAmount) {
	    this.sellerAmount = sellerAmount;
	}

	/**
	 * 取得 买家金额
	 * @return 买家金额
	 */
	public BigDecimal getBuyerAmount() {
	    return buyerAmount;
	}

	/**
	 * 設定 买家金额
	 * @param buyerAmount 买家金额
	 */
	public void setBuyerAmount(BigDecimal buyerAmount) {
	    this.buyerAmount = buyerAmount;
	}

	/**
	 * 取得 平台金额
	 * @return 平台金额
	 */
	public BigDecimal getPlatformAmount() {
	    return platformAmount;
	}

	/**
	 * 設定 平台金额
	 * @param platformAmount 平台金额
	 */
	public void setPlatformAmount(BigDecimal platformAmount) {
	    this.platformAmount = platformAmount;
	}

	/**
	 * 取得 创建时间
	 * @return 创建时间
	 */
	public Date getCreatetime() {
	    return createtime;
	}

	/**
	 * 設定 创建时间
	 * @param createtime 创建时间
	 */
	public void setCreatetime(Date createtime) {
	    this.createtime = createtime;
	}

	/**
	 * 取得 更新时间
	 * @return 更新时间
	 */
	public Date getUpdatetime() {
	    return updatetime;
	}

	/**
	 * 設定 更新时间
	 * @param updatetime 更新时间
	 */
	public void setUpdatetime(Date updatetime) {
	    this.updatetime = updatetime;
	}

	/**
	 * 取得 划账完成时间
	 * @return 划账完成时间
	 */
	public Date getPayedTime() {
	    return payedTime;
	}

	/**
	 * 設定 划账完成时间
	 * @param payedTime 划账完成时间
	 */
	public void setPayedTime(Date payedTime) {
	    this.payedTime = payedTime;
	}
}
