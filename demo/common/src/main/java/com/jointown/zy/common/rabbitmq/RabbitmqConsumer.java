/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jointown.zy.common.rabbitmq.RabbitmqDelivery;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * @ClassName: RabbitmqConsumer
 * @Description: 消费者实例类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqConsumer extends RabbitmqBase{
	private static final Log log = LogFactory.getLog(RabbitmqConsumer.class);

	/**
	 * Rabbitmq消费者实例化构造函数
	 * @param rountingKey 路由key
	 * @param queueName 队列名称
	 * @param exchangeName 交换器名称
	 */
	public RabbitmqConsumer(String rountingKey, String queueName, String exchangeName) {
		rabbitmqConfig = new RabbitmqConfig(rountingKey, queueName, exchangeName);
		init();
	}

	@Override
	protected RabbitmqParamerter initConnection(Connection conn) {
		String queueName = rabbitmqConfig.getQueueName();
		String exchangeName = rabbitmqConfig.getExchangeName();
		String keys = rabbitmqConfig.getKey();
		try {
			Channel channel = conn.createChannel();
			channel.exchangeDeclare(exchangeName, "direct", true); // 使用direct模式
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("x-ha-policy", "all");
			channel.queueDeclare(queueName, true, false, false, arguments);
			for(String key : StringUtils.split(keys, ",")){
				if (!StringUtils.isEmpty(key)) { // 消费者需要绑定key
					channel.queueBind(queueName, exchangeName, key);
				}
			}
			QueueingConsumer consumer = new QueueingConsumer(channel);
			//channel.basicConsume(queueName, false, consumer);
			RabbitmqParamerter mqParamerter = new RabbitmqParamerter(conn, channel, consumer); // 对于消费者，需要指定第三个参数consumer
			return mqParamerter;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}

	/**
	 * 获取消息
	 */
	public RabbitmqDelivery getMsg() {
		
		RabbitmqParamerter mqParamerter = getMQ();
		if (null == mqParamerter) {
			log.error(String.format("Can not get one connection to any mq"));
			return null;
		}
		
		QueueingConsumer consumer = mqParamerter.getConsumer();

		Delivery delivery = null;
		try {
			String consumerTag = mqParamerter.getChannel().basicConsume(rabbitmqConfig.getQueueName(), false, consumer);
			delivery = consumer.nextDelivery();
			mqParamerter.getChannel().basicCancel(consumerTag);
		} catch (Exception e) {
			log.error(e.toString());
		}
		
		RabbitmqDelivery mqDelivery = null;
		if (null != delivery) {
			mqDelivery = new RabbitmqDelivery();
			mqDelivery.setDelivery(delivery);
			mqDelivery.setRabbitmqParamerter(mqParamerter);
		}
		
		return mqDelivery;
	}
	
	/**
	 * ack
	 * @param deliveryTagId
	 */
	public void ack(RabbitmqDelivery mqDelivery) {
		
		Channel channel = null;
		try {
			channel = mqDelivery.getRabbitmqParamerter().getChannel();
			Delivery delivery = mqDelivery.getDelivery();
			long deliveryTagId = delivery.getEnvelope().getDeliveryTag();
			
			channel.basicAck(deliveryTagId, false);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * fail
	 * @param deliveryTagId
	 */
	public void fail(RabbitmqDelivery mqDelivery) {

		Channel channel = null;
		try {
			channel = mqDelivery.getRabbitmqParamerter().getChannel();
			Delivery delivery = mqDelivery.getDelivery();
			long deliveryTagId = delivery.getEnvelope().getDeliveryTag();
			
			//channel.basicReject(deliveryTagId, false); // 不将消息重新入队列，保存在服务器，下次重启时会再次进入队列消费
			channel.basicNack(deliveryTagId, false, true);//不通过的消息可以再次进入队列而不用重启服务
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	protected boolean isBroken(RabbitmqParamerter mqParamerter) {
		
		// 对于消费者，还需判断consumer是否正常
		Channel channel = mqParamerter.getChannel();
		QueueingConsumer consumer = mqParamerter.getConsumer();
		return (channel == null || !channel.isOpen() || consumer == null);
		
	}
}
