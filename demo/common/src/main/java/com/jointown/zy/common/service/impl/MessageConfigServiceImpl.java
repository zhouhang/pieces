package com.jointown.zy.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.MessageConfigDao;
import com.jointown.zy.common.model.MessageConfig;
import com.jointown.zy.common.service.MessageConfigService;
/**
 * @ClassName: MessageConfigServiceImpl
 * @Description: 短信配置ServiceImpl
 * @Author: ldp
 * @Date: 2015年9月7日
 * @Version: 1.0
 */
@Service
public class MessageConfigServiceImpl implements MessageConfigService {

	private static final Logger log = LoggerFactory.getLogger(MessageConfigServiceImpl.class);
	
	@Autowired
	private MessageConfigDao messageConfigDao;
	
	@Override
	public MessageConfig getAvailableChannel() throws Exception {
		return messageConfigDao.getAvailableChannel();
	}

}
