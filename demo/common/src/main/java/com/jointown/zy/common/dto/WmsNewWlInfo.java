package com.jointown.zy.common.dto;

import java.io.Serializable;

public class WmsNewWlInfo implements Serializable {

	/** 仓单编号 */
	private String wlId;
	
	/** 实际分割数量 */
	private Double actualCount;
	
	/** 货主 */
	private String userName;
	
	/** 父仓单号 */
	private String pWlId;
	
	/** 订单号 */
	private String orderId;
	
	/** 合同编号*/
	private String contractNum;
	
	//add by fanyuna 2015.11.11 仓单表新增了字段，分割时新仓单的商品件数、已出库数量及仓单状态会发生变化
		/** 商品件数 */
		private Integer skuNumber;
		/** 已出库数量  是件数 */
		private Integer outedNumber;
		/** 仓单状态 */
		private Integer status;
		

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public Double getActualCount() {
		return actualCount;
	}

	public void setActualCount(Double actualCount) {
		this.actualCount = actualCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getpWlId() {
		return pWlId;
	}

	public void setpWlId(String pWlId) {
		this.pWlId = pWlId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public Integer getSkuNumber() {
		return skuNumber;
	}

	public void setSkuNumber(Integer skuNumber) {
		this.skuNumber = skuNumber;
	}

	public Integer getOutedNumber() {
		return outedNumber;
	}

	public void setOutedNumber(Integer outedNumber) {
		this.outedNumber = outedNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
