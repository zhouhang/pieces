/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.vo;

import com.jointown.zy.common.model.BusiPurchase;

/**
 * @ClassName: BusiPurchaseMobileEmailMsgVo
 * @Description: 发送采购通知用
 * @Author: 赵航
 * @Date: 2015年10月16日
 * @Version: 1.0
 */
public class BusiPurchaseMobileEmailMsgVo extends BusiPurchase{
	
	private static final long serialVersionUID = 6052256456234432526L;
	
	/** 交易员名称 */
	private String tradersName;
	
	/** 交易员电话 */
	private String tradersMobile;
	
	/** 交易员Email */
	private String tradersEmail;
	
	/** 发布会员电话 */
	private String purchaserMobile;

	/**
	 * 获取交易员名称
	 * @return 交易员名称
	 */
	public String getTradersName() {
	    return tradersName;
	}

	/**
	 * 设定交易员名称
	 * @param tradersName 交易员名称
	 */
	public void setTradersName(String tradersName) {
	    this.tradersName = tradersName;
	}

	/**
	 * 获取交易员电话
	 * @return 交易员电话
	 */
	public String getTradersMobile() {
	    return tradersMobile;
	}

	/**
	 * 设定交易员电话
	 * @param tradersMobile 交易员电话
	 */
	public void setTradersMobile(String tradersMobile) {
	    this.tradersMobile = tradersMobile;
	}

	/**
	 * 获取交易员Email
	 * @return 交易员Email
	 */
	public String getTradersEmail() {
	    return tradersEmail;
	}

	/**
	 * 设定交易员Email
	 * @param tradersEmail 交易员Email
	 */
	public void setTradersEmail(String tradersEmail) {
	    this.tradersEmail = tradersEmail;
	}

	/**
	 * 获取发布会员电话
	 * @return 发布会员电话
	 */
	public String getPurchaserMobile() {
	    return purchaserMobile;
	}

	/**
	 * 设定发布会员电话
	 * @param purchaserMobile 发布会员电话
	 */
	public void setPurchaserMobile(String purchaserMobile) {
	    this.purchaserMobile = purchaserMobile;
	}
}
