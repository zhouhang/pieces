package com.jointown.zy.common.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * wms 仓库接口Dto
 * @author ldp
 * date 2015年3月23日
 * version 1.0
 */
public class WmsWareHouseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346819176620711805L;

	/** 仓库编号*/
	private String wareHouseCode;
	/** 仓库名称*/
	private String wareHouseName;
	/** 仓库描述*/
	private String wareHouseDes;
	
	private Integer status;
	
	//仓库地区（省份）
		private String district;
		//仓库类型 （常温，阴凉，冷冻，冷藏）
	    private String type;
	    //仓库类别（自有，合作，租用，临时）
	    private String category;
	    //仓库地址
	    private String address;
	    //仓库联系人
	    private String contact;
	    //仓库联系电话
	    private String telephone;
	
	
	public WmsWareHouseDto() {
		super();
	}

	public WmsWareHouseDto(String wareHouseCode, String wareHouseName,
			String wareHouseDes) {
		super();
		this.wareHouseCode = wareHouseCode;
		this.wareHouseName = wareHouseName;
		this.wareHouseDes = wareHouseDes;
	}

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getWareHouseDes() {
		return wareHouseDes;
	}

	public void setWareHouseDes(String wareHouseDes) {
		this.wareHouseDes = wareHouseDes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	
	
	
}
