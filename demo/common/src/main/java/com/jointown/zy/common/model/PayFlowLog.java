package com.jointown.zy.common.model;

import java.util.Date;

/**
 * @ClassName: PayFlowLog
 * @Description: 支付流水日志实体
 * @Author: ldp
 * @Date: 2015年4月11日
 * @Version: 1.0
 */
public class PayFlowLog {

	/**支付流水日志ID*/
	private Integer logId;
	/**系统标识*/
	private Integer sourceSys;
	/**订单ID*/
	private String orderId;
	/**返回码*/
	private String returnCode;
	/**备注*/
	private String note;
	/**状态*/
	private Integer status;
	/**日志创建时间*/
	private Date createTime;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(Integer sourceSys) {
		this.sourceSys = sourceSys;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
