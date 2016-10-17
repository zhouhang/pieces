package com.ms.dao.model;

import java.io.Serializable;
import java.util.Date;


public class TrackingDetail  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer type;
	
	private String company;
	
	private String trackingNo;
	
	private String vistor;
	
	private String vistorPhone;
	
	private Date vistorTime;
	
	private Date createTime;
	
	public TrackingDetail(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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