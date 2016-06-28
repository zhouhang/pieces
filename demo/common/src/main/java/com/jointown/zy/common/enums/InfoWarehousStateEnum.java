package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum InfoWarehousStateEnum {

	UNDISPOSE(0, "未处理"), 
	VALID(1, "有效"), 
	INVALID(2, "无效"), 
	USER_REVOCA(3,"用户撤销");

	private int status;
	private String statusDesc;

	private InfoWarehousStateEnum() {};

	private InfoWarehousStateEnum(int status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (InfoWarehousStateEnum status : InfoWarehousStateEnum.values()) {
			map.put(String.valueOf(status.getStatus()), status.getStatusDesc());
		}
		return map;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
