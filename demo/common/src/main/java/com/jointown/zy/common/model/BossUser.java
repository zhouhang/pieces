package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BossUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	//用户名
	private String userCode;
	//姓名
	private String userName;
	//工号
	private String userNo;
	//密码
	private String password;
	//密码盐
	private String salt;
	//电话
	private String mobile;
	//手机
	private String phone;
	//邮箱
	private String email;
	//组织ID
	private Integer orgId;
	//状态 0：有效 1：删除 2：未激活 3：锁定
	private Integer status;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//过期时间
	private Date expireTime;
	//组织名称
	private String orgName;
	//角色id
	private String roleId;
	//角色名称
	private String roleName;
	public BossUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BossUser(Integer userId, String userCode, String userName,
			String userNo, String password, String salt, String mobile,
			String phone, String email, Integer orgId, Integer status,
			Date createTime, Date updateTime, Date expireTime, String orgName,
			String roleId, String roleName) {
		super();
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.userNo = userNo;
		this.password = password;
		this.salt = salt;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.orgId = orgId;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.expireTime = expireTime;
		this.orgName = orgName;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public String getOrgName() {
		return orgName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
