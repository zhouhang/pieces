package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Resources  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private String path;
	
	private Integer pid;
	
	private Date createDate;
	
	public Resources(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}