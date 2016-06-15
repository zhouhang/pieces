package com.jointown.zy.common.model;

import java.io.Serializable;

/**
 * 仓库管理
 * @author wangjunhu
 *	2014-12-18
 */
public class BusiWareHouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//仓库ID
	private String wareHouseId;
	//仓库名称
	private String wareHouseName;
	//仓库编号
	private String wareHouseCode;
	//仓库描述
	private String wareHouseDes;
	//仓库链接
	private String wareHouseUrl;
	//仓库地区（省份）
	private String province;
	//仓库类型 （常温，阴凉，冷冻，冷藏）
    private String whtype;
    //仓库类别（自有，合作，租用，临时）
    private String whcategory;
    //仓库地址
    private String whaddress;
    //仓库联系人
    private String whcontact;
    //仓库联系电话
    private String whtelephone;
    //仓库状态
    private Short status;

	
	
	
	public BusiWareHouse() {
		super();
	}

	public BusiWareHouse(String wareHouseId, String wareHouseName,
			String wareHouseCode, String wareHouseDes, String wareHouseUrl) {
		this.wareHouseId = wareHouseId;
		this.wareHouseName = wareHouseName;
		this.wareHouseCode = wareHouseCode;
		this.wareHouseDes = wareHouseDes;
		this.wareHouseUrl = wareHouseUrl;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}

	public String getWareHouseDes() {
		return wareHouseDes;
	}

	public void setWareHouseDes(String wareHouseDes) {
		this.wareHouseDes = wareHouseDes;
	}

	public String getWareHouseUrl() {
		return wareHouseUrl;
	}

	public void setWareHouseUrl(String wareHouseUrl) {
		this.wareHouseUrl = wareHouseUrl;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getWhtype() {
		return whtype;
	}

	public void setWhtype(String whtype) {
		this.whtype = whtype;
	}

	public String getWhcategory() {
		return whcategory;
	}

	public void setWhcategory(String whcategory) {
		this.whcategory = whcategory;
	}

	public String getWhaddress() {
		return whaddress;
	}

	public void setWhaddress(String whaddress) {
		this.whaddress = whaddress;
	}

	public String getWhcontact() {
		return whcontact;
	}

	public void setWhcontact(String whcontact) {
		this.whcontact = whcontact;
	}

	public String getWhtelephone() {
		return whtelephone;
	}

	public void setWhtelephone(String whtelephone) {
		this.whtelephone = whtelephone;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	
		
}
