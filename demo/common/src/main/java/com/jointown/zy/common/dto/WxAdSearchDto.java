package com.jointown.zy.common.dto;

import java.util.List;

/**
 * 微信广告查询Dto，用于前台的数据封装到后台使用
 * @author lichenxiao
 * 2015-3-9
 */
public class WxAdSearchDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Long adId;
	/** 广告显示类型*/
	private Integer adType;	
	/** 广告名称 */
	private String adPostionName;
	/** 广告标题 */
	private String adTitle;
	/** 广告内容 */
	private String adMemo;
	/** 广告链接地址 */
	private String adUrl;
	/** 开始时间 */
	private String startWxAdDate;
	/** 结束时间 */
	private String endWxAdDate;
	/** 创建人id */
	private Integer creater;
	/** 修改人id */
	private Integer updater;
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public Integer getAdType() {
		return adType;
	}
	public void setAdType(Integer adType) {
		this.adType = adType;
	}
	public String getAdPostionName() {
		return adPostionName;
	}
	public void setAdPostionName(String adPostionName) {
		this.adPostionName = adPostionName;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdMemo() {
		return adMemo;
	}
	public void setAdMemo(String adMemo) {
		this.adMemo = adMemo;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public String getStartWxAdDate() {
		return startWxAdDate;
	}
	public void setStartWxAdDate(String startWxAdDate) {
		this.startWxAdDate = startWxAdDate;
	}
	public String getEndWxAdDate() {
		return endWxAdDate;
	}
	public void setEndWxAdDate(String endWxAdDate) {
		this.endWxAdDate = endWxAdDate;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	@Override
	public String toString() {
		return "WxAdSearchDto [adId=" + adId + ", adType=" + adType
				+ ", adPostionName=" + adPostionName + ", adTitle=" + adTitle
				+ ", adMemo=" + adMemo + ", adUrl=" + adUrl
				+ ", startWxAdDate=" + startWxAdDate + ", endWxAdDate="
				+ endWxAdDate + ", creater=" + creater + ", updater=" + updater
				+ "]";
	}


}
