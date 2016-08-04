package com.pieces.service.enums;

public enum CategoryEnum {
	STATUS_VALID(1,"有效"),
	STATUS_INVALID(0,"无效"),
	LEVEL_CATEGORY(1,"category"),
	LEVEL_BREED(2,"breed");

	private CategoryEnum(Integer value, String text) {
		this.value = value;
		this.text = text;
	}
	
	private Integer value;
	private String text;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
