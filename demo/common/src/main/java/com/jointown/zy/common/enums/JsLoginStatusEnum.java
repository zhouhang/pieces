package com.jointown.zy.common.enums;

/**
 * @ClassName: JsLoginStatusEnum
 * @Description: js登陆通用状态
 * @Author: guoyb
 * @Date: 2015年6月8日
 * @Version: 1.0
 */
public enum JsLoginStatusEnum {
	/** 未登录 */
	UNLOGIN("0","未登录"),
	/** 未认证*/
	UNANTHENTICATION("1","未认证"),
	/** 请求controller成功 */
	STATUS_OK("2","处理成功"),
	/** 请求controller成功，但是发生异常 */
	STATUS_ERROR("3","请求controller成功，但是发生异常 ");
	
	/** status值 */
	private String status;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/** 描述 */
	private String desc;
	
	private JsLoginStatusEnum(){}
	private JsLoginStatusEnum(String status,String desc){
		this.status = status;
		this.desc = desc;
	}
}
