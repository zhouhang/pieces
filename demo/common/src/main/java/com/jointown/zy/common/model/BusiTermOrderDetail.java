package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiTermOrderDetail implements Serializable {
	/** 账期详情ID */
    private String id;

    /** 账期订单编号 */
    private String orderid;

    /** 账期订单备注 */
    private String note;

    /** 账期开始时间 */
    private Date startTime;

    /** 账期结束时间 */
    private Date endTime;

    /** 后台操作人ID */
    private Long operator;

    /** 账期创建时间 */
    private Date createTime;

	/**
	 * 取得账期详情ID
	 * @return 账期详情ID
	 */
	public String getId() {
	    return id;
	}

	/**
	 * 设定账期详情ID
	 * @param id 账期详情ID
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * 取得账期订单编号
	 * @return 账期订单编号
	 */
	public String getOrderid() {
	    return orderid;
	}

	/**
	 * 设定账期订单编号
	 * @param orderid 账期订单编号
	 */
	public void setOrderid(String orderid) {
	    this.orderid = orderid;
	}

	/**
	 * 取得账期订单备注
	 * @return 账期订单备注
	 */
	public String getNote() {
	    return note;
	}

	/**
	 * 设定账期订单备注
	 * @param note 账期订单备注
	 */
	public void setNote(String note) {
	    this.note = note;
	}

	/**
	 * 取得账期开始时间
	 * @return 账期开始时间
	 */
	public Date getStartTime() {
	    return startTime;
	}

	/**
	 * 设定账期开始时间
	 * @param startTime 账期开始时间
	 */
	public void setStartTime(Date startTime) {
	    this.startTime = startTime;
	}

	/**
	 * 取得账期结束时间
	 * @return 账期结束时间
	 */
	public Date getEndTime() {
	    return endTime;
	}

	/**
	 * 设定账期结束时间
	 * @param endTime 账期结束时间
	 */
	public void setEndTime(Date endTime) {
	    this.endTime = endTime;
	}

	/**
	 * 取得后台操作人ID
	 * @return 后台操作人ID
	 */
	public Long getOperator() {
	    return operator;
	}

	/**
	 * 设定后台操作人ID
	 * @param operator 后台操作人ID
	 */
	public void setOperator(Long operator) {
	    this.operator = operator;
	}

	/**
	 * 取得账期创建时间
	 * @return 账期创建时间
	 */
	public Date getCreateTime() {
	    return createTime;
	}

	/**
	 * 设定账期创建时间
	 * @param createTime 账期创建时间
	 */
	public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	}

}