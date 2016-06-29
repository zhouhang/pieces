package com.jointown.zy.common.vo;

import java.io.Serializable;

/**
 * 
 * 描述： 挂牌商品卖家信息<br/>
 * 
 * 日期： 2015年1月7日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public class BusiGoodsSellerVo implements Serializable{

	private static final long serialVersionUID = 5492285230296934716L;
	
	/** 卖家ID */
	private String sellerId;

	/** 认证名称 */
	private String name;
	
	/** 手机 */
	private String mobilephone;
	
	/** 注册时间 */
	private String registTime;
	
	/** 成交单数 */
	private String dealCount;
	
	/** 现货保障（图片） */
	private String spotSecurityPic;
	
	/** 质量保障（图片）*/
	private String qualitySecurityPic;
	
	//add by fanyuna
	/** 挂牌数量 */
	private String goodsCount;
	
	/** 认证标志  1:已通过个人认证 2:已通过企业认证 */
	private String certify;
	
	/**add by ldp 2015-09-21*/
	/**已下单数量*/
	private String orderSCount;

	/**
	 * 取得卖家ID
	 * @return 卖家ID
	 */
	public String getSellerId() {
	    return sellerId;
	}

	/**
	 * 设定卖家ID
	 * @param sellerId 卖家ID
	 */
	public void setSellerId(String sellerId) {
	    this.sellerId = sellerId;
	}

	/**
	 * 取得姓名
	 * @return 姓名
	 */
	public String getName() {
	    return name;
	}

	/**
	 * 设定姓名
	 * @param name 姓名
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * 取得手机
	 * @return 手机
	 */
	public String getMobilephone() {
	    return mobilephone;
	}

	/**
	 * 设定手机
	 * @param mobilephone 手机
	 */
	public void setMobilephone(String mobilephone) {
	    this.mobilephone = mobilephone;
	}

	/**
	 * 取得注册时间
	 * @return 注册时间
	 */
	public String getRegistTime() {
	    return registTime;
	}

	/**
	 * 设定注册时间
	 * @param registTime 注册时间
	 */
	public void setRegistTime(String registTime) {
	    this.registTime = registTime;
	}

	/**
	 * 取得成交单数
	 * @return 成交单数
	 */
	public String getDealCount() {
	    return dealCount;
	}

	/**
	 * 设定成交单数
	 * @param dealCount 成交单数
	 */
	public void setDealCount(String dealCount) {
	    this.dealCount = dealCount;
	}

	/**
	 * 取得现货保障（图片）
	 * @return 现货保障（图片）
	 */
	public String getSpotSecurityPic() {
	    return spotSecurityPic;
	}

	/**
	 * 设定现货保障（图片）
	 * @param spotSecurityPic 现货保障（图片）
	 */
	public void setSpotSecurityPic(String spotSecurityPic) {
	    this.spotSecurityPic = spotSecurityPic;
	}

	/**
	 * 取得质量保障（图片）
	 * @return 质量保障（图片）
	 */
	public String getQualitySecurityPic() {
	    return qualitySecurityPic;
	}

	/**
	 * 设定质量保障（图片）
	 * @param qualitySecurityPic 质量保障（图片）
	 */
	public void setQualitySecurityPic(String qualitySecurityPic) {
	    this.qualitySecurityPic = qualitySecurityPic;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getCertify() {
		return certify;
	}

	public void setCertify(String certify) {
		this.certify = certify;
	}

	public String getOrderSCount() {
		return orderSCount;
	}

	public void setOrderSCount(String orderSCount) {
		this.orderSCount = orderSCount;
	}
	
}
