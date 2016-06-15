package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 微信公众平台开发--活动管理
 * 
 * @author aizhengdong
 *
 * @data 2015年2月14日
 */
public class WxActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long activityId;
	
	/** 名称 */
	private String name;
	
	/** 类型 */
	private Integer type;
	
	/** 描述 */
	private String memo;

	/** 文章路径 */
	private String url;

	/** 图片路径 */
	private String picUrl;

	/** 排序号 */
	private Integer sortno;
	
	/** 状态 0-正式 1-删除 */
	private Integer status;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 创建人ID */
	private Integer creater;
	
	/** 更新人ID */
	private Integer updater;
	
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSortno() {
		return sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
