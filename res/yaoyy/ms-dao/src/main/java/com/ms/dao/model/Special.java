package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Special  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//标题
	private String title;
	
	private String pictuerUrl;
	
	private String description;
	
	//0 禁用 1 启用
	private Integer status;
	
	private Date updateTime;
	
	private Date createTime;
	
	private Integer sort;
	
	//修改人id
	private Integer updateMem;
	
	private Integer createMem;
	
	public Special(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPictuerUrl() {
		return pictuerUrl;
	}

	public void setPictuerUrl(String pictuerUrl) {
		this.pictuerUrl = pictuerUrl;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getUpdateMem() {
		return updateMem;
	}

	public void setUpdateMem(Integer updateMem) {
		this.updateMem = updateMem;
	}
	
	public Integer getCreateMem() {
		return createMem;
	}

	public void setCreateMem(Integer createMem) {
		this.createMem = createMem;
	}
	
}