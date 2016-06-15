package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.enums.UserSecurityCertResultEnum;
import com.jointown.zy.common.enums.UserSecurityOptEnums;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.UserSecurityOptService;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.vo.MessageVo;
/**
 * @ClassName: UserSecurityOptServiceImpl
 * @Description: TODO
 * @Author: ldp
 * @Date: 2015年8月26日
 * @Version: 1.0
 */
@Service
public class UserSecurityOptServiceImpl extends BaseService implements
		UserSecurityOptService {

	private static final Logger logger = LoggerFactory.getLogger(UserSecurityOptServiceImpl.class);
	
	private static final int EMAIL_EXPIRE = 48;//邮件有效期48小时
	
	@Autowired
	private RedisManager redisManager;
	@Autowired
	private UcUserDao ucUserDao;
	
	@Override
	public String getSetEmailContext(String email,String optType,UcUser ucUser) throws Exception {
		logger.info("UserSecurityOptServiceImpl.getSetEmailContext email:" + email);
		JsonObject json = new JsonObject();
		String userId = String.valueOf(ucUser.getUserId());
		json.addProperty("userId", userId);
		json.addProperty("tok", UUID.randomUUID().toString());
		json.addProperty("email", email);
		json.addProperty("optType", optType);
		//邮件内容存redis
		redisManager.del(RedisEnum.USER_SECURITY_INFO_EMAIL_OPT.getValue() + userId);
		redisManager.set(RedisEnum.USER_SECURITY_INFO_EMAIL_OPT.getValue() + userId, json.toString(), EMAIL_EXPIRE*60*60);
		//加密
		return PayUtil.getDesEncryptData(json.toString());
	}

	@Override
	public MessageVo activeEmail(String veContext) throws Exception {
		logger.info("UserSecurityOptServiceImpl.activeEmail veContext:" + veContext);
		MessageVo mvo = new MessageVo();
		if (null == veContext || "".equals(veContext)) {
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_NOT_NULL));
			return mvo;
		}
		//解密数据
		Map<String, String> emailParams = BeanUtil.jsonToMap(PayUtil.getDesDecryptData(veContext));
		logger.info("UserSecurityOptServiceImpl.activeEmail emailParams:" + emailParams);
		if (null == emailParams || emailParams.size() == 0) {
			logger.info("UserSecurityOptServiceImpl.activeEmail emailParams null");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_NOT_NULL));
			return mvo;
		}
		//校验数据
		String userId = emailParams.get("userId");
		Map<String, String> redisParams = BeanUtil.jsonToMap(redisManager.get(RedisEnum.USER_SECURITY_INFO_EMAIL_OPT.getValue() + userId));
		logger.info("UserSecurityOptServiceImpl.activeEmail redisParams:" + redisParams);
		if (null == redisParams || redisParams.size() == 0) {
			logger.info("UserSecurityOptServiceImpl.activeEmail redis null");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_IS_EXPIRE));
			return mvo;
		}
		if (!redisParams.get("tok").equals(emailParams.get("tok"))) {//校验tok
			logger.info("UserSecurityOptServiceImpl.activeEmail redis params tok:" + redisParams.get("tok"));
			logger.info("UserSecurityOptServiceImpl.activeEmail email params tok:" + emailParams.get("tok"));
			logger.info("tok is not equals");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_IS_EXPIRE));
			return mvo;
		}
		if (!redisParams.get("email").equals(emailParams.get("email"))) {//校验邮箱
			logger.info("email is not equals");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_IS_EXPIRE));
			return mvo;
		}
		//判断邮箱是否存在
		List<UcUser> list = ucUserDao.findUcUserByEmail(emailParams.get("email"));
		if (null != list && list.size() > 0) {
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_EXIST));
			return mvo;
		}
		//设置邮箱
		UcUser ucuser = new UcUser();
		ucuser.setEmail(emailParams.get("email"));
		ucuser.setUserId(Long.parseLong(emailParams.get("userId")));
		ucuser.setUpdateTime(new Date());
		int updateFlag = ucUserDao.updatePhoneAndEmail(ucuser);
		if (updateFlag > 0) {//激活成功
			logger.info("UserSecurityOptServiceImpl.activeEmail success!");
			mvo.setOk(true);
			mvo.setObj(emailParams);
			redisManager.del(RedisEnum.USER_SECURITY_INFO_EMAIL_OPT.getValue() + userId);//修改成功清除redis
			return mvo;
		}
		logger.info("UserSecurityOptServiceImpl.activeEmail failed!");
		mvo.setOk(false);
		mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_IS_EXPIRE));
		return mvo;
	}

	@Override
	public MessageVo emailIsExist(String email) throws Exception {
		logger.info("UserSecurityOptServiceImpl.emailIsExist email:" + email);
		MessageVo mvo = new MessageVo();
		if (email == null) {
			mvo.setOk(false);
			mvo.addError("error01", "邮箱地址不能为空!");
			return mvo;
		}
		String reg = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(reg);
	    Matcher matcher = regex.matcher(email);
		if (!matcher.matches()) {
			mvo.setOk(false);
			mvo.addError("error02", "邮箱地址格式不正确!");
			return mvo;
		}
		List<UcUser> list = ucUserDao.findUcUserByEmail(email);
		if (null != list && list.size() > 0) {
			mvo.setOk(false);
			mvo.addError("error03", "邮箱已存在!");
			return mvo;
		}
		mvo.setOk(true);
		return mvo;
	}

	@Override
	public String getModMobileEmContext(UcUser ucUser) throws Exception {
		logger.info("UserSecurityOptServiceImpl.getModMobileEmContext userinfo:" + ucUser);
		
		JsonObject json = new JsonObject();
		json.addProperty("userId", ucUser.getUserId().toString());
		json.addProperty("optType", UserSecurityOptEnums.UPDATE_MOBILE.getOptCode());
		json.addProperty("validateResult", String.valueOf(UserSecurityCertResultEnum.SECURITY_CERT_EMAIL_PASS.getCertCode()));
		
		//邮件内容存redis
		redisManager.del(RedisEnum.USER_SECURITY_INFO_MOBILE_OPT.getValue() + ucUser.getUserId());
		redisManager.set(RedisEnum.USER_SECURITY_INFO_MOBILE_OPT.getValue() + ucUser.getUserId(), json.toString(), 48*60*60);
		
		//加密
		return PayUtil.getDesEncryptData(json.toString());
	}

	@Override
	public MessageVo emailAuth(String mmveContext) throws Exception {
		logger.info("UserSecurityOptServiceImpl.emailAuth mmveContext:" + mmveContext);
		MessageVo mvo = new MessageVo();
		if (StringUtils.isBlank(mmveContext)) {
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_NOT_NULL));
			return mvo;
		}
		//解密数据
		Map<String, String> emailParams = BeanUtil.jsonToMap(PayUtil.getDesDecryptData(mmveContext));
		logger.info("UserSecurityOptServiceImpl.emailAuth emailParams:" + emailParams);
		if (null == emailParams || emailParams.size() == 0) {
			logger.info("UserSecurityOptServiceImpl.emailAuth emailParams null");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_NOT_NULL));
			return mvo;
		}
		//redis取数据
		String userId = emailParams.get("userId");
		Map<String, String> redisParams = BeanUtil.jsonToMap(redisManager.get(RedisEnum.USER_SECURITY_INFO_MOBILE_OPT.getValue() + userId));
		logger.info("UserSecurityOptServiceImpl.emailAuth redisParams:" + redisParams);
		if (null == redisParams || redisParams.size() == 0) {
			logger.info("UserSecurityOptServiceImpl.emailAuth redis null");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_CONTEXT_IS_EXPIRE));
			return mvo;
		}
		//校验数据
		if (!emailParams.get("validateResult").equals(String.valueOf(UserSecurityCertResultEnum.SECURITY_CERT_EMAIL_PASS.getCertCode()))) {
			logger.info("UserSecurityOptServiceImpl.emailAuth  email auth failed!");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_AUTH_FAILED));
			return mvo;
		}
		mvo.setOk(true);
		mvo.setObj(emailParams.get("optType"));
		return mvo;
	}

	@Override
	public MessageVo validateEmail(String endata) throws Exception {
		logger.info("UserSecurityOptServiceImpl.validateEmail endata:" + endata);
		MessageVo mvo = new MessageVo();
		if (StringUtils.isBlank(endata)) {
			logger.info("UserSecurityOptServiceImpl.validateEmail endata is null");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_VALIDATE_FAILED));
			return mvo;
		}
		//解密数据
		Map<String, String> paramsMap = BeanUtil.jsonToMap(PayUtil.getDesDecryptData(endata));
		logger.info("UserSecurityOptServiceImpl.validateEmail paramsMap:" + paramsMap);
		if (null == paramsMap || paramsMap.size() == 0) {
			logger.info("UserSecurityOptServiceImpl.validateEmail paramsMap null");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_VALIDATE_FAILED));
			return mvo;
		}
		//校验数据
		if (!paramsMap.get("validateResult").equals(String.valueOf(UserSecurityCertResultEnum.SECURITY_CERT_EMAIL_PASS.getCertCode()))) {
			logger.info("UserSecurityOptServiceImpl.validateEmail  email validate failed!");
			mvo.setOk(false);
			mvo.addError(new ErrorMessage(ErrorRepository.UC_SEC_EMAIL_VALIDATE_FAILED));
			return mvo;
		}
		mvo.setOk(true);
		mvo.setObj(paramsMap.get("optType"));
		return mvo;
	}

}
