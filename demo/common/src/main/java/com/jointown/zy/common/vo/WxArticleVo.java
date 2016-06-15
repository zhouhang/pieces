package com.jointown.zy.common.vo;

/**
 * 微信公众平台开发--多条图文消息信息
 * 
 * <p>
 * 图文消息个数，限制为10条以内，如果图文数超过10，则将会无响应
 * </p>
 * 
 * @author aizhengdong
 *
 * @data 2015年2月10日
 */
public class WxArticleVo {
	/** 图文消息标题 */
	private String Title;

	/** 图文消息描述 */
	private String Description;

	/** 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 */
	private String PicUrl;

	/** 点击图文消息跳转链接 */
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	
}
