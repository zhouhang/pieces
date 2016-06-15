package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiPurchaseApply implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * ZYC.BUSI_PURCHASE_APPLY.PURCHASE_ID (采购申请ID)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private Long purchaseId;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.BREED_NAME (药材名称)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String breedName;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.BREED_STANDARD_LEVEL (等级规格)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String breedStandardLevel;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.BREED_AMOUNT (药材数量)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String breedAmount;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.BREED_PRICE (药材价格)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String breedPrice;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.BREED_PLACE (药材产地)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String breedPlace;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.BREED_DESC (药材描述)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String breedDesc;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.STATUS (记录状态)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private Short status;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.CREATE_TIME (创建时间)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private Date createTime;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.UPDATE_TIME (更新时间)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private Date updateTime;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.APPLY_NAME (申请人姓名)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String applyName;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.APPLY_MOBILE (申请人手机号)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private Long applyMobile;
    
    /**
     * ZYC.BUSI_PURCHASE_APPLY.APPLY_RESOURCE (申请来源)
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    private String applyResource;
    
    /**
     * ZYC.BUSI_PURCHASE_APPLY.APPROVE_TIME (审核时间（处理时间）)
     * @ibatorgenerated 2015-06-15
     */
    private Date approveTime;

    /**
     * ZYC.BUSI_PURCHASE_APPLY.APPROVER (审核人ID（处理人）)
     * @ibatorgenerated 2015-06-15
     */
    private Long approver;
    
    /**
     * ZYC.BUSI_PURCHASE_APPLY.DEPICT (描述)
     * @ibatorgenerated 2015-06-15
     */
    private String depict;
    
    /**
     * ZYC.BUSI_PURCHASE_APPLY.REMARKS (备注)
     * @ibatorgenerated 2015-06-15
     */
    private String remarks;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedStandardLevel() {
        return breedStandardLevel;
    }

    public void setBreedStandardLevel(String breedStandardLevel) {
        this.breedStandardLevel = breedStandardLevel;
    }

    public String getBreedAmount() {
        return breedAmount;
    }

    public void setBreedAmount(String breedAmount) {
        this.breedAmount = breedAmount;
    }

    public String getBreedPrice() {
        return breedPrice;
    }

    public void setBreedPrice(String breedPrice) {
        this.breedPrice = breedPrice;
    }

    public String getBreedPlace() {
        return breedPlace;
    }

    public void setBreedPlace(String breedPlace) {
        this.breedPlace = breedPlace;
    }

    public String getBreedDesc() {
        return breedDesc;
    }

    public void setBreedDesc(String breedDesc) {
        this.breedDesc = breedDesc;
    }

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

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Long getApprover() {
		return approver;
	}

	public void setApprover(Long approver) {
		this.approver = approver;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}  
	
	
}