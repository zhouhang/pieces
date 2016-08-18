package com.pieces.dao.vo;

import com.pieces.dao.model.ShippingAddress;

public class ShippingAddressVo extends ShippingAddress{
	//地址全称
	private String fullAdd;

	public String getFullAdd() {
		return fullAdd;
	}

	public void setFullAdd(String fullAdd) {
		this.fullAdd = fullAdd;
	}
	
	
}