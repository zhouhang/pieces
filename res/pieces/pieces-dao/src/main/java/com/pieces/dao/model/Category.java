package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;



public class Category  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private Integer partenId;
	
	//别名
	private String aliases;
	
	private Integer status;
	
	//类目级别 1 代表一级商品类别. 2 代表二级商品类别.
	private Integer level;
	
	private Date createTime;
	
	public Category(){}
	
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
	
	public Integer getPartenId() {
		return partenId;
	}

	public void setPartenId(Integer partenId) {
		this.partenId = partenId;
	}
	
	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}