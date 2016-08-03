package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


public class Ad  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//广告标题
	private String title;
	
	//广告类型
	private Integer typeId;
	
	//广告图片
	private String pictureUrl;
	
	//开始时间
	private Date startTime;
	
	//结束时间
	private Date endTime;
	
	//链接
	private String link;
	
	//排序
	private String sort;
	
	//激活状态
	private Boolean status;
	
	//创建时间
	private Date createTime;
	
	public Ad(){}
	
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
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}