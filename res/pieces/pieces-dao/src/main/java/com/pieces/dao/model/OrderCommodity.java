package com.pieces.dao.model;

import com.pieces.dao.group.Biz;
import com.pieces.dao.group.Boss;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


/**
 * 订单商品表
 */
public class OrderCommodity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(groups = {Boss.class})
	private String name;
	@NotEmpty(groups = {Boss.class})
	private String spec;
	@NotEmpty(groups = {Boss.class})
	private String level;
	@NotEmpty(groups = {Boss.class})
	private String originOf;
	@NotNull(groups = {Boss.class})
	private Integer amount;
	@NotNull(groups = {Boss.class})
	private Double price;

	// 销售价
	private Double guidePrice;

	private Double subtotal;

	// 询价商品ID
	private Integer enquiryCommodityId;
	
	private Integer orderId;

	// 商品Id
	private Integer commodityId;

	// 商品图片
	private String pictureUrl;
	
	public OrderCommodity(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getOriginOf() {
		return originOf;
	}

	public void setOriginOf(String originOf) {
		this.originOf = originOf;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	public Integer getEnquiryCommodityId() {
		return enquiryCommodityId;
	}

	public void setEnquiryCommodityId(Integer enquiryCommodityId) {
		this.enquiryCommodityId = enquiryCommodityId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getGuidePrice() {
		return guidePrice;
	}

	public void setGuidePrice(Double guidePrice) {
		this.guidePrice = guidePrice;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}