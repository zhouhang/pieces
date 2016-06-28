package com.jointown.zy.common.vo;

/**
 * 微信公众平台开发--菜单按钮的基类
 * 
 * @author aizhengdong
 * 
 * @date 2015年2月3日
 */
public class WxBaseButtonVo {
	/** 菜单标题，不超过16个字节，子菜单不超过40个字节 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
