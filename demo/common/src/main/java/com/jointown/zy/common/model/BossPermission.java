package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 后台权限
 * @author biran
 * 2014-11-25
 */
public class BossPermission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	//是否已经授权
	private Integer isPermissioned;
	
	//层级路径
	private String path;

	//父类名称
	private String parentName;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public BossPermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BossPermission(Integer permissionId, String permissionName,
			Integer parentId, Integer type, String operationResource, Integer status,
			Date createTime, Date updateTime,String sortNo,String className,List<BossPermission> sonList,
			String path) {
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
		this.className = className;
		this.sonList = sonList;
		this.path = path;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	public BossPermission setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
		return this;
	}
	
	public String getPermissionName() {
		return permissionName;
	}
	public BossPermission setPermissionName(String permissionName) {
		this.permissionName = permissionName;
		return this;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	public BossPermission setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public Integer getType() {
		return type;
	}
	public BossPermission setType(Integer type) {
		this.type = type;
		return this;
	}
	
	public String  getOperationResource() {
		return operationResource;
	}
	public BossPermission setOperationResource(String operationResource) {
		this.operationResource = operationResource;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public BossPermission setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public BossPermission setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public BossPermission setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	public String getSortNo() {
		return sortNo;
	}
	public BossPermission setSortNo(String sortNo) {
		this.sortNo = sortNo;
		return this;
	}
	
	public String getClassName() {
		return className;
	}
	public BossPermission setClassName(String className) {
		this.className = className;
		return this;
	}
	
	public List<BossPermission> getSonList() {
		return sonList;
	}
	public BossPermission setSonList(List<BossPermission> sonList) {
		this.sonList = sonList;
		return this;
	}
	
	
	public Integer getIsPermissioned() {
		return isPermissioned;
	}
	public BossPermission setIsPermissioned(Integer isPermissioned) {
		this.isPermissioned = isPermissioned;
		return this;
	}
	
	public String getPath() {
		return path;
	}
	public BossPermission setPath(String path) {
		this.path = path;
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(super.equals(obj))
			return true;
		boolean result = false;
		BossPermission param = (BossPermission)obj;
		result = permissionName==null?(param.getPermissionName()==null?true:false) : permissionName.equals(param.getPermissionName());
		if(result){
			result = operationResource==null?(param.getOperationResource()==null?true:false) : operationResource.equals(param.getOperationResource());
		}
		return result;
	}
	
}
