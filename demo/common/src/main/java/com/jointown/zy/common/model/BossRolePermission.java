package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 后台角色权限
 * @author biran
 * 2014-11-25
 */
public class BossRolePermission implements Serializable {
	//权限ID
	private Integer permissionId;
	
	//角色ID
	private Integer roleId;
	
	//状态 0:有效 1:删除
	private Integer status;
	
	//创建时间
	private Date createTime;
	
	//修改时间
	private Date updateTime;
	
	public BossRolePermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BossRolePermission(Integer permissionId, Integer roleId,
			Integer status,Date createTime, Date updateTime
			) {
		super();
		this.permissionId = permissionId;
		this.roleId = roleId;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
