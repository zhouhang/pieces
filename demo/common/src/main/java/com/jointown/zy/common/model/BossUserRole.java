package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于关联角色
 * 用户角色表
 * description 
 * author ldp
 * 2014年12月6日
 */
public class BossUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户ID
	private int userId;
	//角色ID
	private int roleId;
	//状态
	private int status;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//角色对象
	private BossRole bossRole;
	public BossUserRole() {
		super();
	}
	public BossUserRole(int userId, int roleId, int status, Date createTime,
			Date updateTime, BossRole bossRole) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.bossRole = bossRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
	public BossRole getBossRole() {
		return bossRole;
	}
	public void setBossRole(BossRole bossRole) {
		this.bossRole = bossRole;
	}

}
