package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class OrderForm  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//订单编号
	private String code;
	
	//运费
	private Float shippingCosts;
	
	//对应下单地址记录里面的id
	private Integer addrHistoryId;
	
	//用户id
	private Integer userId;
	
	//备注
	private String remark;
	
	//实际应付
	private  Double amountsPayable;
	
	//商品合计
	private  Double sum;
	
	//发票id
	private Integer invoiceId;
	
	//订单状态
	private Integer status;
	
	//下单日期
	private Date createrTime;
	
	//付款日期
	private Date paymentDate;
	
	public OrderForm(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Float getShippingCosts() {
		return shippingCosts;
	}

	public void setShippingCosts(Float shippingCosts) {
		this.shippingCosts = shippingCosts;
	}
	
	public Integer getAddrHistoryId() {
		return addrHistoryId;
	}

	public void setAddrHistoryId(Integer addrHistoryId) {
		this.addrHistoryId = addrHistoryId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getAmountsPayable() {
		return amountsPayable;
	}

	public void setAmountsPayable(Double amountsPayable) {
		this.amountsPayable = amountsPayable;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}