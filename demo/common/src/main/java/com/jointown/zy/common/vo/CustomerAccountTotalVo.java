package com.jointown.zy.common.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @ClassName: CustomerAccountTotalVo
 * @Description: 客户账务统计总量Vo
 * @Author: ldp
 * @Date: 2015年10月13日
 * @Version: 1.0
 */
public class CustomerAccountTotalVo {
	/**仓单总量 */
	private String wlTotal;
	/**挂牌总量 */
	private String listingTotal;
	/**销售总量*/
	private String orderTotal;
	/**销售总金额 */
	private String orderAmountTotal;
	/**采购总量 */
	private String purchaseTotal;
	/**采购总金额 */
	private String purchaseAmountTotal;
	public String getWlTotal() {
		return wlTotal;
	}
	public void setWlTotal(String wlTotal) {
		this.wlTotal = wlTotal;
	}
	public String getListingTotal() {
		return listingTotal;
	}
	public void setListingTotal(String listingTotal) {
		this.listingTotal = listingTotal;
	}
	public String getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getOrderAmountTotal() {
		return orderAmountTotal;
	}
	public void setOrderAmountTotal(String orderAmountTotal) {
		this.orderAmountTotal = orderAmountTotal;
	}
	public String getPurchaseTotal() {
		return purchaseTotal;
	}
	public void setPurchaseTotal(String purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}
	public String getPurchaseAmountTotal() {
		return purchaseAmountTotal;
	}
	public void setPurchaseAmountTotal(String purchaseAmountTotal) {
		this.purchaseAmountTotal = purchaseAmountTotal;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
	
}
