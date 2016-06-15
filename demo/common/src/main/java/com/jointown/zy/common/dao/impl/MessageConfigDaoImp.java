package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.MessageConfigDao;
import com.jointown.zy.common.model.MessageConfig;

/**
 * @ClassName: MessageConfigDaoImp
 * @Description: 短信通道配置DaoImp
 * @Author: ldp
 * @Date: 2015年9月7日
 * @Version: 1.0
 */
@Repository
public class MessageConfigDaoImp extends BaseDaoImpl implements
		MessageConfigDao {

	@Override
	public MessageConfig getAvailableChannel() throws Exception {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.MessageConfig.getAvailableChannel");
	}

}
