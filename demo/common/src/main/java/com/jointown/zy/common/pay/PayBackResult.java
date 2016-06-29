package com.jointown.zy.common.pay;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付流水订单返回对象
 * @author ldp
 * 2015-2-5
 */
public class PayBackResult {
	private String orderId;
	private BigDecimal amt;
	private Date payTime;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
}
