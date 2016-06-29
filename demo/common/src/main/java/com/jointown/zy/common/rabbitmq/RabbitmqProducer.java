/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName: RabbitmqProducer
 * @Description: Rabbitmq生产者实例类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqProducer extends RabbitmqBase {
	
	private static final Log log = LogFactory.getLog(RabbitmqProducer.class);
	
	/**
	 * 构造函数
	 * @param exchangeName 交换器名称
	 */
	public RabbitmqProducer(String exchangeName) {
		rabbitmqConfig = new RabbitmqConfig(null, "", exchangeName);
		init();
	}
	
	@Override
	protected RabbitmqParamerter initConnection(Connection conn) {
		String exchangeName = rabbitmqConfig.getExchangeName();
		try {
			Channel channel = conn.createChannel();
			channel.exchangeDeclare(exchangeName, "direct", true); // 使用direct模式
			RabbitmqParamerter mqParamerter = new RabbitmqParamerter(conn, channel, null); // 对于生产者，第三个参数consumer为空
			return mqParamerter;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 发布消息
	 * @param msg
	 * @param key
	 */
	public void putMsg(byte[] msg, String key) {
		
		RabbitmqParamerter Paramerter = getMQ();
		if (null == Paramerter) {
			log.error(String.format("Can not get one connection to any mq, message is thrown, message is %s, key is %s", msg, key));
			return;
		}
		
		try {
			Channel channel = Paramerter.getChannel();
			String exchangeName = rabbitmqConfig.getExchangeName();
			channel.basicPublish(exchangeName, key, null, msg);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
}

