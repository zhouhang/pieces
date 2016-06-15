package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 后台角色
 * @author biran
 * 2014-11-27
 */
public class BossRole implements Serializable {
	//主键ID
	private Integer roleId;
	
	//角色名
	private String roleName;
	
	//状态 0:有效 1:删除
	private Integer status;
	
	//创建时间
	private Date createTime;
	
	//修改时间
	private Date updateTime;
	
	public BossRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BossRole(Integer roleId, String roleName, Integer status,
			Date createTime, Date updateTime) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
