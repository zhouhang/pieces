package com.jointown.zy.common.dto;

public class WmsMessageWrapper<T> {
	private T data;
	private String dataId;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
}
