package com.jointown.zy.common.config;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.util.SpringUtil;

/**
 * 
 * 描述： 用于页面获取不同工程的url
 * 
 * 日期： 2014年12月22日
 * 
 * 作者： 赵航
 *
 * 版本： V1.0
 */
public class BrokerServerUrl {
	
	public BrokerServerUrl() {
	}
	
	/**
	 * 获取usercenter的url
	 */
	public String getUserCenter(){
		return getBrokerUrl("jointown.usercenter.method", "jointown.usercenter.host", "jointown.usercenter.port");
	}
	
	/**
	 * 获取passport的url
	 */
	public String getPassport(){
		return getBrokerUrl("jointown.passport.method", "jointown.passport.host", "jointown.passport.port");
	}
	
	/**
	 * 获取static的url
	 */
	public String getStatic(){
		return getBrokerUrl("jointown.static.method", "jointown.static.host", "jointown.static.port");
	}
	
	private String getBrokerUrl(String methodKey, String hostKey, String portKey){
		String method = SpringUtil.getConfigProperties(methodKey);
		String host = SpringUtil.getConfigProperties(hostKey);
		String port = SpringUtil.getConfigProperties(portKey);
		if(StringUtils.isEmpty(method)){
			method = "http";
		}
		if(StringUtils.isEmpty(host)){
			host = "localhost";
		}
		if(StringUtils.isEmpty(port)){
			//port = "80";
			port = "";
		}
		StringBuilder sbd = new StringBuilder();
		sbd.append(method);
		sbd.append("://");
		sbd.append(host);
		sbd.append(":");
		sbd.append(port);
		return sbd.toString();
	}
}
