package com.jointown.zy.common.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信公众平台开发--微信接口业务处理Service
 * 
 * @author aizhengdong
 *
 * @data 2015年2月9日
 */
public interface WxInterfaceService {
	
	/**
	 * 验证签名
	 * @param request
	 * @return 结果值
	 */
	String checkSignature(HttpServletRequest request);
	
	/**
	 * 创建菜单
	 * @param request
	 * @return 结果值
	 */
	String createMenu(HttpServletRequest request);
	
	/**
	 * 处理微信发送的请求
	 * @param request
	 * @return 回复的消息内容
	 */
	String processRequest(HttpServletRequest request);

}
