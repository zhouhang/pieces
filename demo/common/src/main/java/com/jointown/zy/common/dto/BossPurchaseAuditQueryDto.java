/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: BossPurchaseAuditQueryDto
 * @Description: 后台审核采购信息查询条件
 * @Author: 赵航
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public class BossPurchaseAuditQueryDto extends BaseDto{

	private static final long serialVersionUID = -7989420677778824319L;

	/** 采购批次号 */
	private String purchaseCode;
	
	/** 采购品种 */
	private String breedName;
	
	/** 采购单位 */
	private String purchaseOrg;
	
	/** 会员名 */
	private String purchaser;
	
	/** 审核状态 */
	private String auditStatus;
	
	/** 创建时间(始) */
	private String createTimeStart;
	
	/** 创建时间(末) */
	private String createTimeEnd;

	/**
	 * 获取采购批次号
	 * @return 采购批次号
	 */
	public String getPurchaseCode() {
	    return purchaseCode;
	}

	/**
	 * 设定采购批次号
	 * @param purchaseCode 采购批次号
	 */
	public void setPurchaseCode(String purchaseCode) {
	    this.purchaseCode = purchaseCode;
	}

	/**
	 * 获取采购品种
	 * @return 采购品种
	 */
	public String getBreedName() {
	    return breedName;
	}

	/**
	 * 设定采购品种
	 * @param breedName 采购品种
	 */
	public void setBreedName(String breedName) {
	    this.breedName = breedName;
	}

	/**
	 * 获取采购单位
	 * @return 采购单位
	 */
	public String getPurchaseOrg() {
	    return purchaseOrg;
	}

	/**
	 * 设定采购单位
	 * @param purchaseOrg 采购单位
	 */
	public void setPurchaseOrg(String purchaseOrg) {
	    this.purchaseOrg = purchaseOrg;
	}

	/**
	 * 获取会员名
	 * @return 会员名
	 */
	public String getPurchaser() {
	    return purchaser;
	}

	/**
	 * 设定会员名
	 * @param purchaser 会员名
	 */
	public void setPurchaser(String purchaser) {
	    this.purchaser = purchaser;
	}

	/**
	 * 获取审核状态
	 * @return 审核状态
	 */
	public String getAuditStatus() {
	    return auditStatus;
	}

	/**
	 * 设定审核状态
	 * @param auditStatus 审核状态
	 */
	public void setAuditStatus(String auditStatus) {
	    this.auditStatus = auditStatus;
	}

	/**
	 * 获取创建时间(始)
	 * @return 创建时间(始)
	 */
	public String getCreateTimeStart() {
	    return createTimeStart;
	}

	/**
	 * 设定创建时间(始)
	 * @param createTimeStart 创建时间(始)
	 */
	public void setCreateTimeStart(String createTimeStart) {
	    this.createTimeStart = createTimeStart;
	}

	/**
	 * 获取创建时间(末)
	 * @return 创建时间(末)
	 */
	public String getCreateTimeEnd() {
	    return createTimeEnd;
	}

	/**
	 * 设定创建时间(末)
	 * @param createTimeEnd 创建时间(末)
	 */
	public void setCreateTimeEnd(String createTimeEnd) {
	    this.createTimeEnd = createTimeEnd;
	}
}
