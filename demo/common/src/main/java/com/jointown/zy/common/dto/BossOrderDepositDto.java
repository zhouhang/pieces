/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: BossOrderDepositDto
 * @Description: 执行的订单划账DTO
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public class BossOrderDepositDto extends BaseDto{

	private static final long serialVersionUID = -9031169926213359708L;

	/** 订单编号 */
	private String orderId;
	
	/** 划账总金额 */
	private String depositAmount;
	
	/** 买家金额 */
	private String buyerAmount;
	
	/** 卖家金额 */
	private String sellerAmount;
	
	/** 平台金额 */
	private String platformAmount;
	
	/** 操作者ID */
	private Long operattorId;
	
	/** 操作者IP */
	private String operattorIp;

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
	 * 获取划账总金额
	 * @return 划账总金额
	 */
	public String getDepositAmount() {
	    return depositAmount;
	}

	/**
	 * 设定划账总金额
	 * @param depositAmount 划账总金额
	 */
	public void setDepositAmount(String depositAmount) {
	    this.depositAmount = depositAmount;
	}

	/**
	 * 获取买家金额
	 * @return 买家金额
	 */
	public String getBuyerAmount() {
	    return buyerAmount;
	}

	/**
	 * 设定买家金额
	 * @param buyerAmount 买家金额
	 */
	public void setBuyerAmount(String buyerAmount) {
	    this.buyerAmount = buyerAmount;
	}

	/**
	 * 获取卖家金额
	 * @return 卖家金额
	 */
	public String getSellerAmount() {
	    return sellerAmount;
	}

	/**
	 * 设定卖家金额
	 * @param sellerAmount 卖家金额
	 */
	public void setSellerAmount(String sellerAmount) {
	    this.sellerAmount = sellerAmount;
	}

	/**
	 * 获取平台金额
	 * @return 平台金额
	 */
	public String getPlatformAmount() {
	    return platformAmount;
	}

	/**
	 * 设定平台金额
	 * @param platformAmount 平台金额
	 */
	public void setPlatformAmount(String platformAmount) {
	    this.platformAmount = platformAmount;
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
	 * 获取操作者IP
	 * @return 操作者IP
	 */
	public String getOperattorIp() {
	    return operattorIp;
	}

	/**
	 * 设定操作者IP
	 * @param operattorIp 操作者IP
	 */
	public void setOperattorIp(String operattorIp) {
	    this.operattorIp = operattorIp;
	}
}
