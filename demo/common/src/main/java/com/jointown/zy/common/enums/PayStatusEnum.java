package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 支付状态枚举
 * @author ldp
 * 2015-2-5
 */
public enum PayStatusEnum {
	UNPAY(0,"未支付"),
	SUCCESS(1,"支付成功"),
	FAILED(2,"支付失败"),
	DISPOSE(3,"处理中"),
	EXCEPTION(4,"支付异常");
	
	private int status;
	private String statusDesc;
	private PayStatusEnum(){};
	private PayStatusEnum(int status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
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
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (PayStatusEnum status : PayStatusEnum.values()) {
			map.put(String.valueOf(status.getStatus()), status.getStatusDesc());
		}
		return map;
	}
	
	/**
	 * 获取除未支付状态的所有值
	 * @return
	 */
	public static Map<String, String> getListQueryMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (PayStatusEnum status : PayStatusEnum.values()) {
			//alter by biran 20150515 不限制状态
//			if(status.getStatus()!=0){
//				map.put(String.valueOf(status.getStatus()), status.getStatusDesc());
//			}
			map.put(String.valueOf(status.getStatus()), status.getStatusDesc());
		}
		return map;
	}
}
