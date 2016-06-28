package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemitResult implements Serializable {

	private static final long serialVersionUID = 1L;
	//结果表ID(主键)
	private Long resultId;
	//来源系统 0-交易系统
	private Integer sourcesys;
	//划账类型：0-订单完成分润  1-保证金处理
	private Integer remitType;
	//交易订单号
	private String orderId;
	//划账流水号
	private Long flowId;
	//创建时间
	private Date createTime;
	//处理时间
	private Date doneTime;
	//处理状态
	private Integer status;
	//划账返回状态
	private Integer oprateStatus;
	//划账完成时间
	private Date remitTime;
	
	public RemitResult() {
		super();
	}
	private RemitResult(Long resultId, Integer sourcesys, Integer remitType,
			String orderId, Long flowId,
			Date createTime, Date doneTime, Integer status,
			Integer oprateStatus, Date remitTime) {
		super();
		this.resultId = resultId;
		this.sourcesys = sourcesys;
		this.remitType = remitType;
		this.orderId = orderId;
		this.flowId = flowId;
		this.createTime = createTime;
		this.doneTime = doneTime;
		this.status = status;
		this.oprateStatus = oprateStatus;
		this.remitTime = remitTime;
	}

	public Long getResultId() {
		return resultId;
	}
	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}
	public Integer getSourcesys() {
		return sourcesys;
	}
	public void setSourcesys(Integer sourcesys) {
		this.sourcesys = sourcesys;
	}
	public Integer getRemitType() {
		return remitType;
	}
	public void setRemitType(Integer remitType) {
		this.remitType = remitType;
	}
	public String getOrderid() {
		return orderId;
	}
	public void setOrderid(String orderId) {
		this.orderId = orderId;
	}
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOprateStatus() {
		return oprateStatus;
	}
	public void setOprateStatus(Integer oprateStatus) {
		this.oprateStatus = oprateStatus;
	}
	public Date getRemitTime() {
		return remitTime;
	}
	public void setRemitTime(Date remitTime) {
		this.remitTime = remitTime;
	}
}
