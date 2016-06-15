package com.jointown.zy.common.constant;

import com.jointown.zy.common.util.SpringUtil;

/**
 * 封装相关resource信息,messageKey代码message中的key,value代表值
 * @author LiuPiao
 *
 */
public enum MessageConstant {
	//alter by biran 20141231 将资源服务器地址分为三个
	RESOURCE_JS("jointown.resource.path.js"),
	RESOURCE_CSS("jointown.resource.path.css"),
	RESOURCE_IMG("jointown.resource.path.img"),
	
	//alter by biran 20150602  为支付项目增加资源文件配置
	RESOURCE_PAY("jointown.resource.path.pay"),

	//update by licx 20150310微信解析域名地址
	RESOURCE_WWW_WX("jointown.wx.resource.path.www"),
	//update by guoyb 20150303 微信资源服务器地址为_WX结尾的三个
	RESOURCE_VERSION_WX("jointown.wx.version"),
	RESOURCE_APPID_WX("jointown.wx.appid"),
	RESOURCE_APPSECRET_WX("jointown.wx.appsecret"),
	RESOURCE_TOKEN_WX("jointown.wx.token"),
	RESOURCE_JS_WX("jointown.wx.resource.path.js"),
	RESOURCE_CSS_WX("jointown.wx.resource.path.css"),
	RESOURCE_IMG_WX("jointown.wx.resource.path.img"),
	RESOURCE_IMG_UPLOAD_WX("jointown.wx.resource.imgurl"),
	//update by wangjunhu 20150309 东方中药材网图片服务器地址
	RESOURCE_IMG_EAST("jointown.east.resource.path.img"),
	
	URL_SERVER_PREFIX_UC("jointown.url.uc.server.prefix"),
	URL_REGISTER_UC("jointown.url.uc.register"),
	URL_FIND_PASSWORD_UC("jointown.url.uc.findPassword"),
	URL_SHIRO_LOGIN_UC("jointown.shiro.filter.url.login"),
	RESOURCE_IMG_UPLOAD("jointown.resource.imgurl"),  //alter by Mr.songwei 2015.1.14 上传资源服务器路径
	JOINTOWNURL("jointown.url"),  //alter by Mr.songwei 2015.1.14 外部连接
	URL_PREFIX_PAY("jointown.url.pay"),
	URL_PREFIX_HELP("jointown.url.help"), //alter by biran 2015.11.13  客服中心
	
	//运营人员邮箱地址
	OPERATOR_GUAPAI_EMAIL_ADDRESS("54315.email.operator.guapai"),
	OPERATOR_ZHAIPAI_EMAIL_ADDRESS("54315.email.operator.zhaipai"),
	OPERATOR_CANGKU_EMAIL_ADDRESS("54315.email.operator.cangku"),
	OPERATOR_XUQIU_EMAIL_ADDRESS("54315.email.operator.xuqiu"),
	OPERATOR_CAIGOU_EMAIL_ADDRESS("54315.email.operator.caigou"),
	OPERATOR_JY_EMAIL_ADDRESS("kefu.jy.email.address"),	
	OPERATOR_WX_EMAIL_ADDRESS("54315.email.address.wx"),	
	//调用接口失败 开发、产品、运营的邮箱地址
	INTERFACE_FAIL_DEV_EMAIL_ADDRESS("54315.email.interface.dev"),
	INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS("54315.email.interface.product"),
	INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS("54315.email.interface.operate"),
	
	//财务确认划账信息的邮箱地址
	EMAIL_CHECK_REMITFLOW("54315.email.check.remitflow"),
	
	//跟单员邮件地址
	EMAIL_TRACKER("jointown.busi.term.order.tracker.email"),
	
	//采购配置的候选交易员信息
	PURCHASE_ALTERNATIVE_SALESMAN_NAME("jointown.busi.purchase.salesman.name"),
	PURCHASE_ALTERNATIVE_SALESMAN_PHONE("jointown.busi.purchase.salesman.phone"),
	PURCHASE_ALTERNATIVE_SALESMAN_EMAIL("jointown.busi.purchase.salesman.email"),
	
	//alter by biran 20150603  首页专题页增加URL配置
	URL_TOPIC("jointown.url.topic"),
	
	//alter by biran 20150603  首页专题页增加URL配置
	IMG_PATH("sftp.pic.imgPath"),
	
	//alter by guoyb 20150907  首页专题页增加URL配置
	IMG_PATH_WX("sftp.pic.imgPath.wx"),
	
	//add by fanyuna 2015.09.17 调用接口的连接时间、读取时间
	API_CONNECTION_TIMEOUT("api.connection.timeout"),
	API_READ_TIMEOUT("api.read.timeout");
	
	private String messageKey;
	private String value;
	
	private MessageConstant(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
	

	public MessageConstant setMessageKey(String messageKey) {
		this.messageKey = messageKey;
		return this;
	}

	public String getValue() {
		return value==null?value=SpringUtil.getConfigProperties().getProperty(getMessageKey()):value;
	}
	
	public Integer intValue() {
		return Integer.parseInt(getValue());
	}

	public MessageConstant setValue(String value) {
		this.value = value;
		return this;
	}
}