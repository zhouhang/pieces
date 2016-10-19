package com.ms.dao.model;

import java.io.Serializable;


public class RoleResources implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer roleId;
	
	private Integer resourcesId;
	
	public RoleResources(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(Integer resourcesId) {
		this.resourcesId = resourcesId;
	}
	
}