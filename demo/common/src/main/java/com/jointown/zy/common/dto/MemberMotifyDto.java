package com.jointown.zy.common.dto;

import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jointown.zy.common.exception.ErrorRepository;

/**
 * 会员修改DTO
 * description 
 * author ldp
 * 2014年11月26日
 */
public class MemberMotifyDto {
	private String memberId;
	private String realName;
	private String mobileNo;
	private String email;
	private String remark;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	private static final String REALNAMW_REGX = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\s]{2,25}$";
	private static final String REMARK_REGX = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\s]{0,500}$";
	private static final String EMAIL_REGX = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	
	public String validate(){
		String msg = null;
	
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
		if (null == mobileNo || "".equals(mobileNo)) {
			msg = ErrorRepository.UC_USER_MOBILE_NOT_NULL.getMessage();
			return msg;
		}
        if (!Pattern.compile(MOB_REGX).matcher(mobileNo).matches()) {
			return "手机号格式不正确!";
		}
        
        //验证email
        if (email!=null && !"".equals(email)) {
        	if (!Pattern.compile(EMAIL_REGX).matcher(email).matches()) {
        		return "邮箱格式不正确!";
        	}	
        	email = email.trim();
		}
        //验证备注
        if(!Pattern.compile(REMARK_REGX).matcher(remark).matches()){
        	return "备注不能多于500个字符";
        }
        //验证通过
		return "success";
	}
}
