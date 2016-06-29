package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 微信公众平台开发--广告管理
 * 
 * @author lichenxiao
 *
 * @data 2015年3月9日
 */
public class WxAd implements Serializable {

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
	/** 状态 0-正式 1-删除 */
	private Integer status;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
		return "WxAd [adId=" + adId + ", adType=" + adType + ", adPostionName="
				+ adPostionName + ", adTitle=" + adTitle + ", adMemo=" + adMemo
				+ ", adUrl=" + adUrl + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", creater="
				+ creater + ", updater=" + updater + "]";
	}
}
