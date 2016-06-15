package com.jointown.zy.common.dto;

/**
 * 
 * 描述： 前台会员中心-我的资料DTO
 * 
 * 日期： 2014年12月11日
 * 
 * 作者： 赵航
 *
 * 版本： V1.0
 */
public class UcUserMaterialDto extends BaseDto{

	private static final long serialVersionUID = -7300126834249222286L;
	
	/** 原密码 */
	private String oldPassword;
	
	/** 新密码 */
	private String newPassword;
	
	/** 确认密码 */
	private String surePassword;
	
	/** 原手机号码 */
	private String oldMobile;
	
	/** 新手机号码 */
	private String newMobile;
	
	/** 手机验证码 */
	private String mobileVerificationCode;
	
	/** 原公司/真实名称 */
	private String oldCompanyName;
	
	/** 新公司/真实名称 */
	private String newCompanyName;
	
	/** 登录密码 */
	private String loginPassword;
	
	/** 原邮箱 */
	private String oldEmail;
	
	/** 新邮箱 */
	private String newEmail;
	

	/**
	 * 取得原密码
	 * @return 原密码
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * 设定原密码
	 * @param oldPassword 原密码
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * 取得新密码
	 * @return 新密码
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 设定新密码
	 * @param newPassword 新密码
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * 取得确认密码
	 * @return 确认密码
	 */
	public String getSurePassword() {
		return surePassword;
	}

	/**
	 * 设定确认密码
	 * @param surePassword 确认密码
	 */
	public void setSurePassword(String surePassword) {
		this.surePassword = surePassword;
	}

	/**
	 * 取得原手机号码
	 * @return 原手机号码
	 */
	public String getOldMobile() {
		return oldMobile;
	}

	/**
	 * 设定原手机号码
	 * @param oldMobile 原手机号码
	 */
	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	/**
	 * 取得新手机号码
	 * @return 新手机号码
	 */
	public String getNewMobile() {
		return newMobile;
	}

	/**
	 * 设定新手机号码
	 * @param newMobile 新手机号码
	 */
	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}

	/**
	 * 取得手机验证码
	 * @return 手机验证码
	 */
	public String getMobileVerificationCode() {
		return mobileVerificationCode;
	}

	/**
	 * 设定手机验证码
	 * @param mobileVerificationCode 手机验证码
	 */
	public void setMobileVerificationCode(String mobileVerificationCode) {
		this.mobileVerificationCode = mobileVerificationCode;
	}

	/**
	 * 取得原公司/真实名称
	 * @return 原公司/真实名称
	 */
	public String getOldCompanyName() {
		return oldCompanyName;
	}

	/**
	 * 设定原公司/真实名称
	 * @param oldCompanyName 原公司/真实名称
	 */
	public void setOldCompanyName(String oldCompanyName) {
		this.oldCompanyName = oldCompanyName;
	}

	/**
	 * 取得新公司/真实名称
	 * @return 新公司/真实名称
	 */
	public String getNewCompanyName() {
		return newCompanyName;
	}

	/**
	 * 设定新公司/真实名称
	 * @param newCompanyName 新公司/真实名称
	 */
	public void setNewCompanyName(String newCompanyName) {
		this.newCompanyName = newCompanyName;
	}

	/**
	 * 取得登录密码
	 * @return 登录密码
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * 设定登录密码
	 * @param loginPassword 登录密码
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/**
	 * 取得原邮箱
	 * @return 原邮箱
	 */
	public String getOldEmail() {
		return oldEmail;
	}

	/**
	 * 设定原邮箱
	 * @param oldEmail 原邮箱
	 */
	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	/**
	 * 取得新邮箱
	 * @return 新邮箱
	 */
	public String getNewEmail() {
		return newEmail;
	}

	/**
	 * 设定新邮箱
	 * @param newEmail 新邮箱
	 */
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	
}
