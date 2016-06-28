package com.jointown.zy.common.dto;

import java.io.Serializable;

public class WmsOldWlInfo implements Serializable {
	/** 仓单编号 */
	private String wlId;
	
	/** 分割后的数量 */
	private Double remainCount;
	
	/** 合同编号*/
	private String contractNum;
	
	//add by fanyuna 2015.11.11 仓单表新增了字段，分割时原仓单的商品件数、已出库数量及仓单状态会发生变化
	/** 商品件数 */
	private Integer skuNumber;
	/** 已出库数量  是件数*/
	private Integer outedNumber;
	/** 仓单状态 */
	private Integer status;
	
	

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public Double getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Double remainCount) {
		this.remainCount = remainCount;
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
