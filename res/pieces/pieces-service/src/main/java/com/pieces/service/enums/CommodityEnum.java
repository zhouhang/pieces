package com.pieces.service.enums;

public enum CommodityEnum {
	COMMODITY_SPECIFICATIONS(10000,"切制规格"),
	COMMODITY_PLACE(10001,"原药产地"),
	COMMODITY_LEVEL(10002,"等级");
	
	private CommodityEnum(Integer value,String text) {
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
