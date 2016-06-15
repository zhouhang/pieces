package com.jointown.zy.common.dto;

/**
 * 意见
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
public class WxOpinionDto extends BaseDto {

	/**
	 * 用户名
	 * 
	 * @author aizhengdong 2015年7月15日
	 */
	private String userName;

	/**
	 * 手机号码
	 * 
	 * @author aizhengdong 2015年7月15日
	 */
	private String mobile;

	/**
	 * 意见描述（小于等于1000字符）
	 * 
	 * @author aizhengdong 2015年7月15日
	 */
	private String memo;

	/**
	 * 图片地址
	 * 
	 * @author aizhengdong 2015年7月15日
	 */
	private String picUrl;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}
