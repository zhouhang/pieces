package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.MessageConfig;

/**
 * @ClassName: MessageConfigDao
 * @Description: 短信通道配置Dao
 * @Author: ldp
 * @Date: 2015年9月7日
 * @Version: 1.0
 */
public interface MessageConfigDao {

	/**
	 * @Description: 获取可用的短信通道
	 * @Author: ldp
	 * @Date: 2015年9月7日
	 * @return
	 * @throws Exception
	 */
	public MessageConfig getAvailableChannel() throws Exception;
}
