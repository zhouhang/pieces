package com.jointown.zy.common.dto;

import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jointown.zy.common.exception.ErrorRepository;

public class MemberAddDto {
	
	
	private String userName;
	private String passWord;
	private String realName;
	private String mobile;
	private String remark;
	


	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassWord() {
		return passWord;
	}



	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}



	public String getRealName() {
		return realName;
	}



	public void setRealName(String realName) {
		this.realName = realName;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	private static final String MOB_REGX = "^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$";
	private static final String USERNAME_REGX = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\s]{5,25}$";
	private static final String PWD_REGX = "^[\\w\\W]{6,16}$";
	private static final String REALNAMW_REGX = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\s]{2,25}$";
	private static final String REMARK_REGX = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\s]{0,500}$";
	
	
	public String validate(){
		String msg = null;
		//验证用户名
		if (null == userName || "".equals(userName)) {
			msg = ErrorRepository.UC_USER_NAME_NOT_NULL.getMessage();
			return msg;
		}
		//去空格
		userName = userName.trim();
		
		Pattern pattern_userName = Pattern.compile(USERNAME_REGX);
        if (!pattern_userName.matcher(userName).matches()) {
        	msg = "会员名至少5-25个字符！";
			return msg;
		}
		
        //验证密码
		if (null == passWord || "".equals(passWord)) {
			msg = ErrorRepository.UC_USER_PASSWORD_NOT_NULL.getMessage();
			return msg;
		}
        if (!Pattern.compile(PWD_REGX).matcher(passWord).matches()) {
			return "密码至少6~16个字符！";
		}
		
        //验证realName
		if (null == realName || "".equals(realName)) {
			msg = ErrorRepository.UC_USER_REALNAME_NOT_NULL.getMessage();
			return msg;
		}
        if (!Pattern.compile(REALNAMW_REGX).matcher(realName).matches()) {
			return "公司/姓名至少4~50个字符！";
		}
        realName = realName.trim();
        
        //验证手机号
		if (null == mobile || "".equals(mobile)) {
			msg = ErrorRepository.UC_USER_MOBILE_NOT_NULL.getMessage();
			return msg;
		}
        if (!Pattern.compile(MOB_REGX).matcher(mobile).matches()) {
			return "手机号格式不正确!";
		}
        
        //验证备注
        if(!Pattern.compile(REMARK_REGX).matcher(remark).matches()){
        	return "备注不能多于500个字符";
        }
        //验证通过
		return "success";
	}
	
}
