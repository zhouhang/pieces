package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class User  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//0：注册用户，1：申请寄样生成的用户 -1 禁用用户
	private Integer type;
	
	//手机号
	private String phone;
	
	//用户密码
	private String password;
	
	private String salt;
	
	//用户公众号对应的openid
	private String openid;
	
	private Date updateTime;
	
	private Date createTime;
	
	public User(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}