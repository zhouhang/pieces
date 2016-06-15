package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 组织机构
 * @author zhouji
 * 2014年11月7日下午12:15:28
 */
public class Organization implements Serializable {
	private Integer id;
	//组织编号
	//private String orgCode;
	//组织名称
	private String orgName;
	//父ID
	private Integer parentId;
	//组织层级
	private Integer orgLevel;
	//状态 0:有效 1:删除
	private Integer status;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Organization(Integer id, String orgName,
			Integer parentId, Integer orgLevel, Integer status,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.orgName = orgName;
		this.parentId = parentId;
		this.orgLevel = orgLevel;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
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
	
	
}
