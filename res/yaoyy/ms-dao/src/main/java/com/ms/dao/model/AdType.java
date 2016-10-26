package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class AdType  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private Date createTime;
	
	public AdType(){}
	
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
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}