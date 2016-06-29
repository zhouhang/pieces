package com.jointown.zy.common.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 
 * @author lichenxiao
 * 查询权限DTO
 */
public class PermissionSearchDto {
	
	//权限名称
	private String permisstionName;
	//权限路径
	private String operationResource;
	//权限上级目录权限ID号
	private String parentId;

	
	public String getPermisstionName() {
		return permisstionName;
	}



	public void setPermisstionName(String permisstionName) {
		this.permisstionName = permisstionName;
	}



	public void setOperationResource(String operationResource) {
		this.operationResource = operationResource;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public String getOperationResource() {
		return operationResource;
	}



	
}
