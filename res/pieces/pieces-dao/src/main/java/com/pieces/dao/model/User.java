package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 前台用户
 * @author feng
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private Integer id;

	//用户名
	private String userName;

	//密码
	private String password;

	//密码盐
	private String salt;

	//企业全称
	private String companyFullName;

	//关联地区ID
	private Integer areaId;

	//联系人姓名
	private String contactName;

	//联系人手机
	private String contactMobile;

	//用户状态： 0：有效 1：删除 2：未激活 3：锁定
	private Integer status;

	//创建时间
	private Date createTime;

	//修改时间
	private Date updateTime;

	//角色id
	private String roleId;

	//在线状态（0：离线，1：在线）
	private Integer onlineStatus;

	//是否绑定erp（0：未绑定，1：绑定）
	private Integer bindErp;

	//创建渠道（0：前台  1：后台）
	private Integer createChannel;

	//地址全称
	private String areaFull;

	public User(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Integer getBindErp() {
		return bindErp;
	}

	public void setBindErp(Integer bindErp) {
		this.bindErp = bindErp;
	}

	public Integer getCreateChannel() {
		return createChannel;
	}

	public void setCreateChannel(Integer createChannel) {
		this.createChannel = createChannel;
	}

	public String getAreaFull() {
		return areaFull;
	}

	public void setAreaFull(String areaFull) {
		this.areaFull = areaFull;
	}

}
