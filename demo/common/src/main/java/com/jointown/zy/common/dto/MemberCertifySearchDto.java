package com.jointown.zy.common.dto;

/**
 * 会员认证查询dto
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 */
public class MemberCertifySearchDto extends BaseDto {
	
	private static final long serialVersionUID = 3502471458286071383L;
	/** 提交开始时间*/
	private String submitStartDate;
	/** 提交结束时间*/
	private String submitEndDate;
	/** 会员名*/
	private String userName;
	/** 手机号*/
	private String mobileNo;
	/** 认证类型*/
	private String certifyType;
	/** 会员类型*/
	private String userType;
	/** 企业名称/姓名*/
	private String realName;
	/** 会员类型*/
	private String dealType;
	/**
	 * 取得提交开始时间
	 * @return 提交开始时间
	 */
	public String getSubmitStartDate() {
	    return submitStartDate;
	}
	/**
	 * 设定提交开始时间
	 * @param submitStartDate 提交开始时间
	 */
	public void setSubmitStartDate(String submitStartDate) {
	    this.submitStartDate = submitStartDate;
	}
	/**
	 * 取得提交结束时间
	 * @return 提交结束时间
	 */
	public String getSubmitEndDate() {
	    return submitEndDate;
	}
	/**
	 * 设定提交结束时间
	 * @param submitEndDate 提交结束时间
	 */
	public void setSubmitEndDate(String submitEndDate) {
	    this.submitEndDate = submitEndDate;
	}
	/**
	 * 取得会员名
	 * @return 会员名
	 */
	public String getUserName() {
	    return userName;
	}
	/**
	 * 设定会员名
	 * @param userName 会员名
	 */
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	/**
	 * 取得手机号
	 * @return 手机号
	 */
	public String getMobileNo() {
	    return mobileNo;
	}
	/**
	 * 设定手机号
	 * @param mobileNo 手机号
	 */
	public void setMobileNo(String mobileNo) {
	    this.mobileNo = mobileNo;
	}
	/**
	 * 取得认证类型
	 * @return 认证类型
	 */
	public String getCertifyType() {
	    return certifyType;
	}
	/**
	 * 设定认证类型
	 * @param certifyType 认证类型
	 */
	public void setCertifyType(String certifyType) {
	    this.certifyType = certifyType;
	}
	/**
	 * 取得会员类型
	 * @return 会员类型
	 */
	public String getUserType() {
	    return userType;
	}
	/**
	 * 设定会员类型
	 * @param userType 会员类型
	 */
	public void setUserType(String userType) {
	    this.userType = userType;
	}
	/**
	 * 取得企业名称/姓名
	 * @return 企业名称/姓名
	 */
	public String getRealName() {
	    return realName;
	}
	/**
	 * 设定企业名称/姓名
	 * @param realName 企业名称/姓名
	 */
	public void setRealName(String realName) {
	    this.realName = realName;
	}
	/**
	 * 获取业务类型
	 * @param dealType 业务类型
	 */
	public String getDealType() {
		return dealType;
	}
	/**
	 * 设定业务类型
	 * @param dealType 业务类型
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
	
	
}
