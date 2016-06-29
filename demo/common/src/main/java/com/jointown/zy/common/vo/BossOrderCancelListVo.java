/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BossOrderCancelListVo
 * @Description: 后台交易取消列表Vo
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public class BossOrderCancelListVo implements Serializable{

	private static final long serialVersionUID = 5236192010610447776L;
	
	/** 订单编号 */
	private String orderId;
	
	/** 申请人用户名 */
	private String applicantName;
	
	/** 申请人真实名称 */
	private String applicantRealName;
	
	/** 申请人联系方式 */
	private String applicantMobile;
	
	/** 订单状态 */
	private String orderState;
	
	/** 审核状态 */
	private String examineState;
	
	/** 申请时间 */
	private Date applicantDate;
	
	/** 取消原因 */
	private String cancelType;
	
	/**
	 * add by fanyuna 2015.07.30 增加订单买家、卖家业务员名称 start
	 */
	private String buyerSalesmanName;
	
	private String sellerSalesmanName;
	
	/*****add by ldp 2015.08.18 增加卖家、标题、订单总额、实际付款****/
	/**卖家*/
	private String seller;
	/**买家真实名称*/
	private String sellerRealName;
	/**卖家联系方式*/
	private String sellerMobile;
	/**标题*/
	private String title;
	/**订单总价*/
	private BigDecimal totalPrice;
	/**实际付款*/
	private BigDecimal actualPayment;
	
	public String getSellerRealName() {
		return sellerRealName;
	}

	public void setSellerRealName(String sellerRealName) {
		this.sellerRealName = sellerRealName;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(BigDecimal actualPayment) {
		this.actualPayment = actualPayment;
	}

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
	 * 取得 申请人用户名
	 * @return 申请人用户名
	 */
	public String getApplicantName() {
	    return applicantName;
	}

	/**
	 * 設定 申请人用户名
	 * @param applicantName 申请人用户名
	 */
	public void setApplicantName(String applicantName) {
	    this.applicantName = applicantName;
	}

	/**
	 * 取得 申请人真实名称
	 * @return 申请人真实名称
	 */
	public String getApplicantRealName() {
	    return applicantRealName;
	}

	/**
	 * 設定 申请人真实名称
	 * @param applicantRealName 申请人真实名称
	 */
	public void setApplicantRealName(String applicantRealName) {
	    this.applicantRealName = applicantRealName;
	}

	/**
	 * 取得 申请人联系方式
	 * @return 申请人联系方式
	 */
	public String getApplicantMobile() {
	    return applicantMobile;
	}

	/**
	 * 設定 申请人联系方式
	 * @param applicantMobile 申请人联系方式
	 */
	public void setApplicantMobile(String applicantMobile) {
	    this.applicantMobile = applicantMobile;
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
	 * 取得 审核状态
	 * @return 审核状态
	 */
	public String getExamineState() {
	    return examineState;
	}

	/**
	 * 設定 审核状态
	 * @param examineState 审核状态
	 */
	public void setExamineState(String examineState) {
	    this.examineState = examineState;
	}

	/**
	 * 取得 申请时间
	 * @return 申请时间
	 */
	public Date getApplicantDate() {
	    return applicantDate;
	}

	/**
	 * 設定 申请时间
	 * @param applicantDate 申请时间
	 */
	public void setApplicantDate(Date applicantDate) {
	    this.applicantDate = applicantDate;
	}

	/**
	 * 取得 取消原因
	 * @return 取消原因
	 */
	public String getCancelType() {
	    return cancelType;
	}

	/**
	 * 設定 取消原因
	 * @param cancelType 取消原因
	 */
	public void setCancelType(String cancelType) {
	    this.cancelType = cancelType;
	}
	
}
