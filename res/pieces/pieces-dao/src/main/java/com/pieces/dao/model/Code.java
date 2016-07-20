package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;



public class Code  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//自定义的Code值
	private String code;
	
	//显示值
	private String name;
	
	//关联分类品种ID
	private Integer relatedCode;
	
	//记录状态 -1 删除, 0 禁用, 1 启用
	private Integer status;
	
	//Code 类型: 例如 切制规格, 原药产地..... 等
	private Integer typeId;
	
	//商品列表页，该code是否选中
	private boolean checked;
	
	private Date createTime;
	
	public Code(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getRelatedCode() {
		return relatedCode;
	}

	public void setRelatedCode(Integer relatedCode) {
		this.relatedCode = relatedCode;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}