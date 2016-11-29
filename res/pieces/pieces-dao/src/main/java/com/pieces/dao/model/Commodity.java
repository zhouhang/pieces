package com.pieces.dao.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class Commodity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//原药品种
	private Integer categoryId;
	
	//商品名称
	@NotEmpty
	@Length(min = 1, max = 20)
	private String name;

	//商品标题
	@NotEmpty
	@Length(min = 1, max = 50)
	private String title;
	
	//规格等级
	@NotEmpty
	@Length(min = 1, max = 50)
	private String level;
	
	//切制规格 片型
	@NotEmpty
	@Length(min = 1, max = 20)
	private String spec;
	
	//原药产地
	@NotEmpty
	@Length(min = 1, max = 20)
	private String originOf;
	
	//执行标准
	@NotEmpty
	@Length(min = 1, max = 20)
	private String executiveStandard;
	
	//性状描述
	@NotEmpty
	@Length(min = 1, max = 200)
	private String exterior;
	
	//商品属性(json结构)
	private String attribute;
	
	//商品详情
	@NotEmpty
	private String details;
	
	//图片地址
	@NotEmpty
	private String pictureUrl;
	
	//状态
	@NotNull
	private Integer status;
	
	private Date createTime;
	
	public Commodity(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getOriginOf() {
		return originOf;
	}

	public void setOriginOf(String originOf) {
		this.originOf = originOf;
	}
	
	public String getExecutiveStandard() {
		return executiveStandard;
	}

	public void setExecutiveStandard(String executiveStandard) {
		this.executiveStandard = executiveStandard;
	}
	
	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}
	
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getDetails() {
		if (details!=null) {
			details = details.replace("&lt","<").replace("&gt",">");
		}
		return details;
	}

	public void setDetails(String details) {
		if (details!=null) {
			details = details.replace("&lt","<").replace("&gt",">");
		}
		this.details = details;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
	
}