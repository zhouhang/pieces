package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class SampleAddress  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//收货地址
	private String address;
	
	//收货人
	private String receiver;
	
	//收货人电话
	private String receiverPhone;
	
	//备注信息
	private String remark;
	
	//寄样单id
	private Integer sendId;
	
	private Date createTime;
	
	private Date updateTime;
	
	public SampleAddress(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getSendId() {
		return sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
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