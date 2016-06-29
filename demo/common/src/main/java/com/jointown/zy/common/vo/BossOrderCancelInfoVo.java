/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.vo;

import java.io.Serializable;

/**
 * @ClassName: BossOrderCancelInfoVo
 * @Description: 后台交易取消审核-订单退款进度详情VO
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public class BossOrderCancelInfoVo implements Serializable{

	private static final long serialVersionUID = -2821002572670036638L;
	
	/** 订单编号 */
	private String orderId;
	
	/** 具体描述 */
	private String reason;
	
	/** 申诉类型 */
	private String appealType;
	
	/** 申诉类型名称 */
	private String appealTypeName;
	
	/** 证据地址 */
	private String evidencePic;

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
	public String getAppealType() {
	    return appealType;
	}

	/**
	 * 设定申诉类型
	 * @param appealType 申诉类型
	 */
	public void setAppealType(String appealType) {
	    this.appealType = appealType;
	}

	/**
	 * 获取申诉类型名称
	 * @return 申诉类型名称
	 */
	public String getAppealTypeName() {
	    return appealTypeName;
	}

	/**
	 * 设定申诉类型名称
	 * @param appealTypeName 申诉类型名称
	 */
	public void setAppealTypeName(String appealTypeName) {
	    this.appealTypeName = appealTypeName;
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
	
}
