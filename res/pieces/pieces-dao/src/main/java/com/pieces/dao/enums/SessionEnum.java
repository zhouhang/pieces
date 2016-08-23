package com.pieces.dao.enums;

/**
 * Author: koabs
 * 8/17/16.
 * 订单状态
 */
public enum SessionEnum {
    ORDER_TOKEN("token");

    private SessionEnum(String key) {
        this.key = key;
    }

    private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
