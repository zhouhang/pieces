package com.jointown.zy.common.vo;


/**
 * 微信公众平台开发--整个菜单对象的封装
 * 
 * @author aizhengdong
 *
 * @data 2015年2月3日
 */
public class WxMenuVo {
	/** 多个菜单项 */
	private WxBaseButtonVo[] button;

	public WxBaseButtonVo[] getButton() {
		return button;
	}

	public void setButton(WxBaseButtonVo[] button) {
		this.button = button;
	}
}
