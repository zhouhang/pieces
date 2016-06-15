package com.jointown.zy.common.vo;

/**
 * @ClassName: JsLoginMessageVo
 * @Description:当需要通过js访问需要登录或者认证的页面时作为返回转换为json的中间类
 * @Author: guoyb
 * @Date: 2015年6月8日
 * @Version: 1.0
 */
public class JsLoginMessageVo {
	
	/** 登陆状态值,可从 */
	private String loginStatus;
	
	/** 下跳页面链接 ,下跳页面也可以不设置而在页面的js中直接输入*/
	private String next;
	
	/** 额外信息 */
	private Object extra;

	/**
	 * @return the loginStatus
	 */
	public String getLoginStatus() {
		return loginStatus;
	}

	/**
	 * @param loginStatus the loginStatus to set
	 */
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	/**
	 * @return the next
	 */
	public String getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(String next) {
		this.next = next;
	}

	/**
	 * @return the extra
	 */
	public Object getExtra() {
		return extra;
	}

	/**
	 * @param extra the extra to set
	 */
	public void setExtra(Object extra) {
		this.extra = extra;
	}
}
