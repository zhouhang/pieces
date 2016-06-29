package com.jointown.zy.common.model;

import java.util.Date;

/**
 * @ClassName: MessageConfig
 * @Description: 短信配置model
 * @Author: ldp
 * @Date: 2015年9月7日
 * @Version: 1.0
 */
public class MessageConfig {

	/**配置项ID*/
	private Integer configId;
	/**通道名称*/
	private String channelName;
	/**备注*/
	private String memo;
	/**状态 1-使用 0-停用*/
	private String status;
	/**类名*/
	private String className;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**修改人ID（后台人员）*/
	private Integer updater;
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	
	
}
