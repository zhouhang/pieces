package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class ShippingAddress  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer userId;
	
	//是否默认地址1 默认
	private Boolean isDefault;
	
	//收货人
	private String consignee;
	
	//手机号码
	private String tel;
	
	//所在区域
	private String area;
	
	//详细地址
	private String detail;
	
	private Date createTime;
	
	//别名
	private String aliases;
	
	public ShippingAddress(){}
	
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
	
	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	
}