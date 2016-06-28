package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jointown.zy.common.model.WxOpinion;

/**
 * 意见
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
public class WxOpinionVo extends WxOpinion implements Serializable {
	/**
	 * 图片地址
	 * 
	 * @author aizhengdong 2015年8月24日
	 */
	private String[] picUrls;

	/**
	 * 更新人姓名
	 * 
	 * @author aizhengdong 2015年8月24日
	 */
	private String updaterName;

	public String[] getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String[] picUrls) {
		this.picUrls = picUrls;
	}

	public String getUpdaterName() {
		return updaterName;
	}

	public void setUpdaterName(String updaterName) {
		this.updaterName = updaterName;
	}

}
