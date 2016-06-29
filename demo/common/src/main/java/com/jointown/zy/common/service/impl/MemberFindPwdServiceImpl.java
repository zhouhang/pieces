package com.jointown.zy.common.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.Password;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.service.IMemberFindPwdService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.EmailUtils;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.LxzkSendMessage;

/**
 * 找回密码
 * author ldp
 * 2014年12月10日
 */
@Service
public class MemberFindPwdServiceImpl implements IMemberFindPwdService {

	@Autowired
	public UcUserService ucUserService;
	@Autowired
	public UcUserDao ucUserDao;
	@Autowired
	public RedisManager redisManager;
	
	@Override
	public String getMobileCode(String mobileNo,String mobileCode){
		String sendFlag = LxzkSendMessage.sendMessage(mobileNo, GetMessageContext.getLxzkMobileCodeMsg(mobileCode));
		if ("success".equals(sendFlag)) {
			return "y";
		}
		return "n";
	}

	@Override
	public String findPasswordByEmail(String userName) {
		String email = ucUserService.findUcUserByUserName(userName).getEmail();
		if (null == email || "".equals(email)) {
			return "该用户未绑定邮箱";
		}
		UUID emailUUID = UUID.randomUUID();
		redisManager.set(String.valueOf(emailUUID),userName, 12*60*60);
		//发送邮件
		String context = GetEmailContext.getFindPwdEmailText(String.valueOf(emailUUID));
		if (EmailUtils.sendMail(GetEmailContext.EMAIL_SUBJECT_FIND_PWD,email,context)) {
			return "success";//发送成功
		}else {
			return "failed";//发送失败
		}
		
	}

	@Override
	public String verityEmail(String key) {
		String getResdisName = redisManager.get(key);
		if (null == getResdisName ) {
			return "找回密码链接地址已过期!";
		}
		return "success";
	}

	@Override
	public String modifyPwd(String memberName,String password) {
		UcUser ucUser = new UcUser();
		Password passwordObject = EncryptUtil.JointownEncode(password);
		ucUser.setUserName(memberName);
		ucUser.setPassword(passwordObject.getPassword());
		ucUser.setSalt(passwordObject.getSalt());
		return String.valueOf(ucUserDao.updateUcUserPassword(ucUser));
	}

	@Override
	public List<UcUser> findMemberMobile(String mobile) {
		return ucUserDao.findMemberMobile(mobile);
	}
	@Override
	public List<UcUser> findMemberByMobileAndUsername(Map<Object,Object> queryMap) {
		return ucUserDao.findMemberByMobileAndUsername(queryMap);
	}

	
}
