package com.pieces.dao.vo;

import com.pieces.dao.model.ShippingAddress;

public class ShippingAddressVo extends ShippingAddress{
	//地址全称
	private String fullAdd;

	// 省份ID
	private Integer provinceId;

	// 区域ID
	private String cityId;

	public String getFullAdd() {
		return fullAdd;
	}

	public void setFullAdd(String fullAdd) {
		this.fullAdd = fullAdd;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}