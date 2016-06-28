package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.MemberMotifyDto;
import com.jointown.zy.common.dto.MemberSearchDto;
import com.jointown.zy.common.dto.Password;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.IMemberManageService;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.GetInitPassword;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.vo.BossUserVo;

/**
 * 
 * @author ldp 2014-11-20
 */
@Service
public class MemberManageServiceImpl implements IMemberManageService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberManageServiceImpl.class);

	@Autowired
	public UcUserDao ucUserDao;
	
	@Autowired
	public ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	public MessageConfigManage messageConfigManage;

	@Override
	public int addMember(UcUser ucUser) {
		// 会员创建时间
		Date dt = new Date();
		ucUser.setCreateTime(dt);

		// 获取密码加密处理
		String tempPassWord = ucUser.getPassword();
		Password pass = EncryptUtil.JointownEncode(tempPassWord);
		ucUser.setPassword(pass.getPassword());
		ucUser.setSalt(pass.getSalt());
		
		int successFlag = 0;
		//先查询该用户是否存在，存在update
		/*UcUser selectUcUser = ucUserDao.findMemberByAllUserName(ucUser.getUserName());
		if (null != selectUcUser) {//如果该会员名存在且已删除，则update
			log.info("execute update member method!");
			successFlag = ucUserDao.updateMemberByUserName(ucUser);
		}else {
			log.info("execute insert member method!");
			successFlag = ucUserDao.addUcUser(ucUser);
		}*/		
		log.info("execute insert member method!");
		successFlag = ucUserDao.addUcUser(ucUser);
		// 会员添加成功之后发送短信
		if (successFlag > 0) {
			log.info("添加成功");
			/*taskExecutor.execute(new MessageTaskSend(ucUser.getMobile(),
							GetMessageContext.getAddMemberMessageContext(
									ucUser.getUserName(), tempPassWord)));*/
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(
					ucUser.getMobile(),
					GetMessageContext.getAddMemberMessageContext(
							ucUser.getUserName(), tempPassWord)));
			log.info("mobile msg send...");
		}
		return successFlag;
	}

	@Override
	public UcUser findMemberByUserID(String userId) {
		return ucUserDao.findMemberByUserId(userId);
	}
	
	@Override
	public UcUser getUcUserById(Integer id){
		return ucUserDao.getUcUserById(id);
	}

	@Override
	public List<UcUser> conditonSearchMember(MemberSearchDto memberSearchDto) {
		Map<String, Object> ucUserParams = new HashMap<String, Object>();
		ucUserParams.put("userName", memberSearchDto.getUserName());
		ucUserParams.put("mobile", memberSearchDto.getMobileNo());
		ucUserParams.put("regStartDate", memberSearchDto.getRegStartDate());
		ucUserParams.put("regEndDate", memberSearchDto.getRegEndDate());
		ucUserParams.put("companyName", memberSearchDto.getRealName());
		//added by biran 20150804 增加业务员信息
		ucUserParams.put("salesMan", memberSearchDto.getSalesMan());
		return ucUserDao.findMemberByCondition(ucUserParams);
	}

	@Override
	public int modifyMember(MemberMotifyDto memberMotifyDto,BossUserVo userinfo) {
		UcUser ucUser = new UcUser();
		ucUser.setUserId(Long.valueOf(memberMotifyDto.getMemberId()));
		ucUser.setEmail(memberMotifyDto.getEmail());
		ucUser.setMobile(memberMotifyDto.getMobileNo());
		ucUser.setRemark(memberMotifyDto.getRemark());
		ucUser.setCompanyName(memberMotifyDto.getRealName());
		ucUser.setUpdateTime(new Date());
		//added by biran 20150901 增加最后更新人ID
		if(userinfo!=null){
			ucUser.setUpdaterId(userinfo.getId().intValue());
		}
		return ucUserDao.updateUcUserInfo(ucUser);
	}

	@Override
	public int deleteMember(UcUser ucUser) {
		return ucUserDao.deleteUcUser(ucUser);
	}

	@Override
	public int isLock(UcUser ucUser) {
		Integer status=ucUser.getStatus();
		if (status==0) {// 未锁定用户，改为锁定
			status = 2;
		} else if (status==2) {// 解锁
			status = 0;
		}
		ucUser.setStatus(status);
		return ucUserDao.isLock(ucUser);
	}

	@Override
	public int secretReset(UcUser NewUser) {
		String newPassword = GetInitPassword.get6BitPwd();
		Password password = EncryptUtil.JointownEncode(newPassword);
		NewUser.setPassword(password.getPassword());
		NewUser.setSalt(password.getSalt());
		UcUser ucUser = ucUserDao.findMemberByUserId(NewUser.getUserId().toString());
		
		// 短信发送内容
		String messageContent = GetMessageContext.getSecretResetMessgeContext(ucUser.getUserName(), newPassword);
		// 接收短信的手机号
		String mobile = ucUser.getMobile();

		// 需发送短信
		int modifyFlag = ucUserDao.secretReset(NewUser);
		if (modifyFlag > 0) {
			log.info("重置密码成功！");
			// 发送短信
			/*taskExecutor.execute(new MessageTaskSend(mobile, messageContent));*/
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(mobile, messageContent));
			log.info("secret mobile msg send!");
			return 1;//发送成功
		}
		return 0;
	}

	@Override
	public String lookRemark(String userId) {
		UcUser ucUser = ucUserDao.findMemberByUserId(userId);
		return ucUser.getRemark();
	}

	@Override
	public String memberNameIsHaved(String userName) {
		UcUser ucUser = ucUserDao.findUcUserByUserName(userName,true);
		if (null != ucUser) {
			return ucUser.getUserName();
		}
		return null;
	}

	@Override
	public UcUser findMemberByUserName(String memberName) {
		return ucUserDao.findUcUserByUserName(memberName);
	}

	@Override
	public UcUser findMemberByCompanyName(String companyName) {
		return ucUserDao.findMemberByCompanyName(companyName);
	}

	@Override
	public List<UcUser> memberMobileIsExist(String mobile) {
		return ucUserDao.findMemberMobile(mobile);
	}

	@Override
	public List<UcUser> reMemberMobIsExist(String userId, String mobile) {
		return ucUserDao.reMemberMobIsExist(userId, mobile);
	}

	@Override
	public UcUser getUcUserById(String id) {
		return ucUserDao.getUcUserById(id);
	}
	
}
