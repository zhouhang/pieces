package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class RemitFlowLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//日志ID(主键)
	private Long logId;
	//来源系统 0-交易系统
	private Integer sourcesys;
	//订单ID
	private String orderId;
	//返回码
	private String returnCode;
	//备注
	private String note;
	//状态：1-成功 0-失败
	private Integer status;
	//创建时间
	private Date createTime;
	//划账类型
	private Integer remitType;
	//划账流水号
	private Long flowId;
	
	public RemitFlowLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RemitFlowLog(Long logId, Integer sourcesys, String orderId,
			String returnCode, String note, Integer status, Date createTime,
			Integer remitType, Long flowId) {
		super();
		this.logId = logId;
		this.sourcesys = sourcesys;
		this.orderId = orderId;
		this.returnCode = returnCode;
		this.note = note;
		this.status = status;
		this.createTime = createTime;
		this.remitType = remitType;
		this.flowId = flowId;
	}

	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public Integer getSourcesys() {
		return sourcesys;
	}
	public void setSourcesys(Integer sourcesys) {
		this.sourcesys = sourcesys;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRemitType() {
		return remitType;
	}

	public void setRemitType(Integer remitType) {
		this.remitType = remitType;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	
	
}
