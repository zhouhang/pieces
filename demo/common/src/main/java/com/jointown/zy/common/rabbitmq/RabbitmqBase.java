/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.util.ArrayList;
import java.util.List;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName: RabbitmqBase
 * @Description: Rabbitmq生产者及消费者的基类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public abstract class RabbitmqBase {
	
	/**
	 * 存储所有到MQ的连接
	 */
	protected List<RabbitmqParamerter> mqParamerters = new ArrayList<RabbitmqParamerter>();
	
	/**
	 * 存储mq的队列的配置信息
	 */
	protected RabbitmqConfig rabbitmqConfig = null;
	
	/**
	 * 设定Rabbitmq配置信息
	 * @param rabbitmqConfig 配置信息
	 */
	public void setRabbitmqQueueConfig(RabbitmqConfig rabbitmqConfig) {
		this.rabbitmqConfig = rabbitmqConfig;
	}
	
	/**
	 * 初始化MQ的连接
	 */
	public void init() {
		mqParamerters.clear(); // 清空当前连接
		RabbitmqConnection connections = RabbitmqConnection.getInstance();
		for(Connection conn : connections.getConnections()){
			RabbitmqParamerter mqParamerter = initConnection(conn);
			if (null == mqParamerter) {
				mqParamerter = new RabbitmqParamerter(conn,null, null);
			}
			mqParamerters.add(mqParamerter);
		}
		
	}
	
	protected RabbitmqParamerter getMQ() {
		//主从方式（默认第一个为主连接，主连接出问题启用副连级）
		int curPosition = 0;
		RabbitmqParamerter mqParamerter = null;
		while (null == mqParamerter) {
			
			// 首先获取一个连接，如果连接有问题就尝试修复，如果修复不成功就获取下一个连接，依此类推直到找到正常的连接
			// 如果找不到这样的连接，就抛出异常
			mqParamerter = mqParamerters.get(curPosition++);
			if (isBroken(mqParamerter)) { // 连接有问题，修复连接
				mqParamerter = fixRabbitmq(mqParamerter);
			}
			
			if(curPosition >= mqParamerters.size()){
				break;
			}
		}
		
		return mqParamerter;
	}
	
	/**
	 * 获取到MQ服务器的连接
	 * @param rabbitmqConfig
	 * @return
	 */
	protected abstract RabbitmqParamerter initConnection(Connection conn);
	
	/**
	 * 连接是否坏掉
	 * @return
	 */
	protected boolean isBroken(RabbitmqParamerter mqParamerter) {

		Channel channel = mqParamerter.getChannel();
		return (channel == null || !channel.isOpen());
	}
	
	/**
	 * 修复连接
	 * @param mqConnection
	 */
	protected RabbitmqParamerter fixRabbitmq(RabbitmqParamerter mqParamerter) {
		
		RabbitmqParamerter fixRabbitmq = initConnection(mqParamerter.getConnection());
		if (null != fixRabbitmq) {
			mqParamerter.setChannel(fixRabbitmq.getChannel());
			mqParamerter.setConsumer(fixRabbitmq.getConsumer());
			return mqParamerter;
		}
		return null;
	}
}
