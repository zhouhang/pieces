/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.vo;

/**
 * @ClassName: OrderMobileEmailVo
 * @Description: 订单相关买卖双方及业务员的信息
 * @Author: 赵航
 * @Date: 2015年9月9日
 * @Version: 1.0
 */
public class OrderMobileEmailVo {
	
	/** 订单编号 */
	private String orderId;

	/** 买方ID */
	private Long buyerId;
	
	/** 买方用户名 */
	private String buyerName;
	
	/** 买方手机号 */
	private String buyerMobile;
	
	/** 买方Email */
	private String buyerEmail;
	
	/** 买方业务员ID */
	private Long buyerSalesmanId;
	
	/** 买方业务员用户名 */
	private String buyerSalesmanName;
	
	/** 买方业务员手机号 */
	private String buyerSalesmanMobile;
	
	/** 买方业务员Email */
	private String buyerSalesmanEmail;
	
	/** 卖方ID */
	private Long sellerId;
	
	/** 卖方用户名 */
	private String sellerName;
	
	/** 卖方手机号 */
	private String sellerMobile;
	
	/** 卖方Email */
	private String sellerEmail;
	
	/** 卖方业务员ID */
	private Long sellerSalesmanId;
	
	/** 卖方业务员用户名 */
	private String sellerSalesmanName;
	
	/** 卖方业务员手机号 */
	private String sellerSalesmanMobile;
	
	/** 卖方业务员Email */
	private String sellerSalesmanEmail;

	/**
	 * 取得 订单编号
	 * @return 订单编号
	 */
	public String getOrderId() {
	    return orderId;
	}

	/**
	 * 設定 订单编号
	 * @param orderId 订单编号
	 */
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}

	/**
	 * 取得 买方ID
	 * @return 买方ID
	 */
	public Long getBuyerId() {
	    return buyerId;
	}

	/**
	 * 設定 买方ID
	 * @param buyerId 买方ID
	 */
	public void setBuyerId(Long buyerId) {
	    this.buyerId = buyerId;
	}

	/**
	 * 取得 买方用户名
	 * @return 买方用户名
	 */
	public String getBuyerName() {
	    return buyerName;
	}

	/**
	 * 設定 买方用户名
	 * @param buyerName 买方用户名
	 */
	public void setBuyerName(String buyerName) {
	    this.buyerName = buyerName;
	}

	/**
	 * 取得 买方手机号
	 * @return 买方手机号
	 */
	public String getBuyerMobile() {
	    return buyerMobile;
	}

	/**
	 * 設定 买方手机号
	 * @param buyerMobile 买方手机号
	 */
	public void setBuyerMobile(String buyerMobile) {
	    this.buyerMobile = buyerMobile;
	}

	/**
	 * 取得 买方Email
	 * @return 买方Email
	 */
	public String getBuyerEmail() {
	    return buyerEmail;
	}

	/**
	 * 設定 买方Email
	 * @param buyerEmail 买方Email
	 */
	public void setBuyerEmail(String buyerEmail) {
	    this.buyerEmail = buyerEmail;
	}

	/**
	 * 取得 买方业务员ID
	 * @return 买方业务员ID
	 */
	public Long getBuyerSalesmanId() {
	    return buyerSalesmanId;
	}

	/**
	 * 設定 买方业务员ID
	 * @param buyerSalesmanId 买方业务员ID
	 */
	public void setBuyerSalesmanId(Long buyerSalesmanId) {
	    this.buyerSalesmanId = buyerSalesmanId;
	}

	/**
	 * 取得 买方业务员用户名
	 * @return 买方业务员用户名
	 */
	public String getBuyerSalesmanName() {
	    return buyerSalesmanName;
	}

	/**
	 * 設定 买方业务员用户名
	 * @param buyerSalesmanName 买方业务员用户名
	 */
	public void setBuyerSalesmanName(String buyerSalesmanName) {
	    this.buyerSalesmanName = buyerSalesmanName;
	}

	/**
	 * 取得 买方业务员手机号
	 * @return 买方业务员手机号
	 */
	public String getBuyerSalesmanMobile() {
	    return buyerSalesmanMobile;
	}

	/**
	 * 設定 买方业务员手机号
	 * @param buyerSalesmanMobile 买方业务员手机号
	 */
	public void setBuyerSalesmanMobile(String buyerSalesmanMobile) {
	    this.buyerSalesmanMobile = buyerSalesmanMobile;
	}

	/**
	 * 取得 买方业务员Email
	 * @return 买方业务员Email
	 */
	public String getBuyerSalesmanEmail() {
	    return buyerSalesmanEmail;
	}

	/**
	 * 設定 买方业务员Email
	 * @param buyerSalesmanEmail 买方业务员Email
	 */
	public void setBuyerSalesmanEmail(String buyerSalesmanEmail) {
	    this.buyerSalesmanEmail = buyerSalesmanEmail;
	}

	/**
	 * 取得 卖方ID
	 * @return 卖方ID
	 */
	public Long getSellerId() {
	    return sellerId;
	}

	/**
	 * 設定 卖方ID
	 * @param sellerId 卖方ID
	 */
	public void setSellerId(Long sellerId) {
	    this.sellerId = sellerId;
	}

	/**
	 * 取得 卖方用户名
	 * @return 卖方用户名
	 */
	public String getSellerName() {
	    return sellerName;
	}

	/**
	 * 設定 卖方用户名
	 * @param sellerName 卖方用户名
	 */
	public void setSellerName(String sellerName) {
	    this.sellerName = sellerName;
	}

	/**
	 * 取得 卖方手机号
	 * @return 卖方手机号
	 */
	public String getSellerMobile() {
	    return sellerMobile;
	}

	/**
	 * 設定 卖方手机号
	 * @param sellerMobile 卖方手机号
	 */
	public void setSellerMobile(String sellerMobile) {
	    this.sellerMobile = sellerMobile;
	}

	/**
	 * 取得 卖方Email
	 * @return 卖方Email
	 */
	public String getSellerEmail() {
	    return sellerEmail;
	}

	/**
	 * 設定 卖方Email
	 * @param sellerEmail 卖方Email
	 */
	public void setSellerEmail(String sellerEmail) {
	    this.sellerEmail = sellerEmail;
	}

	/**
	 * 取得 卖方业务员ID
	 * @return 卖方业务员ID
	 */
	public Long getSellerSalesmanId() {
	    return sellerSalesmanId;
	}

	/**
	 * 設定 卖方业务员ID
	 * @param sellerSalesmanId 卖方业务员ID
	 */
	public void setSellerSalesmanId(Long sellerSalesmanId) {
	    this.sellerSalesmanId = sellerSalesmanId;
	}

	/**
	 * 取得 卖方业务员用户名
	 * @return 卖方业务员用户名
	 */
	public String getSellerSalesmanName() {
	    return sellerSalesmanName;
	}

	/**
	 * 設定 卖方业务员用户名
	 * @param sellerSalesmanName 卖方业务员用户名
	 */
	public void setSellerSalesmanName(String sellerSalesmanName) {
	    this.sellerSalesmanName = sellerSalesmanName;
	}

	/**
	 * 取得 卖方业务员手机号
	 * @return 卖方业务员手机号
	 */
	public String getSellerSalesmanMobile() {
	    return sellerSalesmanMobile;
	}

	/**
	 * 設定 卖方业务员手机号
	 * @param sellerSalesmanMobile 卖方业务员手机号
	 */
	public void setSellerSalesmanMobile(String sellerSalesmanMobile) {
	    this.sellerSalesmanMobile = sellerSalesmanMobile;
	}

	/**
	 * 取得 卖方业务员Email
	 * @return 卖方业务员Email
	 */
	public String getSellerSalesmanEmail() {
	    return sellerSalesmanEmail;
	}

	/**
	 * 設定 卖方业务员Email
	 * @param sellerSalesmanEmail 卖方业务员Email
	 */
	public void setSellerSalesmanEmail(String sellerSalesmanEmail) {
	    this.sellerSalesmanEmail = sellerSalesmanEmail;
	}
}
