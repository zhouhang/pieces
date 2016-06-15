/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.util.SpringUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName: RabbitmqConnection
 * @Description: 用于获取Rabbitmq物理连接
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqConnection {
	private final static Logger logger = LoggerFactory.getLogger(RabbitmqConnection.class);
	
	protected List<Connection> mqConnections = new ArrayList<Connection>();
	
	private static RabbitmqConnection rabbitmqConnection;
	
	/**
	 * @Description: 获取RabbitmqConnection的单例
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static RabbitmqConnection getInstance(){
		if(rabbitmqConnection == null){
			synchronized (RabbitmqConnection.class) {
				if(rabbitmqConnection == null){
					rabbitmqConnection = new RabbitmqConnection();
				}
			}
		}
		return rabbitmqConnection;
	}
	
	private RabbitmqConnection(){
		mqConnections.clear(); // 清空当前连接
		
		// 读取属性文件的配置信息
		Properties config = SpringUtil.getConfigProperties();
		
		// 实例化Connection
		String hosts = (String) config.get("rabbitmq.host");
		for(String host : hosts.split(",")){
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(Integer.valueOf((String) config.get("rabbitmq.port")));
			factory.setUsername((String) config.get("rabbitmq.username"));
			factory.setPassword((String) config.get("rabbitmq.password"));
			Connection conn = null;
			try {
				conn = factory.newConnection();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			mqConnections.add(conn);
		}
	}
	
	/**
	 * @Description: 获取连接
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @return
	 */
	public List<Connection> getConnections(){
		return mqConnections;
	}
	
}
