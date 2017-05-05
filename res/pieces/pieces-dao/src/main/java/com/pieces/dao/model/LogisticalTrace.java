package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流跟踪信息用来存储快递鸟推送过来的信息
 */
public class LogisticalTrace  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//快递单号
	private String logisticCode;
	
	//快递公司编码
	private String shipperCode;
	
	//轨迹内容
	private String acceptStation;
	
	//轨迹时间
	private Date acceptTime;
	
	public LogisticalTrace(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}
	
	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	
	public String getAcceptStation() {
		return acceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		this.acceptStation = acceptStation;
	}
	
	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	
}