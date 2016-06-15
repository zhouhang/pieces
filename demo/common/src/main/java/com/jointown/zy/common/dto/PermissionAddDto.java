package com.jointown.zy.common.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 
 * @author lichenxiao
 * 增加权限DTO
 */
public class PermissionAddDto {
	
	//权限名称
	private String permisstionName;
	//权限路径
	private String permisstionOperationResource;
	//权限上级目录权限ID号
	private String permisstionParentId;

	
	public String getPermisstionName() {
		return permisstionName;
	}



	public void setPermisstionName(String permisstionName) {
		this.permisstionName = permisstionName;
	}



	public String getPermisstionOperationResource() {
		return permisstionOperationResource;
	}



	public void setPermisstionOperationResource(String permisstionOperationResource) {
		this.permisstionOperationResource = permisstionOperationResource;
	}



	public String getPermisstionParentId() {
		return permisstionParentId;
	}



	public void setPermisstionParentId(String permisstionParentId) {
		this.permisstionParentId = permisstionParentId;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
