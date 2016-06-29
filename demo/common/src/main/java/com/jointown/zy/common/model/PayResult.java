package com.jointown.zy.common.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: PayResult
 * @Description: 支付结果model
 * @Author: ldp
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
public class PayResult {
	/**支付结果ID*/
	private Integer resultId;
	/**支付系统标识 0-交易系统*/
	private Integer sourceSys;
	/**支付款项 0-保证金 1-尾款 2-全款*/
	private Integer amtType;
	/**交易订单号*/
	private String orderId;
	/**交易金额*/
	private BigDecimal amount;
	/**处理状态 0-待处理 1-已处理*/
	private Integer status;
	/**支付流水号*/
	private BigDecimal flowId;
	/**创建时间*/
	private Date createTime;
	/**处理时间*/
	private Date doneTime;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public Integer getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(Integer sourceSys) {
		this.sourceSys = sourceSys;
	}
	public Integer getAmtType() {
		return amtType;
	}
	public void setAmtType(Integer amtType) {
		this.amtType = amtType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFlowId() {
		return flowId;
	}
	public void setFlowId(BigDecimal flowId) {
		this.flowId = flowId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}
	
	

}
