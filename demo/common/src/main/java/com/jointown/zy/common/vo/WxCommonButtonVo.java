package com.jointown.zy.common.vo;


/**
 * 微信公众平台开发--普通按钮
 * 
 * @author aizhengdong
 *
 * @data 2015年2月3日
 */
public class WxCommonButtonVo extends WxBaseButtonVo{
	
	/** 菜单的响应动作类型 */
	private String type;
	
	/** 菜单KEY值，用于消息接口推送，click等点击类型必须 */
	private String key;
	
	/** 跳转URL */
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
