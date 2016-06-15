/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.Serializable;

import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * @ClassName: RabbitmqDelivery
 * @Description: Rabbitmq数据封装类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqDelivery implements Serializable{

	private static final long serialVersionUID = -6235955290151730870L;

	/** 实际的数据 */
	private Delivery delivery;

	/** Rabbitmq连接，信道，及队列绑定的消费者的实体类 */
	private RabbitmqParamerter rabbitmqParamerter;
	
	/**
	 * 构造函数
	 */
	public RabbitmqDelivery() {
		super();
	}

	/**
	 * 带参数的构造函数
	 */
	public RabbitmqDelivery(Delivery delivery, RabbitmqParamerter rabbitmqParamerter) {
		super();
		this.delivery = delivery;
		this.rabbitmqParamerter = rabbitmqParamerter;
	}

	/**
	 * 获取实际的数据
	 * @return 实际的数据
	 */
	public Delivery getDelivery() {
		return delivery;
	}

	/**
	 * 设定实际的数据
	 * @param delivery 实际的数据
	 */
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	/**
	 * 获取Rabbitmq连接，信道，及队列绑定的消费者的实体类
	 * @return Rabbitmq连接，信道，及队列绑定的消费者的实体类
	 */
	public RabbitmqParamerter getRabbitmqParamerter() {
		return rabbitmqParamerter;
	}

	/**
	 * 设定Rabbitmq连接，信道，及队列绑定的消费者的实体类
	 * @param rabbitmqParamerter Rabbitmq连接，信道，及队列绑定的消费者的实体类
	 */
	public void setRabbitmqParamerter(RabbitmqParamerter rabbitmqParamerter) {
		this.rabbitmqParamerter = rabbitmqParamerter;
	}
	
	
}
