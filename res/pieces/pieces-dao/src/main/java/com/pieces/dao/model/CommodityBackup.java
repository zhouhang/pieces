package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;



public class CommodityBackup  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//商品名称
	private String name;
	
	//切制规格
	private Integer spec;
	
	//原药产地
	private Integer originOf;
	
	//执行标准
	private String executiveStandard;

	//商品等级
	private Integer level;

	//生产厂家
	private String factory;
	
	//外观描述
	private String exterior;
	
	//商品图片地址
	private String pictureUrl;
	
	//详细信息
	private String details;
	
	private Integer status;
	
	//与商品相关联的二级品种ID
	private Integer categoryId;
	
	private Date createTime;
	

	
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
	
	public Integer getSpec() {
		return spec;
	}

	public void setSpec(Integer spec) {
		this.spec = spec;
	}
	
	public Integer getOriginOf() {
		return originOf;
	}

	public void setOriginOf(Integer originOf) {
		this.originOf = originOf;
	}
	
	public String getExecutiveStandard() {
		return executiveStandard;
	}

	public void setExecutiveStandard(String executiveStandard) {
		this.executiveStandard = executiveStandard;
	}
	
	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}
	
	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}