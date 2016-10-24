package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class TrackingDetail  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer sendId;
	
	//1：物流信息 ，2：客户来访信息
	private Integer type;
	
	//快递公司
	private String company;
	
	//快递单号
	private String trackingNo;
	
	//来访人
	private String vistor;
	
	//来访电话
	private String vistorPhone;
	
	//来访时间
	private Date vistorTime;
	
	private Date createTime;
	
	public TrackingDetail(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSendId() {
		return sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	
	public String getVistor() {
		return vistor;
	}

	public void setVistor(String vistor) {
		this.vistor = vistor;
	}
	
	public String getVistorPhone() {
		return vistorPhone;
	}

	public void setVistorPhone(String vistorPhone) {
		this.vistorPhone = vistorPhone;
	}
	
	public Date getVistorTime() {
		return vistorTime;
	}

	public void setVistorTime(Date vistorTime) {
		this.vistorTime = vistorTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}