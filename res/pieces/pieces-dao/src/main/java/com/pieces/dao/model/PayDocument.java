package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 存储支付记录提交的图片信息
 */
public class PayDocument  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer payRecordId;
	
	private String path;
	
	private Date createDate;
	
	public PayDocument(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPayRecordId() {
		return payRecordId;
	}

	public void setPayRecordId(Integer payRecordId) {
		this.payRecordId = payRecordId;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}