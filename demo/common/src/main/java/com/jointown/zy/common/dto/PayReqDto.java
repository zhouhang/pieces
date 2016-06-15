package com.jointown.zy.common.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @ClassName: PayReqDto
 * @Description: 交易支付接口Dto
 * @Author: ldp
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
public class PayReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9132531086944542493L;
	
	/**交易订单号*/
	private String orderId;
	/**交易金额*/
	private String amount;
	/**金额类型*/
	private String amtType;
	/**付款人ID*/
	private String userId;
	/**收款人ID*/
	private String recieveId;
	/**系统标识*/
	private String sourceSys;
	/**商品名称*/
	private String orderTitle;
	/**签名数据*/
	private String signData;
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmtType() {
		return amtType;
	}
	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRecieveId() {
		return recieveId;
	}
	public void setRecieveId(String recieveId) {
		this.recieveId = recieveId;
	}
	public String getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}
	public String getSignData() {
		return signData;
	}
	public void setSignData(String signData) {
		this.signData = signData;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
