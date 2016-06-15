package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:InfoWarehousModel
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:03:25
 * @version V1.0
 * @Description:入仓信息管理Model
 */
public class InfoWarehousModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 仓库申请ID
	 */
	private Integer warehouseId;
	/**
	 * 药材名称
	 */
	private String breedName;

	private String breed_Name;
	/**
	 * 药材数量
	 */
	private String breedAmount;
	/**
	 * 仓库类型
	 */
	private String warehouseType;
	/**
	 * 仓库面积
	 */
	private String warehouseArea;
	/**
	 * 记录状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 申请人姓名
	 */
	private String applyName;
	/**
	 * 申请人手机号
	 */
	private Long applyMobile;
	/**
	 * 申请来源
	 */
	private String applyResource;
	/**
	 * 仓库地区
	 */
	private String warehousePlace;
	/**
	 * 期望服务
	 */
	private String expectedService;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 货物所在地
	 */
	private String warehouseAddress;
	/**
	 * 品种id
	 */
	private Integer breedId;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 处理人Id
	 */
	private Integer handleId;

	/**
	 * 处理人
	 */
	private String handler;

	/**
	 * 备注
	 */
	private String remarks;

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getBreedAmount() {
		return breedAmount;
	}

	public void setBreedAmount(String breedAmount) {
		this.breedAmount = breedAmount;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(String warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

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

	public String getApplyResource() {
		return applyResource;
	}

	public void setApplyResource(String applyResource) {
		this.applyResource = applyResource;
	}

	public String getWarehousePlace() {
		return warehousePlace;
	}

	public void setWarehousePlace(String warehousePlace) {
		this.warehousePlace = warehousePlace;
	}

	public String getExpectedService() {
		return expectedService;
	}

	public void setExpectedService(String expectedService) {
		expectedService = expectedService.replace(",", "，");
		while (expectedService.endsWith("，")) {
			expectedService = expectedService.substring(0,
					expectedService.lastIndexOf("，"));
		}
		this.expectedService = expectedService;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		warehouseAddress = warehouseAddress.replace(",", "，");
		while (warehouseAddress.endsWith("，")) {
			warehouseAddress = warehouseAddress.substring(0,
					warehouseAddress.lastIndexOf("，"));
		}
		this.warehouseAddress = warehouseAddress;
	}

	public Integer getBreedId() {
		return breedId;
	}

	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Integer getHandleId() {
		return handleId;
	}

	public void setHandleId(Integer handleId) {
		this.handleId = handleId;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBreed_Name() {
		return breed_Name;
	}

	public void setBreed_Name(String breed_Name) {
		this.breed_Name = breed_Name;
	}

}