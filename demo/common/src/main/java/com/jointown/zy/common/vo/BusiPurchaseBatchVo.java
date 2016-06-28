package com.jointown.zy.common.vo;

import org.apache.commons.lang3.StringUtils;

import com.jointown.zy.common.model.BusiPurchaseBatch;
import com.jointown.zy.common.util.SpringUtil;

/**
 * 
 * @ClassName: BusiPurchaseBatchVo
 * @Description: 采购批次信息值对象
 * @Author: 刘漂
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public class BusiPurchaseBatchVo extends BusiPurchaseBatch{
	
	private static final long serialVersionUID = -2033912442001520607L;

	public BusiPurchaseBatchVo() {
	}
	
	/**
	 * 采购数目
	 */
	private Integer purchaseCount;
	
	/**
	 * 报价总数
	 */
	private Integer quoteAmount;
	
	/**
	 * 最低报价
	 */
	private Integer minQuotePrice;
	
	/**
	 * 交易员
	 */
	private String salesman;
	
	/**
	 * 交易员姓名
	 */
	private String salesmanName;
	
	/**
	 * 交易员邮箱
	 */
	private String salesmanEmail;
	
	/**
	 * 交易员电话
	 */
	private String salesmanPhone;
	

	public Integer getQuoteAmount() {
		return quoteAmount;
	}

	public void setQuoteAmount(Integer quoteAmount) {
		this.quoteAmount = quoteAmount;
	}

	public Integer getMinQuotePrice() {
		return minQuotePrice;
	}

	public void setMinQuotePrice(Integer minQuotePrice) {
		this.minQuotePrice = minQuotePrice;
	}

	public Integer getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanName() {
		return StringUtils.isEmpty(salesmanName)?SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name"):salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getSalesmanEmail() {
		return  StringUtils.isEmpty(salesmanEmail)?SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.email"):salesmanEmail;
	}

	public void setSalesmanEmail(String salesmanEmail) {
		this.salesmanEmail = salesmanEmail;
	}

	public String getSalesmanPhone() {
		return StringUtils.isEmpty(salesmanPhone)?SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.phone"):salesmanPhone;
	}

	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}
	
	

}
