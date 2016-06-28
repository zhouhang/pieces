package com.jointown.zy.common.pay;

import java.io.Serializable;

/**
 * @ClassName: RemitResultResp
 * @Description: 划账流水结果响应对象
 * @Author: ldp
 * @Date: 2015年7月3日
 * @Version: 1.0
 */

public class RemitResultResp implements Serializable {

	/** TODO */
	private static final long serialVersionUID = -7578204935178653627L;
	/**交易订单号*/
	private String orderId;
	/**划转类型  ：1-订单完成划账  2-订单过期划账 3-订单申退划账*/
	private String remitType;
	/**划账流水号*/
	private String remitFlowId;
	/**划账返回状态1-划账成功 3-拒绝 */
	private String remitStatus;
	/**划账流水结果ID*/
	private String remitResultId;
	/**划账完成时间*/
	private String remitTime = "";
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRemitType() {
		return remitType;
	}
	public void setRemitType(String remitType) {
		this.remitType = remitType;
	}
	public String getRemitFlowId() {
		return remitFlowId;
	}
	public void setRemitFlowId(String remitFlowId) {
		this.remitFlowId = remitFlowId;
	}
	public String getRemitStatus() {
		return remitStatus;
	}
	public void setRemitStatus(String remitStatus) {
		this.remitStatus = remitStatus;
	}
	public String getRemitResultId() {
		return remitResultId;
	}
	public void setRemitResultId(String remitResultId) {
		this.remitResultId = remitResultId;
	}
	public String getRemitTime() {
		return remitTime;
	}
	public void setRemitTime(String remitTime) {
		this.remitTime = remitTime;
	}
	
	
}
