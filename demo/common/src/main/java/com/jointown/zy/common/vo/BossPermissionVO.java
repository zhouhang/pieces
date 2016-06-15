package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jointown.zy.common.model.BossPermission;
/**
 * 后台权限vo
 * @author biran
 * 2014-11-25
 */
@XmlRootElement
public class BossPermissionVO  {
	//主键ID
	private Integer permissionId;
	
	//权限名
	private String permissionName;
	
	//父权限ID
	private Integer parentId;
	
	//权限类型 0:系统 1:目录 2:菜单 3:按钮
	private Integer type;
	
	//对应于不同的权限类别记录权限可以操作的资源。譬如 页面URL,menu的URL,button的标记等等
	private String operationResource ;
	
	//状态 0:有效 1:删除
	private Integer status;
	
	//创建时间
	private Date createTime;
	
	//修改时间
	private Date updateTime;
	
	//排序编号
	private String sortNo;
	
	//样式
	private String className;
		
	//存储下级权限菜单list
	private List<BossPermission> sonList;
		
	private String parentName;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public BossPermissionVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BossPermissionVO(Integer permissionId, String permissionName,
			Integer parentId, Integer type, String operationResource, Integer status,
			Date createTime, Date updateTime,String sortNo) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.parentId = parentId;
		this.type = type;
		this.operationResource = operationResource;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sortNo = sortNo;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String  getOperationResource() {
		return operationResource;
	}
	public void setOperationResource(String operationResource) {
		this.operationResource = operationResource;
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
	
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public List<BossPermission> getSonList() {
		return sonList;
	}
	public void setSonList(List<BossPermission> sonList) {
		this.sonList = sonList;
	}
	
}
