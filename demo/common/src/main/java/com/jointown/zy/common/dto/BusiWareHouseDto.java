package com.jointown.zy.common.dto;

import com.jointown.zy.common.model.BusiWareHouse;

/**
 * 仓库管理Dto
 * @author wangjunhu
 * 2014-12-29
 */
public class BusiWareHouseDto {

	private String wareHouseId;
	private String wareHouseName;
	private String wareHouseCode;
	private String wareHouseDes;
	private String wareHouseUrl;
	private BusiWareHouse busiWareHouse;
	
	public BusiWareHouseDto() {
		super();
		busiWareHouse = new BusiWareHouse();
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
		busiWareHouse.setWareHouseId(wareHouseId);
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
		busiWareHouse.setWareHouseName(wareHouseName);
	}

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
		busiWareHouse.setWareHouseCode(wareHouseCode);
	}

	public String getWareHouseDes() {
		return wareHouseDes;
	}

	public void setWareHouseDes(String wareHouseDes) {
		this.wareHouseDes = wareHouseDes;
		busiWareHouse.setWareHouseDes(wareHouseDes);
	}

	public String getWareHouseUrl() {
		return wareHouseUrl;
	}

	public void setWareHouseUrl(String wareHouseUrl) {
		this.wareHouseUrl = wareHouseUrl;
		busiWareHouse.setWareHouseUrl(wareHouseUrl);
	}

	public BusiWareHouse getBusiWareHouse() {
		return busiWareHouse;
	}

	public void setBusiWareHouse(BusiWareHouse busiWareHouse) {
		this.busiWareHouse = busiWareHouse;
	}
}
