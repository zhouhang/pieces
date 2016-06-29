/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.model.BusiPurchase;

/**
 * @ClassName: BossPurchaseVo
 * @Description: 采购交易Vo
 * @Author: 赵航
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public class BusiPurchaseVo extends BusiPurchase{
	
	private static final long serialVersionUID = -7422593561178525626L;
	
	/** 计量单位名称 */
	private String wunitName;
	
	/** 采购会员手机号 */
	private String purchaserMobile;
	
	/** 交易员名称 */
	private String tradersName;
	
	/** 交易员邮箱*/
	private String tradersEmail;
	
	/** 交易员名称 */
	private String tradersMobile;
	

	
	/** 成交的采购价格 */
	private BigDecimal purchasePrice;
	
	/** 过期时间 */
	private Date overTime;
	
	/** 最小报价 */
	private BigDecimal minQuotePrice;
	
	/** 报价次数*/
	private Integer quoteCount;
	
	/** 成交后的过去天数 */
	private Integer dealSuccessPassedDay;
	
	/**
	 * 获取过期时间
	 * @return 过期时间
	 */
	public Date getOverTime() {
		return overTime;
	}

	/**
	 * 设定过期时间
	 * @param overTime 过期时间
	 */
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	/**
	 * 获取计量单位名称
	 * @return 计量单位名称
	 */
	public String getWunitName() {
	    return wunitName;
	}

	/**
	 * 设定计量单位名称
	 * @param wunitName 计量单位名称
	 */
	public void setWunitName(String wunitName) {
	    this.wunitName = wunitName;
	}

	/**
	 * 获取交易员名称,如果为空则获取备选的
	 * @return 交易员名称
	 */
	public String getTradersNameOrAlternative() {
	    return StringUtils.isEmpty(getTradersName())?MessageConstant.PURCHASE_ALTERNATIVE_SALESMAN_NAME.getValue():getTradersName();
	}
	
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
	 * 获取采购价格
	 * @return 采购价格
	 */
	public BigDecimal getPurchasePrice() {
	    return purchasePrice;
	}

	/**
	 * 设定采购价格
	 * @param purchasePrice 采购价格
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
	    this.purchasePrice = purchasePrice;
	}

	/**
	 * 获取最小报价
	 * @return 最小报价
	 */
	public BigDecimal getMinQuotePrice() {
		return minQuotePrice;
	}

	/**
	 * 设定最小报价
	 * @param minQuotePrice 最小报价
	 */
	public void setMinQuotePrice(BigDecimal minQuotePrice) {
		this.minQuotePrice = minQuotePrice;
	}

	/**
	 * 获取成交后的过去天数
	 * @return 成交后的过去天数
	 */
	public Integer getDealSuccessPassedDay() {
	    return dealSuccessPassedDay;
	}

	/**
	 * 设定成交后的过去天数
	 * @param dealSuccessPassedDay 成交后的过去天数
	 */
	public void setDealSuccessPassedDay(Integer dealSuccessPassedDay) {
	    this.dealSuccessPassedDay = dealSuccessPassedDay;
	}

	/**
	 * 
	 * @Description: 获取交易员邮箱,如果为空则获取备选的
	 * @Author: 刘漂
	 * @Date: 2015年11月6日
	 * @return
	 */
	public String getTradersEmailOrAlternative() {
		return StringUtils.isEmpty(getTradersEmail())?MessageConstant.PURCHASE_ALTERNATIVE_SALESMAN_EMAIL.getValue():getTradersEmail();
	}
	
	public String getTradersEmail() {
		return tradersEmail;
	}

	public void setTradersEmail(String tradersEmail) {
		this.tradersEmail = tradersEmail;
	}

	/**
	 * 
	 * @Description: 获取交易员手机号,如果为空则获取备选的
	 * @Author: 刘漂
	 * @Date: 2015年11月6日
	 * @return
	 */
	public String getTradersMobileOrAlternative() {
		return StringUtils.isEmpty(getTradersMobile())?MessageConstant.PURCHASE_ALTERNATIVE_SALESMAN_PHONE.getValue():getTradersMobile();
	}
	
	public String getTradersMobile() {
		return tradersMobile;
	}

	public void setTradersMobile(String tradersMobile) {
		this.tradersMobile = tradersMobile;
	}

	public Integer getQuoteCount() {
		return quoteCount;
	}

	public void setQuoteCount(Integer quoteCount) {
		this.quoteCount = quoteCount;
	}

	public String getPurchaserMobile() {
		return purchaserMobile;
	}

	public void setPurchaserMobile(String purchaserMobile) {
		this.purchaserMobile = purchaserMobile;
	}
	
	private boolean isTrue(boolean...booleanParam){
		return ((ArrayUtils.isEmpty(booleanParam))||(!booleanParam[0]))?false:true;
	}

}
