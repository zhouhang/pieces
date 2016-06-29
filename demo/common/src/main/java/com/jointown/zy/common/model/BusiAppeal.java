/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BusiAppeal
 * @Description: 订单申诉表模型类
 * @Author: 赵航
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public class BusiAppeal implements Serializable{

	private static final long serialVersionUID = 6945418400319716531L;

	/** 申诉编号 */
	private Long appealId;
	
	/** 订单编号 */
	private String orderId;
	
	/** 具体描述 */
	private String reason;
	
	/** 申诉类型 */
	private Short appealType;
	
	/** 其他原因名 */
	private String appealName; 
	
	/** 证据地址 */
	private String evidencePic;
	
	/** 申诉状态 */
	private Short examineState;
	
	/** 创建日期 */
	private Date createTime;
	
	/** 修改日期 */
	private Date updateTime;
	
	/** 申诉人ID */
	private Long appealor;
	
	/** 驳回原因 */
	private String rejectReason;

	/**
	 * 获取申诉编号
	 * @return 申诉编号
	 */
	public Long getAppealId() {
	    return appealId;
	}

	/**
	 * 设定申诉编号
	 * @param appealId 申诉编号
	 */
	public void setAppealId(Long appealId) {
	    this.appealId = appealId;
	}

	/**
	 * 获取订单编号
	 * @return 订单编号
	 */
	public String getOrderId() {
	    return orderId;
	}

	/**
	 * 设定订单编号
	 * @param orderId 订单编号
	 */
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}

	/**
	 * 获取具体描述
	 * @return 具体描述
	 */
	public String getReason() {
	    return reason;
	}

	/**
	 * 设定具体描述
	 * @param reason 具体描述
	 */
	public void setReason(String reason) {
	    this.reason = reason;
	}

	/**
	 * 获取申诉类型
	 * @return 申诉类型
	 */
	public Short getAppealType() {
	    return appealType;
	}

	/**
	 * 设定申诉类型
	 * @param appealType 申诉类型
	 */
	public void setAppealType(Short appealType) {
	    this.appealType = appealType;
	}

	/**
	 * 获取证据地址
	 * @return 证据地址
	 */
	public String getEvidencePic() {
	    return evidencePic;
	}

	/**
	 * 设定证据地址
	 * @param evidencePic 证据地址
	 */
	public void setEvidencePic(String evidencePic) {
	    this.evidencePic = evidencePic;
	}

	/**
	 * 获取申诉状态
	 * @return 申诉状态
	 */
	public Short getExamineState() {
	    return examineState;
	}

	/**
	 * 设定申诉状态
	 * @param examineState 申诉状态
	 */
	public void setExamineState(Short examineState) {
	    this.examineState = examineState;
	}

	/**
	 * 获取创建日期
	 * @return 创建日期
	 */
	public Date getCreateTime() {
	    return createTime;
	}

	/**
	 * 设定创建日期
	 * @param createTime 创建日期
	 */
	public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	}

	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getUpdateTime() {
	    return updateTime;
	}

	/**
	 * 设定修改日期
	 * @param updateTime 修改日期
	 */
	public void setUpdateTime(Date updateTime) {
	    this.updateTime = updateTime;
	}

	/**
	 * 获取申诉人ID
	 * @return 申诉人ID
	 */
	public Long getAppealor() {
	    return appealor;
	}

	/**
	 * 设定申诉人ID
	 * @param appealor 申诉人ID
	 */
	public void setAppealor(Long appealor) {
	    this.appealor = appealor;
	}

	/**
	 * 获取驳回原因
	 * @return 驳回原因
	 */
	public String getRejectReason() {
	    return rejectReason;
	}

	/**
	 * 设定驳回原因
	 * @param rejectReason 驳回原因
	 */
	public void setRejectReason(String rejectReason) {
	    this.rejectReason = rejectReason;
	}

	/**
	 * @return the appealName
	 */
	public String getAppealName() {
		return appealName;
	}

	/**
	 * @param appealName the appealName to set
	 */
	public void setAppealName(String appealName) {
		this.appealName = appealName;
	}
}
