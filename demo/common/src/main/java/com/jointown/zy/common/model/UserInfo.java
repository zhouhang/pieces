package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wanfeizhang
 * @date 2014-01-07 12:15:56
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** userstore的编码 */
	public static final String USER_STORE_CODE = "1012";
	/** userstore的值 */
	public static final String USER_STORE_VALUE = "db5a7a3d14a5d194fc8b8130dc551e14";

	/** 初始化用户的计费方案 */
	public static final int INIT_BILLING_PLAN_ID = 1;

	/** 初始化用户信誉度 */
	public static final double INIT_REPUTATION = 60D;

	/** 初始化免费用户额度 */
	public static final int INIT_EMAILVOLUMN = 200;
	/** 初始化内部用户额度 */
	public static final int INIT_INNER_EMAILVOLUMN = 200000;
	/** 初始化VIP用户额度 */
	public static final int INIT_VIP_EMAILVOLUMN = 200000;

	/** 账户类型:未验证 */
	public static final int ACCOUNT_TYPE_NOT_VERIFY = 0;
	/** 账户类型:冻结 */
	public static final int ACCOUNT_TYPE_FORZEN = -1;
	/** 账户类型:审核不通过 */
	public static final int ACCOUNT_TYPE_VERIFY_FAIL = -2;
	/** 账户类型:用户未激活 */
	public static final int ACCOUNT_TYPE_NOT_ACTIVATE = -3;
	/** 账户类型:免费用户 */
	public static final int ACCOUNT_TYPE_FREE = 1;
	/** 账户类型:免费用户(由收费用户转为免费用户) */
	public static final int ACCOUNT_TYPE_FREE2 = 4;
	/** 账户类型:付费用户(默认付费类型) */
	public static final int ACCOUNT_TYPE_VIP = 2;
	/** 账户类型:付费用户(付费类型2，独立IP付费用户) */
	public static final int ACCOUNT_TYPE_VIP2 = 3;
	/** 账户类型:内部用户 */
	public static final int ACCOUNT_TYPE_INNER = 9;

	/** 审核通过 */
	public static final int ISVERIFY_YES = 1;
	/** 待审核 */
	public static final int ISVERIFY_PENDING = 0;
	/** 审核未通过 */
	public static final int ISVERIFY_NO = -1;

	/** 普通用户 */
	public static final int USER_TYPE_GENERAL = 0;
	/** 主用户 */
	public static final int USER_TYPE_PARENT = 1;
	/** 子用户 */
	public static final int USER_TYPE_CHILD = 2;
	/** DISCUZ用户 */
	public static final int USER_TYPE_DISCUZ = 3;
	/** MailChimp用户 */
	public static final int USER_TYPE_MAIL_CHIMP = 4;
	

	/** 初始化的主账户警告金额 */
	public static final int ALERT_BALANCE_PARENT = 5;

	/** 用户id */
	private Integer userId;
	/** 用户名 */
	private String userName;
	/** 用户密码 */
	private String passwd;
	/** 用户类别 */
	private Integer accountType;

	/** 邮件容量 */
	private Integer emailVolumn;

	/** 信用度 */
	private Double reputation;

	/** 账户余额 */
	private Double balance;

	/** 收费方案 */
	private Integer billingPlanId;

	/** 账户类型更新时间 */
	private Date typeTime;

	/** 创建时间 */
	private Date gmtCreated;
	/** 第一次充值时间 */
	private Date firstDepositTime;

	/** 之前的账号类型 */
	private Integer preAccountType;

	/** 设置的提示额度 */
	private Integer alertBalance;

	/** 是否审核通过 */
	private Integer isVerify;

	private Integer transactionRate;

	private Integer marketingRate;

	/** 用户类型 0普通账户 1主账户 2子账户 3discuz用户 */
	private Integer userType;

	/** 父账户Id */
	private Integer parentId;

	/** UserContactInfo中的createTime */
	private Date createTime;

	/**
	 * 通过账户类型代号获得账户类型名称
	 * 
	 * @param accountType
	 * @return
	 */
	public static String getAccountTypeName(Integer accountType) {
		switch (accountType) {
		case 0:
			return "未验证";
		case -1:
			return "冻结";
		case -2:
			return "审核不通过";
		case 1:
			return "免费用户";
		case 4:
			return "免费用户(由收费转为免费)";
		case 2:
			return "付费用户";
		case 3:
			return "付费用户(包含独立IP)";
		case 9:
			return "内部用户";
		default:
			return "默认用户";
		}
	}

	/**
	 * 获得账户类型名称
	 * 
	 * @return
	 */
	public String getAccountTypeName() {
		switch (accountType) {
		case 0:
			return "未验证";
		case -1:
			return "冻结";
		case -2:
			return "审核不通过";
		case 1:
			return "免费用户";
		case 4:
			return "免费用户(由收费转为免费)";
		case 2:
			return "付费用户";
		case 3:
			return "付费用户(包含独立IP)";
		case 9:
			return "内部用户";
		default:
			return "默认用户";
		}
	}

	/**
	 * 判断用户是否为充值首月
	 * 
	 * @param firstDepositTimeLong 充值时间戳
	 * @return true是 false 不是
	 */
	public boolean isFirstDepositMonth(Long firstDepositTimeLong) {
		boolean isFirstMonth = false;
		if (firstDepositTimeLong != null) {
			Calendar userfirstDepositTime = Calendar.getInstance();
			Calendar nowCal = Calendar.getInstance();
			userfirstDepositTime.setTime(new Date(firstDepositTimeLong));
			if (userfirstDepositTime.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR) && userfirstDepositTime.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH)) {// 如果首次充值月份是计费月
				isFirstMonth = true;
			}
		}
		return isFirstMonth;
	}

	/**
	 * 判断用户是否为充值首月
	 * 
	 * @param firstDepositTimeLong 充值时间戳
	 * @return true是 false不是
	 */
	public boolean isFirstDepositMonth() {
		if (this.getFirstDepositTime() == null) {
			return false;
		}
		Long firstDepositTimeLong = this.getFirstDepositTime().getTime();
		boolean isFirstMonth = false;
		if (firstDepositTimeLong != null) {
			Calendar userfirstDepositTime = Calendar.getInstance();
			Calendar nowCal = Calendar.getInstance();
			userfirstDepositTime.setTime(new Date(firstDepositTimeLong));
			if (userfirstDepositTime.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR) && userfirstDepositTime.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH)) {// 如果首次充值月份是计费月
				isFirstMonth = true;
			}
		}
		return isFirstMonth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", passwd=" + passwd + ", accountType=" + accountType + ", emailVolumn=" + emailVolumn
				+ ", reputation=" + reputation + ", balance=" + balance + ", billingPlanId=" + billingPlanId + ", typeTime=" + typeTime + ", firstDepositTime="
				+ firstDepositTime + ", gmtCreated=" + gmtCreated + ", preAccountType=" + preAccountType + ", alertBalance=" + alertBalance + ", isVerify="
				+ isVerify + ", transactionRate=" + transactionRate + ", marketingRate=" + marketingRate + "]";
	}

}