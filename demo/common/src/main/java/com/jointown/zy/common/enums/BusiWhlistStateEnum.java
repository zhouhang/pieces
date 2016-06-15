package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BusiWhlistStateEnum {

	PLEDGED("0","未质押"),
	NOPLEDGED("1","已质押");
	
	private String id;
	private String label;
	private BusiWhlistStateEnum(){}
	private BusiWhlistStateEnum(String id, String label){
		this.id = id;
		this.label = label;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusiWhlistStateEnum item : BusiWhlistStateEnum.values()) {
			map.put(item.getId(), item.getLabel());
		}
		return map;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
