package com.pieces.dao.vo;

import com.pieces.dao.model.Logistical;

public class LogisticalVo extends Logistical{
	//用户id
	private Integer userId;

	// 代理商id
	private Integer agentId;

	//运单号
	private String lCode;
	//订单号
	private String oCode;
	//收货地址
	private Integer addr;
	//发货日期
	private String shipDateStr;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getlCode() {
		return lCode;
	}
	public void setlCode(String lCode) {
		this.lCode = lCode;
	}
	public String getoCode() {
		return oCode;
	}
	public void setoCode(String oCode) {
		this.oCode = oCode;
	}
	public Integer getAddr() {
		return addr;
	}
	public void setAddr(Integer addr) {
		this.addr = addr;
	}
	public String getShipDateStr() {
		return shipDateStr;
	}
	public void setShipDateStr(String shipDateStr) {
		this.shipDateStr = shipDateStr;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
}