package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情表
 * 
 * @author Liu Piao
 *
 * @date 2015-4-16
 */
@SuppressWarnings("serial")
public class BusiOrderDetail implements Serializable {
	
	/**
	 * 订单详情ID
	 */
	private Long orderDetailId;
	/**
	 * 订单ID
	 */
	private String orderId;
	/**
	 * 状态
	 */
	private String state;
	private Date createTime;
	private Date updateTime;
	/**
	 * 挂牌快照
	 */
	private String listingSnapshot;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getListingSnapshot() {
		return listingSnapshot;
	}

	public void setListingSnapshot(String listingSnapshot) {
		this.listingSnapshot = listingSnapshot;
	}

}