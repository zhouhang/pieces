package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Admin  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String username;
	
	private String password;
	
	private Date createDate;
	
	public Admin(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}