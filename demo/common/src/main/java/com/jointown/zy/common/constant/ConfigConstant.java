package com.jointown.zy.common.constant;

import com.jointown.zy.common.util.SpringUtil;

/**
 * 常量参数
 * author ldp
 * 2014年12月18日
 */
public class ConfigConstant {
	
	//add by Mr.songwei 2015.1.17 11:35
	public static final int USER_CENTER_PAGE_SIZE=10;  //前台分页每页显示的条数
	
	public static final int WX_USER_CENTER_PAGE_SIZE=5;  //微信前台分页每页显示的条数
	
	public static final int WX_BUYINFO_PAGE_SIZE=2;  //微信商品购买记录分页条数
	
	//add by Mr.songwei 2015.3.20 9:35
	public static final int ORDERSTATE=0;  //订单状态 ：0表示洽淡中

	public static final int AD_CATEGORY=0;  //首页平台广告管理分类（默认为0，表示首页广告）

	
	/**
	 * 短信参数(用户名、密码、短信接口地址)
	 */
	public static final String USER_NAME;
	public static final String PASSWORD;
	public static final String MOB_MSG_URL;
	
	/**
	 * 邮箱参数定义
	 */
	public static final String EMAIL_SMTP;
	public static final String EMAIL_FROM;
	public static final String EMAIL_FROM_NAME;//发件人名称
	public static final String EMAIL_FROM_USERNAME;
	public static final String EMAIL_FROM_PASSWORD;
	public static final String EMAIL_SUBJECT;
	
	//接口调用失败发邮件的邮件地址
	public static final String EMAIL_FROM_WMS;
	public static final String EMAIL_FROM_WMS_USERNAME;
	public static final String EMAIL_FROM_WMS_PASSWORD;
	
	/**
	 * 凌讯中科短信参数内容
	 */
	/**短信接口地址*/
	public static final String LXZK_SMS_URL;
	/**企业ID*/
	public static final String LXZK_SMS_USERID;		
	/**账号*/
	public static final String LXZK_SMS_ACCOUNT;	
	/**密码*/
	public static final String LXZK_SMS_PASSWORD;	
	
	/**
	 * 客服人员的邮件地址
	 */
	public static final String EMAIL_TO_KEFU;
	
	/**
	 * 企信通新通道
	 */
	public static final String NEW_QXT_URL;
	public static final String NEW_QXT_USERID;
	public static final String NEW_QXT_PASSWORD;
	
	
	static {
		//获取手机短信参数
		USER_NAME = SpringUtil.getConfigProperties( "sms.name");
		PASSWORD = SpringUtil.getConfigProperties( "sms.password");
		MOB_MSG_URL = SpringUtil.getConfigProperties("sms.req_url");
		
		//获取邮件参数
		EMAIL_SMTP = SpringUtil.getConfigProperties( "email.smtp");
		EMAIL_FROM = SpringUtil.getConfigProperties("email.from");
		EMAIL_FROM_NAME = SpringUtil.getConfigProperties("email.from.name");
		EMAIL_FROM_USERNAME = SpringUtil.getConfigProperties( "email.from.username");
		EMAIL_FROM_PASSWORD = SpringUtil.getConfigProperties( "email.from.password");
		EMAIL_SUBJECT =SpringUtil.getConfigProperties( "email.subject.pwdfind");
		
		
		//获取收件客服的邮件地址
		EMAIL_TO_KEFU= SpringUtil.getConfigProperties("email.to.kefu");
		
		EMAIL_FROM_WMS=SpringUtil.getConfigProperties( "email.from.wms");
		EMAIL_FROM_WMS_USERNAME=SpringUtil.getConfigProperties( "email.from.wms.username");
		EMAIL_FROM_WMS_PASSWORD=SpringUtil.getConfigProperties("email.from.wms.password");
		
		//获取凌讯中科短信接口参数
		LXZK_SMS_URL = SpringUtil.getConfigProperties("lxzk.sms.url");
		LXZK_SMS_USERID = SpringUtil.getConfigProperties("lxzk.sms.userid");
		LXZK_SMS_ACCOUNT = SpringUtil.getConfigProperties("lxzk.sms.account");
		LXZK_SMS_PASSWORD = SpringUtil.getConfigProperties( "lxzk.sms.password");
		
		//获取企信通新短信接口参数
		NEW_QXT_URL = SpringUtil.getConfigProperties("new.qxt.sms.url");
		NEW_QXT_USERID = SpringUtil.getConfigProperties("new.qxt.sms.userId");
		NEW_QXT_PASSWORD = SpringUtil.getConfigProperties("new.qxt.sms.password");
		
	}
}