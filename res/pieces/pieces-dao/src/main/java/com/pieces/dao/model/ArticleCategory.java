package com.pieces.dao.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class ArticleCategory  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//类别名
	@NotEmpty
	private String name;
	
	//状态：1激活 0 禁用
	private Integer status;
	
	//排序
	private Integer sort;
	
	//所属模块:1 帮助中心 2 新闻模块
	@NotNull
	private Integer model;
	
	private Integer createUser;
	
	private Date createTime;
	
	//分类图标
	private String icon;
	
	public ArticleCategory(){}
	
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}