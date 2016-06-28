package com.jointown.zy.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RemitStatusEnum
 * @Description: 划账流水状态枚举
 * @Author: ldp
 * @Date: 2015年7月1日
 * @Version: 1.0
 */
public enum RemitStatusEnum {
	UN_REMIT(0,"未划账"),
	REMIT_SUCCESS(1,"划账成功"),
	REMIT_FAILED(2,"划账失败"),
	REMIT_REFUSE(3,"拒绝"),
	REMIT_EXCEPTION(4,"划账异常");
	
	public int status;
	public String title;
	
	private RemitStatusEnum(int status, String title) {
		this.status = status;
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Map<String,String> toMap(){
		Map<String,String> map = new HashMap<String, String>(); 
		for(RemitStatusEnum status:RemitStatusEnum.values()){
			map.put(String.valueOf(status.getStatus()), status.getTitle());
		}
		return map;
	}

}
