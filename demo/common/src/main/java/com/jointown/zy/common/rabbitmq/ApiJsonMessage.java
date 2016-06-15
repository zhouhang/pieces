/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jointown.zy.common.enums.ApiFlagEnums;

/**
 * @ClassName: ApiJsonMessage
 * @Description: API消息实体类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class ApiJsonMessage implements Serializable{

	private static final long serialVersionUID = 8977049839215557063L;
	
	/** 接口标识 */
	private ApiFlagEnums apiFlag;
	
	/** 消息内容 */
	private String apiJsonData;
	
	/**
	 * 构造函数
	 */
	public ApiJsonMessage(){}
	
	/**
	 * 带参数构造函数
	 */
	public ApiJsonMessage(ApiFlagEnums apiFlag, String apiJsonData) {
		super();
		this.apiFlag = apiFlag;
		this.apiJsonData = apiJsonData;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 获取接口标识
	 * @return 接口标识
	 */
	public ApiFlagEnums getApiFlag() {
		return apiFlag;
	}

	/**
	 * 设定接口标识
	 * @param apiFlag 接口标识
	 */
	public void setApiFlag(ApiFlagEnums apiFlag) {
		this.apiFlag = apiFlag;
	}

	/**
	 * 获取消息内容
	 * @return 消息内容
	 */
	public String getApiJsonData() {
		return apiJsonData;
	}

	/**
	 * 设定消息内容
	 * @param apiJsonData 消息内容
	 */
	public void setApiJsonData(String apiJsonData) {
		this.apiJsonData = apiJsonData;
	}
	
	
	
}
