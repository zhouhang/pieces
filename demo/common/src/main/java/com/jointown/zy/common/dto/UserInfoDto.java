package com.jointown.zy.common.dto;

import java.util.Date;
import java.util.List;

/**
 * @author kevinzhou
 * @date 2014-09-19 12:15:56
 */
public class UserInfoDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	/** 开始创建时间 */
	private Date beginGmtCreated;
	/** 结束创建时间 */
	private Date endGmtCreated;

	/** 用户类型列表 */
	private List<Integer> userTypeList;

	/** 用户Id列表 */
	private List<Integer> userIdList;

	/** 用户Id或者名称 */
	private String userNameOrId;
	/** 用户类型列表 */
	private List<Integer> accountTypeList;

	private Integer userId;

	private String userName;

	private String passwd;

	private Integer accountType;

	private Integer emailVolumn;

	private Double reputation;

	private Double balance;

	private Integer billingPlanId;

	private Date typeTime;

	private Date firstDepositTime;

	private Date gmtCreated;

	private Integer preAccountType;

	private Integer alertBalance;

	private Integer transactionRate;

	private Integer marketingRate;

	private Integer userType;

	private Integer parentId;

	/** 注册IP地址 */
	private String ipAddress;

	/** 是否审核通过 0待审核 1已审核 -1未通过 */
	private Integer isVerify;

	public List<Integer> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<Integer> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public Date getBeginGmtCreated() {
		return beginGmtCreated;
	}

	public void setBeginGmtCreated(Date beginGmtCreated) {
		this.beginGmtCreated = beginGmtCreated;
	}

	public Date getEndGmtCreated() {
		return endGmtCreated;
	}

	public void setEndGmtCreated(Date endGmtCreated) {
		this.endGmtCreated = endGmtCreated;
	}

	public String getUserNameOrId() {
		return userNameOrId;
	}

	public void setUserNameOrId(String userNameOrId) {
		this.userNameOrId = userNameOrId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Integer> getAccountTypeList() {
		return accountTypeList;
	}

	public void setAccountTypeList(List<Integer> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public List<Integer> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getEmailVolumn() {
		return emailVolumn;
	}

	public void setEmailVolumn(Integer emailVolumn) {
		this.emailVolumn = emailVolumn;
	}

	public Double getReputation() {
		return reputation;
	}

	public void setReputation(Double reputation) {
		this.reputation = reputation;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getBillingPlanId() {
		return billingPlanId;
	}

	public void setBillingPlanId(Integer billingPlanId) {
		this.billingPlanId = billingPlanId;
	}

	public Date getTypeTime() {
		return typeTime;
	}

	public void setTypeTime(Date typeTime) {
		this.typeTime = typeTime;
	}

	public Date getFirstDepositTime() {
		return firstDepositTime;
	}

	public void setFirstDepositTime(Date firstDepositTime) {
		this.firstDepositTime = firstDepositTime;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Integer getPreAccountType() {
		return preAccountType;
	}

	public void setPreAccountType(Integer preAccountType) {
		this.preAccountType = preAccountType;
	}

	public Integer getAlertBalance() {
		return alertBalance;
	}

	public void setAlertBalance(Integer alertBalance) {
		this.alertBalance = alertBalance;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	public Integer getTransactionRate() {
		return transactionRate;
	}

	public void setTransactionRate(Integer transactionRate) {
		this.transactionRate = transactionRate;
	}

	public Integer getMarketingRate() {
		return marketingRate;
	}

	public void setMarketingRate(Integer marketingRate) {
		this.marketingRate = marketingRate;
	}

}