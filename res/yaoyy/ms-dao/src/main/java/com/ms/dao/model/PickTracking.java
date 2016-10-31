package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class PickTracking  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//操作者类别
	private Integer opType;
	
	//操作者id
	private Integer operator;
	
	//记录类型，同意/拒绝/记录/完成
	private Integer recordType;
	
	private Integer pickId;
	
	//同意理由，拒绝理由，记录内容等附加内容
	private String extra;

	private String name;
	
	private Date createTime;
	
	private Date updateTime;
	
	public PickTracking(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	
	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	
	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}


	public Integer getPickId() {
		return pickId;
	}

	public void setPickId(Integer pickId) {
		this.pickId = pickId;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
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
	
}