/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BusiOrderDepositLog
 * @Description: 订单划账操作日志MODEL
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public class BusiOrderDepositLog implements Serializable{

	private static final long serialVersionUID = -7461307603091198319L;

	/** 订单划账操作ID */
	private Long id;
	
	/** 订单ID */
	private String orderId;
	
	/** 划账总金额 */
	private BigDecimal depositAmount;
	
	/** 卖家ID */
	private Long sellerId;
	
	/** 卖家金额 */
	private BigDecimal sellerAmount;
	
	/** 买家ID */
	private Long buyerId;
	
	/** 买家金额 */
	private BigDecimal buyerAmount;
	
	/** 平台金额 */
	private BigDecimal platformAmount;
	
	/** 划账类型 */
	private Short depositType;
	
	/** 操作结果 */
	private Short operationResult;
	
	/** 操作结果备注 */
	private String resultRemark;
	
	/** 操作者ID */
	private Long operattorId;
	
	/** 操作者Ip */
	private String operattorIp;
	
	/** 创建时间 */
	private Date createtime;

	/**
	 * 获取订单划账操作ID
	 * @return 订单划账操作ID
	 */
	public Long getId() {
	    return id;
	}

	/**
	 * 设定订单划账操作ID
	 * @param id 订单划账操作ID
	 */
	public void setId(Long id) {
	    this.id = id;
	}

	/**
	 * 获取订单ID
	 * @return 订单ID
	 */
	public String getOrderId() {
	    return orderId;
	}

	/**
	 * 设定订单ID
	 * @param orderId 订单ID
	 */
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
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
	 * 获取卖家ID
	 * @return 卖家ID
	 */
	public Long getSellerId() {
	    return sellerId;
	}

	/**
	 * 设定卖家ID
	 * @param sellerId 卖家ID
	 */
	public void setSellerId(Long sellerId) {
	    this.sellerId = sellerId;
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
	 * 获取买家ID
	 * @return 买家ID
	 */
	public Long getBuyerId() {
	    return buyerId;
	}

	/**
	 * 设定买家ID
	 * @param buyerId 买家ID
	 */
	public void setBuyerId(Long buyerId) {
	    this.buyerId = buyerId;
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
	 * 获取划账类型
	 * @return 划账类型
	 */
	public Short getDepositType() {
	    return depositType;
	}

	/**
	 * 设定划账类型
	 * @param depositType 划账类型
	 */
	public void setDepositType(Short depositType) {
	    this.depositType = depositType;
	}

	/**
	 * 获取操作结果
	 * @return 操作结果
	 */
	public Short getOperationResult() {
	    return operationResult;
	}

	/**
	 * 设定操作结果
	 * @param operationResult 操作结果
	 */
	public void setOperationResult(Short operationResult) {
	    this.operationResult = operationResult;
	}

	/**
	 * 获取操作结果备注
	 * @return 操作结果备注
	 */
	public String getResultRemark() {
	    return resultRemark;
	}

	/**
	 * 设定操作结果备注
	 * @param resultRemark 操作结果备注
	 */
	public void setResultRemark(String resultRemark) {
	    this.resultRemark = resultRemark;
	}

	/**
	 * 获取操作者ID
	 * @return 操作者ID
	 */
	public Long getOperattorId() {
	    return operattorId;
	}

	/**
	 * 设定操作者ID
	 * @param operattorId 操作者ID
	 */
	public void setOperattorId(Long operattorId) {
	    this.operattorId = operattorId;
	}

	/**
	 * 获取操作者Ip
	 * @return 操作者Ip
	 */
	public String getOperattorIp() {
	    return operattorIp;
	}

	/**
	 * 设定操作者Ip
	 * @param operattorIp 操作者Ip
	 */
	public void setOperattorIp(String operattorIp) {
	    this.operattorIp = operattorIp;
	}

	/**
	 * 获取创建时间
	 * @return 创建时间
	 */
	public Date getCreatetime() {
	    return createtime;
	}

	/**
	 * 设定创建时间
	 * @param createtime 创建时间
	 */
	public void setCreatetime(Date createtime) {
	    this.createtime = createtime;
	}
}
