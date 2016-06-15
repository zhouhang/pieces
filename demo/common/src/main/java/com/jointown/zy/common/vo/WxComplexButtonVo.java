package com.jointown.zy.common.vo;


/**
 * 微信公众平台开发--多级菜单的父按钮
 * 
 * @author aizhengdong
 *
 * @data 2015年2月3日
 */
public class WxComplexButtonVo extends WxCommonButtonVo{
	
	/** 子菜单按钮数组 */
	private WxBaseButtonVo[] sub_button;

	public WxBaseButtonVo[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(WxBaseButtonVo[] sub_button) {
		this.sub_button = sub_button;
	};


	
}
