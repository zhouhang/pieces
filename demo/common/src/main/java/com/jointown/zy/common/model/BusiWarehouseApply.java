package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiWarehouseApply implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * ZYC.BUSI_WAREHOUSE_APPLY.WAREHOUSE_ID (仓库申请ID)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private Long warehouseId;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.BREED_NAME (药材名称)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private String breedName;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.BREED_AMOUNT (药材数量)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private String breedAmount;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.WAREHOUSE_TYPE (仓库类型)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private String warehouseType;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.WAREHOUSE_AREA (仓库面积)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private String warehouseArea;
    
    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.WAREHOUSE_PLACE (仓库地区)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    //private String warehousePlace;
    
    /**
     * 货物所在地
     * by wanghao 2015.06.16
     */
    private String warehouseAddress;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.STATUS (记录状态)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private Short status;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.CREATE_TIME (创建时间)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private Date createTime;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.UPDATE_TIME (更新时间)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private Date updateTime;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.APPLY_NAME (申请人姓名)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private String applyName;

    /**
     * ZYC.BUSI_WAREHOUSE_APPLY.APPLY_MOBILE (申请人手机号)
     * @ibatorgenerated 2015-05-19 21:28:09
     */
    private Long applyMobile;
    
    /**
     * ZYC.BUSI_PURCHASE_APPLY.APPLY_RESOURCE (申请来源)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String applyResource;
    
    /**
     * ZYC.BUSI_PURCHASE_APPLY.EXPECTED_SERVICE (期望服务)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String expectedService;
    
    //品种id
    private Integer breedId;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
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

  /*  public String getWarehousePlace() {
		return warehousePlace;
	}

	public void setWarehousePlace(String warehousePlace) {
		this.warehousePlace = warehousePlace;
	}*/

	public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
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

	public String getExpectedService() {
		return expectedService;
	}

	public void setExpectedService(String expectedService) {
		this.expectedService = expectedService;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public Integer getBreedId() {
		return breedId;
	}

	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}
}