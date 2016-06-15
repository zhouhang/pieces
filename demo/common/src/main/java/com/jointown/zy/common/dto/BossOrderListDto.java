/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: BossOrderListDto
 * @Description: 后台-订单查询列表查询条件DTO
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public class BossOrderListDto extends BaseDto{

	private static final long serialVersionUID = 6246353273813095348L;
	
	/** 订单编号 */
	private String orderId;
	
	/** 挂牌编号 */
	private String listingId;
	
	/** 订单状态 */
	private String orderState;
	
	/** 摘牌时间（始） */
	private String orderStartDate;
	
	/** 摘牌时间（止） */
	private String orderEndDate;
	
	/** 业务员姓名 */
	private String salesmanName;
	
	/** 标题 */
	private String title;
	
	/** 买方名称 */
	private String buyerName;
	
	/** 卖方名称 */
	private String sellerName;
	/**品种*/
	private String breedname;
  	
  	//add by fanyuna 2015.12.01 一次性付款需求
  	/** 付款方式*/
  	private String payWay;
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
	 * 取得 挂牌编号
	 * @return 挂牌编号
	 */
	public String getListingId() {
	    return listingId;
	}

	/**
	 * 設定 挂牌编号
	 * @param listingId 挂牌编号
	 */
	public void setListingId(String listingId) {
	    this.listingId = listingId;
	}

	/**
	 * 取得 订单状态
	 * @return 订单状态
	 */
	public String getOrderState() {
	    return orderState;
	}

	/**
	 * 設定 订单状态
	 * @param orderState 订单状态
	 */
	public void setOrderState(String orderState) {
	    this.orderState = orderState;
	}

	/**
	 * 取得 摘牌时间（始）
	 * @return 摘牌时间（始）
	 */
	public String getOrderStartDate() {
	    return orderStartDate;
	}

	/**
	 * 設定 摘牌时间（始）
	 * @param orderStartDate 摘牌时间（始）
	 */
	public void setOrderStartDate(String orderStartDate) {
	    this.orderStartDate = orderStartDate;
	}

	/**
	 * 取得 摘牌时间（止）
	 * @return 摘牌时间（止）
	 */
	public String getOrderEndDate() {
	    return orderEndDate;
	}

	/**
	 * 設定 摘牌时间（止）
	 * @param orderEndDate 摘牌时间（止）
	 */
	public void setOrderEndDate(String orderEndDate) {
	    this.orderEndDate = orderEndDate;
	}

	/**
	 * 取得 业务员姓名
	 * @return 业务员姓名
	 */
	public String getSalesmanName() {
	    return salesmanName;
	}

	/**
	 * 設定 业务员姓名
	 * @param salesmanName 业务员姓名
	 */
	public void setSalesmanName(String salesmanName) {
	    this.salesmanName = salesmanName;
	}

	/**
	 * 取得 标题
	 * @return 标题
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * 設定 标题
	 * @param title 标题
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 取得 买方名称
	 * @return 买方名称
	 */
	public String getBuyerName() {
	    return buyerName;
	}

	/**
	 * 設定 买方名称
	 * @param buyerName 买方名称
	 */
	public void setBuyerName(String buyerName) {
	    this.buyerName = buyerName;
	}

	/**
	 * 取得 卖方名称
	 * @return 卖方名称
	 */
	public String getSellerName() {
	    return sellerName;
	}

	/**
	 * 設定 卖方名称
	 * @param sellerName 卖方名称
	 */
	public void setSellerName(String sellerName) {
	    this.sellerName = sellerName;
	}

	public String getBreedname() {
		return breedname;
	}

	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	

}
