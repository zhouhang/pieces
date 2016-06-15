/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.Serializable;

/**
 * @ClassName: RabbitmqConfig
 * @Description: Rabbitmq配置信息实体类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqConfig implements Serializable{

	private static final long serialVersionUID = 5541316386468333468L;

	/** 绑定的key */
	private String key;
	
	/** 绑定的Queue的名称 */
	private String queueName;
	
	/** exchange名称 */
	private String exchangeName;

	/**
	 * 构造函数
	 */
	public RabbitmqConfig() {
		super();
	}

	/**
	 * 带参数的构造函数
	 */
	public RabbitmqConfig(String key, String queueName, String exchangeName) {
		super();
		this.key = key;
		this.queueName = queueName;
		this.exchangeName = exchangeName;
	}

	/**
	 * 获取绑定的key
	 * @return 绑定的key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设定绑定的key
	 * @param key 绑定的key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取绑定的Queue的名称
	 * @return 绑定的Queue的名称
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * 设定绑定的Queue的名称
	 * @param queueName 绑定的Queue的名称
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * 获取exchange名称
	 * @return exchange名称
	 */
	public String getExchangeName() {
		return exchangeName;
	}

	/**
	 * 设定exchange名称
	 * @param exchangeName exchange名称
	 */
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

}