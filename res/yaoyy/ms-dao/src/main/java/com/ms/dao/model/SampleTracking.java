package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class SampleTracking  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//操作人id
	private Integer operator;
	
	//操作人名称
	private String name;
	
	//0：后台人员,1:前台用户
	private Integer type;
	
	//寄样单id
	private Integer sendId;
	
	//追踪类型 0：申请寄样，1：同意寄样，2：拒绝寄样，3:客户预约，4：寄送样品，5：客户已收货，6：跟踪记录，7：客户留言，8：寄样单受理完成
	private Integer recordType;
	
	//寄样追踪附加内容：如同意理由，等等
	private String extra;
	
	private Date createTime;
	
	public SampleTracking(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getSendId() {
		return sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}
	
	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
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
	
}