package com.jointown.zy.common.enums;


/**
 * @ClassName: PayResulStatusEnum
 * @Description: 支付结果处理状态枚举
 * @Author: ldp
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
public enum PayResultStatusEnum {

	UNDONE(0,"待处理"),
	DONE(1,"已处理");
	
	private int status;
	private String statusDes;
	
	private PayResultStatusEnum(){}
	
	private PayResultStatusEnum(int status, String statusDes) {
		this.status = status;
		this.statusDes = statusDes;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDes() {
		return statusDes;
	}
	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}
	
}
