package com.jointown.zy.common.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @ClassName: RemitDto
 * @Description: 订单分润dto
 * @Author: ldp
 * @Date: 2015年7月1日
 * @Version: 1.0
 */
public class RemitDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = -395085621378665909L;
	/**交易订单号*/
	private String orderId;
	/**划账开始时间*/
	private String remitStartTime;
	/**划账结束时间*/
	private String remitEndTime;
	/**订单分润状态*/
	private String status;
	/**订单支付渠道*/
	private String payChannel;
	/**划账类型*/
	private String remitType;
	
	/**分类显示 分润 退款数据*/
	private String remitListType;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getRemitStartTime() {
		return remitStartTime;
	}
	public void setRemitStartTime(String remitStartTime) {
		this.remitStartTime = remitStartTime;
	}
	public String getRemitEndTime() {
		return remitEndTime;
	}
	public void setRemitEndTime(String remitEndTime) {
		this.remitEndTime = remitEndTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	public String getRemitType() {
		return remitType;
	}
	public void setRemitType(String remitType) {
		this.remitType = remitType;
	}
	public String getRemitListType() {
		return remitListType;
	}
	public void setRemitListType(String remitListType) {
		this.remitListType = remitListType;
	}
	
	
}
