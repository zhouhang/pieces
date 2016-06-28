package com.jointown.zy.common.enums;

/**
 * @ClassName: UserSecurityOptEnums
 * @Description: 会员安全信息操作枚举
 * @Author: ldp
 * @Date: 2015年8月25日
 * @Version: 1.0
 */
public enum UserSecurityOptEnums {

	/**修改手机号码*/
	UPDATE_MOBILE(0,"修改手机"),
	/**设置邮箱*/
	SET_EMAIL(1,"设置邮箱"),
	/**修改邮箱*/
	UPDATE_EMAIL(2,"修改邮箱");
	private int optCode;
	private String optTitle;
	public int getOptCode() {
		return optCode;
	}
	public void setOptCode(int optCode) {
		this.optCode = optCode;
	}
	public String getOptTitle() {
		return optTitle;
	}
	public void setOptTitle(String optTitle) {
		this.optTitle = optTitle;
	}
	
	private UserSecurityOptEnums(){}
	
	private UserSecurityOptEnums(int optCode, String optTitle) {
		this.optCode = optCode;
		this.optTitle = optTitle;
	}
	
}
