package com.jointown.zy.common.vo;


/**
 * 微信公众平台开发--通用接口凭证
 * 
 * @author aizhengdong
 * 
 * @date 2015年2月3日
 */
public class WxAccessTokenVo {
	/** 获取到的凭证 */
	private String token;
	
	/** 凭证有效时间，单位：秒 */
	private int expiresIn;
	
	/** 创建时间 */
	private long createTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
}
