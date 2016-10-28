package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Ad  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//广告类型ID
	private Integer typeId;
	
	//广告描述
	private String name;
	
	//广告链接
	private String href;
	
	private Integer sort;
	
	//广告图片url
	private String pictureUrl;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer createMem;
	
	private Integer updateMem;

	// 0 禁用 1启用
	private Integer status;
	
	public Ad(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
	
	public Integer getCreateMem() {
		return createMem;
	}

	public void setCreateMem(Integer createMem) {
		this.createMem = createMem;
	}
	
	public Integer getUpdateMem() {
		return updateMem;
	}

	public void setUpdateMem(Integer updateMem) {
		this.updateMem = updateMem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}