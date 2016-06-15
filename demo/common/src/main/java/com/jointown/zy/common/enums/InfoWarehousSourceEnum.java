package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum InfoWarehousSourceEnum {

	WEIXIN("微信", "微信"), 
	//DONGFANGZYC("东方中药材网", "东方中药材网"), 
	ZHENYC("珍药材", "珍药材"), 
	BOSS("客服","客服");

	private String status;
	private String statusDesc;

	private InfoWarehousSourceEnum() {};

	private InfoWarehousSourceEnum(String status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (InfoWarehousSourceEnum status : InfoWarehousSourceEnum.values()) {
			map.put(String.valueOf(status.getStatus()), status.getStatusDesc());
		}
		return map;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
