package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dao.UcUserContactDao;
import com.jointown.zy.common.dto.UcUserContactDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.UcUserContact;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;

@Service("ucUserContactService")
public class UcUserContactServiceImpl implements UcUserContactService {
	
	private static final Logger log = LoggerFactory.getLogger(UcUserContactServiceImpl.class);
	
	@Autowired
	private UcUserContactDao ucUserContactDao;
	
	/**
	 * @Description: 获取会员用户信息
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @return
	 */
	public UcUserVo getUcUserVo(){
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		return ucUserVo;
	}

	@Override
	public List<UcUserContact> selectUcUserContactsByUserId(Long userId)
			throws Exception {
		return ucUserContactDao.getUcUserContactsByUserId(userId);
	}
	
	/**
	 * @Description: 添加联系人信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-23
	 */
	public String addContacter(UcUserContactDto dto) throws Exception {
		JsonObject json = new JsonObject();
		int count = 1;
		for(UcUserContact contacter : dto.getUcUserContact()){
			if (count > 5) {//最多只能添加5个联系人
				break;
			}
			if(null != contacter && StringUtils.isNotBlank(contacter.getMobile())){
				contacter.setUserId(getUcUserVo().getUserId());
				contacter.setStatus(1);
				contacter.setCreateTime(new Date());
				contacter.setUpdateTime(new Date());
				ucUserContactDao.addContacter(contacter);
				count++;
			}
		}
		json.addProperty("status", Boolean.TRUE);
		UcUserVo user = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		user.setHasContacter(Boolean.TRUE);
		SecurityUtils.getSubject().getSession().setAttribute(RedisEnum.SESSION_USER_UC.getValue(), user);
		return json.toString();
	}
	
	public String addContacterByUserId(UcUserContactDto dto) throws Exception {
		JsonObject json = new JsonObject();
		Long userId = dto.getUcUserContact().get(0).getUserId();
		UcUserContact dcontacter = new UcUserContact();
		dcontacter.setUserId(userId);
		dcontacter.setStatus(0);
		ucUserContactDao.delUcUserContactByUserId(dcontacter);
		for(UcUserContact contacter : dto.getUcUserContact()){
			if(null!=contacter){
				contacter.setUserId(userId);
				contacter.setStatus(1);
				contacter.setCreateTime(new Date());
				contacter.setUpdateTime(new Date());
				ucUserContactDao.addContacter(contacter);
			}
		}
		json.addProperty("status", "ok");
		return json.toString();
	}
	
	/**
	 * @Description: 后台参数验证
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @param contacter
	 * @return
	 */
	public MessageVo validate(UcUserContact contacter){
		MessageVo mvo = new MessageVo();
		
		if(StringUtils.isBlank(contacter.getName())){
			mvo.setOk(Boolean.FALSE);
			mvo.addError("name", ErrorRepository.UC_CONTACTER_NAME_NOT_NULL.getMessage());
			return mvo;
		}
		//姓名正则验证 中文 1-50个字符
		Pattern name_regex = Pattern.compile("/[\u4E00-\u9FA5]{1,50}/");
		if(name_regex.matcher(contacter.getName()).matches()){
			mvo.setOk(Boolean.FALSE);
			mvo.addError("name", ErrorRepository.UC_CONTACTER_NAME_FORMART_ERROR.getMessage());
			return mvo;
		}
		if(StringUtils.isBlank(contacter.getMobile())){
			mvo.setOk(Boolean.FALSE);
			mvo.addError("mobile", ErrorRepository.UC_CONTACTER_MOBILEPHONE_NOT_NULL.getMessage());
			return mvo;
		}
		Pattern mobile_regex = Pattern.compile("/^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/");
		if(mobile_regex.matcher(contacter.getMobile()).matches()){
			mvo.setOk(Boolean.FALSE);
			mvo.addError("mobile", ErrorRepository.UC_CONTACTER_MOBILE_FORMART_ERROR.getMessage());
			return mvo;
		}
		if(null==contacter.getSex()){
			mvo.setOk(Boolean.FALSE);
			mvo.addError("sex", ErrorRepository.UC_CONTACTER_SEX_NOT_NULL.getMessage());
			return mvo;
		}
		mvo.setOk(Boolean.TRUE);
		return mvo;
	}

	@Override
	public String delContacter(UcUserContactDto dto) throws Exception {
		JsonObject json = new JsonObject();
		for(UcUserContact contacter : dto.getUcUserContact()){
			if(null!=contacter){
				contacter.setUserId(getUcUserVo().getUserId());
				ucUserContactDao.updateUcUserContact(contacter);
			}
		}
		json.addProperty("status", Boolean.TRUE);
		return json.toString();
	}
}
