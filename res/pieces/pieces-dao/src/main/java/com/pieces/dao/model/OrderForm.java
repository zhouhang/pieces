package com.pieces.dao.model;

import com.pieces.dao.config.SystemConfig;

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

	private Integer createMember;
	
	//付款日期
	private Date paymentDate;

	// 保证金
	private Double deposit;

	// 代理商id
	private Integer agentId;

	// 发货时间
	private Date deliveryDate;

	// 订单过期时间
	private Date expireDate;

	// 订单完成时间
	private Date finishDate;

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

	public Integer getCreateMember() {
		return createMember;
	}

	public void setCreateMember(Integer createMember) {
		this.createMember = createMember;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getExpireDate() {
		if (expireDate == null && createrTime!= null) {
			// 10天的间隔 转换成毫秒
			Long intervals = Long.valueOf(SystemConfig.orderValidityPeriod * 24 * 60 * 60 * 1000L);
			expireDate = new Date();
			expireDate.setTime(this.getCreaterTime().getTime() + intervals);
		}
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
}