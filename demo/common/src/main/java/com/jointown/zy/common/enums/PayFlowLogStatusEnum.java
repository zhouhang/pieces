package com.jointown.zy.common.enums;

/**
 * @ClassName: PayFlowLogStatusEnum
 * @Description: 支付流水日志状态枚举
 * @Author: ldp
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
public enum PayFlowLogStatusEnum {

	FAIL(0,"失败"),
	SUCCESS(1,"成功");
	private int status;
	private String title;
	private PayFlowLogStatusEnum(){}
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
	private PayFlowLogStatusEnum(int status, String title) {
		this.status = status;
		this.title = title;
	}
	
}
