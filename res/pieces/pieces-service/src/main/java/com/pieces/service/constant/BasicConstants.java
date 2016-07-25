package com.pieces.service.constant;

public interface BasicConstants {
	//用户状态：有效
	public static final int USER_STATUS_VALID = 0;
	//用户状态：删除
	public static final int USER_STATUS_DELETE = 1;
	//用户状态：未激活
	public static final int USER_STATUS_NOACTIVATION = 2;
	//用户状态：锁定
	public static final int USER_STATUS_LOCK = 2;
	
	//用户在线状态：离线
	public static final int USER_ONLINESTATUS_NO = 0;
	//用户在线状态：在线
	public static final int USER_ONLINESTATUS_ONLINE = 1;
	
	//用户是否绑定erp：未绑定
	public static final int USER_BINDERP_NO = 0;
	//用户是否绑定erp：绑定
	public static final int USER_BINDERP_BIND = 1;
	
	//账号创建渠道：前台
	public static final int USER_CREATECHANNEL_BIZ = 0;
	//账号创建渠道：后台
	public static final int USER_CREATECHANNEL_BOSS = 1;


	String KAPTCHA_SESSION_KEY = "kaptcha_session_key";  //验证码Session的key

	String ENQUIRY_COOKIES= "enquiry_cookies";

}
