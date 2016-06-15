package com.jointown.zy.common.vo;

import java.util.Date;

/**
 * 统一任务(首页反馈信息)Vo
 *
 * @author ff
 *
 * @data 2015年11月02日
 */
public class HomePageFeedBackVo {
	/** 编号：任务ID */
    private Long homePageId;

    /** 业务点 */
    private String type;
    
    /** 内容  */
    private String content;
    
    /** 备注 */
    private String remark;
    
    /** 备注 */
    private String mobile;
    
    /** 状态 */
    private String status;
    
    /** 创建时间 */
    private Date createTime;

    /** 处理时间 */
    private Date oprateTime;
    
    /** 处理人*/
    private String operator;

	public Long getHomePageId() {
		return homePageId;
	}

	public void setHomePageId(Long homePageId) {
		this.homePageId = homePageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOprateTime() {
		return oprateTime;
	}

	public void setOprateTime(Date oprateTime) {
		this.oprateTime = oprateTime;
	}


	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    
}