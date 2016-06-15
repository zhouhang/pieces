package com.jointown.zy.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dao.WxUserDao;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.WxUserService;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.LxzkSendMessage;
import com.jointown.zy.common.util.MessageSend;

@Service
public class WxUserServiceImpl implements WxUserService {
	
	private static final Logger log = LoggerFactory.getLogger(WxUserServiceImpl.class);
	
	@Autowired
	private WxUserDao wxUserDao;
	
	@Autowired
	private UcUserDao ucUserDao;
	
	@Override
	public UcUser findByCondition(String userName) {
		if(userName==null){
			log.error("WxUserServiceImpl.findByCondition:userName不允许为空.");
			return null;
		}
		return wxUserDao.findByCondition(userName);
	}

	/**
	 * add by 宋威 2015-7-17
	 * 根据挂牌ID查询UcUser信息
	 */
	@Override
	public UcUser getUcUserByListingId(String listingId) {
		if(listingId==null){
			log.error("WxUserServiceImpl.getUcUserByListingId:listingId不允许为空.");
			return null;
		}
		return ucUserDao.getUcUserByListingId(listingId);
	}
	
	@Override
	public String getMobileCode(String mobileNo,String mobileCode){
		String sendFlag = LxzkSendMessage.sendMessage(mobileNo, GetMessageContext.getLxzkMobileCodeMsg(mobileCode));
		if ("success".equals(sendFlag)) {
			return "y";
		}
		return "n";
	}
}
