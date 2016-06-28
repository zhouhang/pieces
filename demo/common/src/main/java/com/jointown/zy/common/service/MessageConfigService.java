package com.jointown.zy.common.service;

import com.jointown.zy.common.model.MessageConfig;

/**
 * @ClassName: MessageConfigService
 * @Description: 短信通道配置Service
 * @Author: ldp
 * @Date: 2015年9月7日
 * @Version: 1.0
 */
public interface MessageConfigService {
	/**
	 * @Description:获取可用的短信通道
	 * @Author: ldp
	 * @Date: 2015年9月7日
	 * @return
	 * @throws Exception
	 */
	public MessageConfig getAvailableChannel() throws Exception;
}
