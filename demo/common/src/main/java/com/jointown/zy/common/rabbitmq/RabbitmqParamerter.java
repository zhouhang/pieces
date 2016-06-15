/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.Serializable;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @ClassName: RabbitmqParamerter
 * @Description: Rabbitmq连接，信道，及队列绑定的消费者的实体类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqParamerter implements Serializable{
	
	private static final long serialVersionUID = -8396434169272708537L;

	/** 物理连接 */
	private Connection connection;
	
	/** 物理连接上的信道 */
	private Channel channel;

	/** 队列绑定的消费者 */
	private QueueingConsumer consumer;
	
	/**
	 * 实例化构造函数
	 */
	public RabbitmqParamerter() {
	}
	
	/**
	 * 带参数的实例化构造函数
	 */
	public RabbitmqParamerter(Connection connection, Channel channel,
			QueueingConsumer consumer) {
		this.connection = connection;
		this.channel = channel;
		this.consumer = consumer;
	}

	/**
	 * 获取物理连接
	 * @return 物理连接
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * 设定物理连接
	 * @param connection 物理连接
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 获取物理连接上的信道
	 * @return 物理连接上的信道
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * 设定物理连接上的信道
	 * @param channel 物理连接上的信道
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * 获取队列绑定的消费者
	 * @return 队列绑定的消费者
	 */
	public QueueingConsumer getConsumer() {
		return consumer;
	}

	/**
	 * 设定队列绑定的消费者
	 * @param consumer 队列绑定的消费者
	 */
	public void setConsumer(QueueingConsumer consumer) {
		this.consumer = consumer;
	}
	
}
