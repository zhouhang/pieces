package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
public class WxOpinion implements Serializable {
	
	/**  
	 * 建议表ID，主键
	 * @author aizhengdong 2015年7月13日
	 */  
	private Long opId;
	
	/**  
	 * 用户名
	 * @author aizhengdong 2015年7月13日
	 */  
	private String opName;
	
	/**  
	 * 手机号码
	 * @author aizhengdong 2015年7月13日
	 */  
	private String opPhone;
	
	/**  
	 * 图片存放地址
	 * @author aizhengdong 2015年7月13日
	 */  
	private String opPicUrl;
	
	/**  
	 * 意见描述（小于等于1000字符）
	 * @author aizhengdong 2015年7月13日
	 */  
	private String opMemo;
	
	/**  
	 * 状态：0-未处理 1-已处理
	 * @author aizhengdong 2015年7月13日
	 */  
	private Short status;
	
	/**  
	 * 创建时间
	 * @author aizhengdong 2015年7月13日
	 */  
	private Date createTime;
	
	/**  
	 * 最后更新时间
	 * @author aizhengdong 2015年7月13日
	 */  
	private Date updateTime;
	
	/**  
	 * 最后一次更新人ID（后台人员）
	 * @author aizhengdong 2015年7月13日
	 */  
	private Integer updater;

	public Long getOpId() {
		return opId;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public String getOpPhone() {
		return opPhone;
	}

	public void setOpPhone(String opPhone) {
		this.opPhone = opPhone;
	}

	public String getOpPicUrl() {
		return opPicUrl;
	}

	public void setOpPicUrl(String opPicUrl) {
		this.opPicUrl = opPicUrl;
	}

	public String getOpMemo() {
		return opMemo;
	}

	public void setOpMemo(String opMemo) {
		this.opMemo = opMemo;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
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

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	@Override
	public String toString() {
		return "WxOpinion [opId=" + opId + ", opName=" + opName + ", opPhone="
				+ opPhone + ", opPicUrl=" + opPicUrl + ", opMemo=" + opMemo
				+ ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", updater=" + updater + "]";
	}
	
}
