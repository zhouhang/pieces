package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 采购信息Vo
 *
 * @author aizhengdong
 *
 * @data 2015年6月19日
 */
public class BusiPurchaseApplyVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 采购信息ID */
    private Long purchaseId;

    /** 发布人姓名 */
    private String applyName;
    
    /** 发布人手机号码  */
    private String applyMobile;
    
    /** 品种名称 */
    private String breedName;
    
    /** 规格名称 */
    private String breedStandardLevel;
    
    /** 价格 */
  	private String breedPrice;
    
    /** 品种产地 */
    private String breedPlace;
    
    /** 数量 */
    private String breedAmount;
    
    /** 状态：未处理-0、有效-1、无效-2、用户撤销-3 */
    private Short status;
    
    /** 创建时间 */
    private Date createTime;

    /** 最后更新时间 */
    private Date updateTime;

    /** 审核时间*/
    private Date approveTime;

    /** 审核人姓名*/
    private String approverName;
    
    /** 信息来源 */
    private String applyResource;
    
    /** 描述*/
    private String depict;
    
	@Override
	public String toString() {
		return "BusiPurchaseApplyVo [purchaseId=" + purchaseId + ", applyName="
				+ applyName + ", applyMobile=" + applyMobile + ", breedName="
				+ breedName + ", breedStandardLevel=" + breedStandardLevel
				+ ", breedPrice=" + breedPrice + ", breedPlace=" + breedPlace
				+ ", breedAmount=" + breedAmount + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", approveTime=" + approveTime + ", approverName="
				+ approverName + ", applyResource=" + applyResource
				+ ", depict=" + depict + "]";
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
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

	public String getBreedAmount() {
		return breedAmount;
	}

	public void setBreedAmount(String breedAmount) {
		this.breedAmount = breedAmount;
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

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApplyResource() {
		return applyResource;
	}

	public void setApplyResource(String applyResource) {
		this.applyResource = applyResource;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}
    

    
}