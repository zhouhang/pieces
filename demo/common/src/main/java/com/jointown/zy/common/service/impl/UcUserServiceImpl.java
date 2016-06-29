package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.Password;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.EncryptUtil;

@Service
public class UcUserServiceImpl implements UcUserService {

	@Autowired
	private UcUserDao ucUserDao;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	private final static Logger logger = LoggerFactory.getLogger(UcUserServiceImpl.class);
	
	@Override
	public void addUcUser(UcUser ucuser) {
		// 插入创建时间
		Date dt = new Date();
		ucuser.setCreateTime(dt);
		//密码加密,salt盐生成
		Password pass = EncryptUtil.JointownEncode(ucuser.getPassword());
		ucuser.setPassword(pass.getPassword());
		ucuser.setSalt(pass.getSalt());
		ucUserDao.addUcUser(ucuser);
	}

	@Override
	public UcUser findUcUserByUserName(String userName,boolean...ignoreStatus) {
		return ucUserDao.findUcUserByUserName(userName,ignoreStatus);
	}

	@Override
	public void updateUcUserPassword(UcUser ucuser) {
		ucUserDao.updateUcUserPassword(ucuser);
	}

	@Override
	public void updateUcUserEmail(UcUser ucuser) {
		ucUserDao.updateUcUserEmail(ucuser);
	}

	@Override
	public void updateUcUserInfo(UcUser ucuser) {
		//插入修改时间
		Date dt = new Date();
		ucuser.setUpdateTime(dt);
		ucUserDao.updateUcUserInfo(ucuser);
	}
	
	public int updatePhoneAndEmail(UcUser ucuser){
		return ucUserDao.updatePhoneAndEmail(ucuser);
	}
	
	
	@Override
	public void updateUcUserLoginInfo(UcUser ucuser) {
		//插入修改时间
		Date dt = new Date();
		ucuser.setUpdateTime(dt);
		ucUserDao.updateUcUserLoginInfo(ucuser);
	}


	@Override
	public void deleteUcUser(UcUser ucuser) {
		//ucUserDao.deleteUcUser(ucuser);
	}

	@Override
	public UcUser findUcUserById(Integer id) {
		return ucUserDao.findUcUserById(id);
	}

	@Override
	public void updateUcUserMobile(UcUser ucuser) {
		ucUserDao.updateUcUserMobile(ucuser);
	}

	@Override
	public void updateUcUserCompany(UcUser ucuser) {
		ucUserDao.updateUcUserCompany(ucuser);		
	}

	@Override
	public List<UcUser> findUcUserByMobile(String mobile) {
		return ucUserDao.findMemberMobile(mobile);
	}
	
	
	@Override
	public void sendMessage(String userId,String message,String...logErrorPrefix){
		try {
			UcUser ucUser = ucUserDao.findMemberByUserId(userId);
			if(ucUser!=null){
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(),message,logErrorPrefix));
			}
		} catch (Exception e) {
			logger.error(logErrorPrefix + e.getMessage());
		}
	}
	
	/*-- added by biran 为用户绑定业务员 20150805  --*/
	@Override
	public void updateMemberSalesMan(UcUser ucuser) {
		ucUserDao.updateMemberSalesMan(ucuser);
		
		return ;
	}

	@Override
	public String getCertifyNameByUserId(Long userId) {
		return ucUserDao.getCertifyNameByUserId(userId);
	}
	
	/**
	 * 微信添加用户记录
	 * @param ucser
	 * @author lichenxiao
	 * @see UcUserService
	 * @param 
	 * @time 2015年7月15日
	 */
	@Override
	public void wxAddUcUser(UcUser ucuser) {
		// 插入创建时间
		Date dt = new Date();
		ucuser.setCreateTime(dt);
		//密码加密,salt盐生成
		Password pass = EncryptUtil.JointownEncode(ucuser.getPassword());
		ucuser.setPassword(pass.getPassword());
		ucuser.setSalt(pass.getSalt());
		ucUserDao.wxAddUcUser(ucuser);
		
	}
}
