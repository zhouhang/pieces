package com.jointown.zy.common.dto;

import java.io.Serializable;
import java.util.List;

import com.jointown.zy.common.model.BusiWarehouseApply;

/**
 * 
 * @ClassName: BusiWarehouseApplyDto
 * @Description: 入仓申请Dto
 * @Author: wangjunhu
 * @Date: 2015年5月20日
 * @Version: 1.0
 */
public class BusiWarehouseApplyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String applyName;
	
	private Long applyMobile;
	
	//private String warehousePlace;
	
	/**
	 * 货物所在地
	 * wanghao by 2015.06.16
	 */
	private String warehouseAddress;
	
	private String expectedService;
	
	private List<BusiWarehouseApply> busiWarehouseApplies;

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public Long getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(Long applyMobile) {
		this.applyMobile = applyMobile;
	}

	/*public String getWarehousePlace() {
		return warehousePlace;
	}

	public void setWarehousePlace(String warehousePlace) {
		warehousePlace = warehousePlace.replace(",", "，");
		while(warehousePlace.endsWith("，")){
			warehousePlace = warehousePlace.substring(0, warehousePlace.lastIndexOf("，"));
		}
		this.warehousePlace = warehousePlace;
	}*/

	public List<BusiWarehouseApply> getBusiWarehouseApplies() {
		return busiWarehouseApplies;
	}

	public void setBusiWarehouseApplies(
			List<BusiWarehouseApply> busiWarehouseApplies) {
		this.busiWarehouseApplies = busiWarehouseApplies;
	}

	public String getExpectedService() {
		return expectedService;
	}

	public void setExpectedService(String expectedService) {
		expectedService = expectedService.replace(",", "，");
		while(expectedService.endsWith("，")){
			expectedService = expectedService.substring(0, expectedService.lastIndexOf("，"));
		}
		this.expectedService = expectedService;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		warehouseAddress = warehouseAddress.replace(",", "，");
		while(warehouseAddress.endsWith("，")){
			warehouseAddress = warehouseAddress.substring(0, warehouseAddress.lastIndexOf("，"));
		}
		this.warehouseAddress = warehouseAddress;
	}
	
	
}
