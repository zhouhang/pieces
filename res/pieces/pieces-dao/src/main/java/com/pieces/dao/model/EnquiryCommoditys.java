package com.pieces.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	//我方报价
	private Double myPrice;
	
	//过期时间
	private Date expireDate;
	
	//创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date createTime;

	// 商品图片
	private String pictureUrl;

	// 开票价
	private Double price;

	// 商品指导价
	private Double guidePrice;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


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

	public Double getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(Double myPrice) {
		this.myPrice = myPrice;
	}

	public Double getPrice() {
		if (price == null) {
			price = myPrice;
		}
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getGuidePrice() {
		return guidePrice;
	}

	public void setGuidePrice(Double guidePrice) {
		this.guidePrice = guidePrice;
	}
}