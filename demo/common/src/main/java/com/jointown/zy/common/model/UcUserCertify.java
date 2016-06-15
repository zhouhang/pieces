package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 认证实体
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 */
public class UcUserCertify implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 会员Id*/
	private Integer userId;
	/** 会员名*/
	private String userName;
	/** 会员手机号*/
	private String mobile;
	/** 认证Id*/
	private Integer certifyId;
	/** 提交时间*/
	private Date submitTime;
	/** 认证名称*/
	private String name;
	/** 认证类型*/
	private Integer type;
	/** 公司性质*/
	private Integer property;
	/** 审核状态*/
	private Integer status;
	/** 会员状态*/
	private Integer userStatus;
	/** 业务类型*/
	private String dealType;
	/** 经营身份*/
	private String dealRole;
	/**
	 * 取得会员Id
	 * @return 会员Id
	 */
	public Integer getUserId() {
	    return userId;
	}
	/**
	 * 设定会员Id
	 * @param userId 会员Id
	 */
	public void setUserId(Integer userId) {
	    this.userId = userId;
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
	 * 取得会员手机号
	 * @return 会员手机号
	 */
	public String getMobile() {
	    return mobile;
	}
	/**
	 * 设定会员手机号
	 * @param mobile 会员手机号
	 */
	public void setMobile(String mobile) {
	    this.mobile = mobile;
	}
	/**
	 * 取得认证Id
	 * @return 认证Id
	 */
	public Integer getCertifyId() {
	    return certifyId;
	}
	/**
	 * 设定认证Id
	 * @param certifyId 认证Id
	 */
	public void setCertifyId(Integer certifyId) {
	    this.certifyId = certifyId;
	}
	/**
	 * 取得提交时间
	 * @return 提交时间
	 */
	public Date getSubmitTime() {
	    return submitTime;
	}
	/**
	 * 设定提交时间
	 * @param submitTime 提交时间
	 */
	public void setSubmitTime(Date submitTime) {
	    this.submitTime = submitTime;
	}
	/**
	 * 取得认证名称
	 * @return 认证名称
	 */
	public String getName() {
	    return name;
	}
	/**
	 * 设定认证名称
	 * @param name 认证名称
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * 取得认证类型
	 * @return 认证类型
	 */
	public Integer getType() {
	    return type;
	}
	/**
	 * 设定认证类型
	 * @param type 认证类型
	 */
	public void setType(Integer type) {
	    this.type = type;
	}
	/**
	 * 取得公司性质
	 * @return 公司性质
	 */
	public Integer getProperty() {
	    return property;
	}
	/**
	 * 设定公司性质
	 * @param property 公司性质
	 */
	public void setProperty(Integer property) {
	    this.property = property;
	}
	/**
	 * 取得审核状态
	 * @return 审核状态
	 */
	public Integer getStatus() {
	    return status;
	}
	/**
	 * 设定审核状态
	 * @param status 审核状态
	 */
	public void setStatus(Integer status) {
	    this.status = status;
	}
	/**
	 * 取得会员状态
	 * @return 会员状态
	 */
	public Integer getUserStatus() {
	    return userStatus;
	}
	/**
	 * 设定会员状态
	 * @param userStatus 会员状态
	 */
	public void setUserStatus(Integer userStatus) {
	    this.userStatus = userStatus;
	}
	/**
	 * 取得业务类型
	 * @return 业务类型
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
	/**
	 * 取得经营身份
	 * @return 经营身份
	 */
	public String getDealRole() {
		return dealRole;
	}
	/**
	 * 设定经营身份
	 * @param dealRole 经营身份
	 */
	public void setDealRole(String dealRole) {
		this.dealRole = dealRole;
	}
}
