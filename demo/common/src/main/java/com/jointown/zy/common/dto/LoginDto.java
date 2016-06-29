package com.jointown.zy.common.dto;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.util.ValidationCodeUtil;
import com.jointown.zy.common.validate.annotation.Error;
import com.jointown.zy.common.validate.annotation.StringValidate;

/**
 * @author LiuPiao
 *
 */
public class LoginDto extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@StringValidate(except="用户名",error=ErrorRepository.BOSS_USER_PASS_NOT_MATCH)
	private String username;
	@StringValidate(min=6,max=16,except="密码",and={"min","max"},error=ErrorRepository.BOSS_USER_PASS_NOT_MATCH)
	@Error(key={"except","and"},errors={ErrorRepository.BOSS_USER_PASS_NOT_DEFAULT,ErrorRepository.BOSS_USER_PASS_NOT_MATCH_LENGTH})
	private String password;
	private String vcode;
	private boolean rememberMe;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	
	@Override
	public List<ErrorMessage> validate() {
		this.errors = super.validate();
		this.vcode = StringUtils.trim(vcode);
		if(!ValidationCodeUtil.isValidationCodeMatched(vcode)){
			addError(new ErrorMessage(ErrorRepository.BOSS_VCODE_ERROR));
		}
		return errors;
	}
	
	
	
	

}
