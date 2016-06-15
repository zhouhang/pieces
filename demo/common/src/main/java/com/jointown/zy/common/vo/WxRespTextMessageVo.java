package com.jointown.zy.common.vo;

/**
 * 微信公众平台开发--回复消息之文本消息
 * 
 * @author aizhengdong
 *
 * @data 2015年2月10日
 */
public class WxRespTextMessageVo extends WxRespBaseMessageVo {
	/** 回复的消息内容 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
