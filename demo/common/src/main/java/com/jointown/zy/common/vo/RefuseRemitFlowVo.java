package com.jointown.zy.common.vo;

import java.io.Serializable;

/**
 * @ClassName: RefuseRemitFlowVo
 * @Description: 订单拒绝信息vo
 * @Author: guoyb
 * @Date: 2015年7月6日
 * @Version: 1.0
 */
public class RefuseRemitFlowVo implements Serializable{
	
	//后台操作人账号
	private String opraterCode;
	//操作人中文名称
	private String opraterName;
	//订单号
	private String orderId;
	//拒绝理由
	private String memo;
	//操作人主键ID
	private String opraterId;
	//操作日期
	private String opraterTime;
	
	/**
	 * @return the opraterCode
	 */
	public String getOpraterCode() {
		return opraterCode;
	}
	/**
	 * @param opraterCode the opraterCode to set
	 */
	public void setOpraterCode(String opraterCode) {
		this.opraterCode = opraterCode;
	}
	/**
	 * @return the opraterName
	 */
	public String getOpraterName() {
		return opraterName;
	}
	/**
	 * @param opraterName the opraterName to set
	 */
	public void setOpraterName(String opraterName) {
		this.opraterName = opraterName;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @return the opraterId
	 */
	public String getOpraterId() {
		return opraterId;
	}
	/**
	 * @param opraterId the opraterId to set
	 */
	public void setOpraterId(String opraterId) {
		this.opraterId = opraterId;
	}
	/**
	 * @return the opraterTime
	 */
	public String getOpraterTime() {
		return opraterTime;
	}
	/**
	 * @param opraterTime the opraterTime to set
	 */
	public void setOpraterTime(String opraterTime) {
		this.opraterTime = opraterTime;
	}

	
}
