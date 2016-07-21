package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class EnquiryCommoditys  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//用户ID
	private Integer userId;
	
	//询价单ID
	private Integer billsId;
	
	private Integer commodityId;
	
	//商品名称
	private String commodityName;
	
	//商品规格
	private String specs;
	
	//等级
	private String level;
	
	//产地
	private String origin;
	
	//数量
	private Integer amount;
	
	//期望价格
	private Float expectPrice;
	
	//期望交货日期
	private Date expectDate;
	
	//我方报价
	private Float myPrice;
	
	//过期时间
	private Date expireDate;
	
	//创建时间
	private Date createTime;
	
	public EnquiryCommoditys(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getBillsId() {
		return billsId;
	}

	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
	}
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	
	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Float getExpectPrice() {
		return expectPrice;
	}

	public void setExpectPrice(Float expectPrice) {
		this.expectPrice = expectPrice;
	}
	
	public Date getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(Date expectDate) {
		this.expectDate = expectDate;
	}
	
	public Float getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(Float myPrice) {
		this.myPrice = myPrice;
	}
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}