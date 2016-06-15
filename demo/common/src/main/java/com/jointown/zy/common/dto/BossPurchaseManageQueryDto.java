/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: BossPurchaseAuditQueryDto
 * @Description: 后台采购信息管理查询条件
 * @Author: 赵航
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public class BossPurchaseManageQueryDto extends BaseDto{

	private static final long serialVersionUID = -7989420677778824319L;

	/** 采购批次号 */
	private String purchaseCode;
	
	/** 采购品种 */
	private String breedName;
	
	/** 采购单位 */
	private String purchaseOrg;
	
	/** 会员名 */
	private String purchaser;
	
	/** 采购状态 */
	private String status;
	
	/** 发布时间(始) */
	private String publishTimeStart;
	
	/** 发布时间(末) */
	private String publishTimeEnd;
	
	/** 交易员 */
	private String tradersName;

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
	 * 获取采购状态
	 * @return 采购状态
	 */
	public String getStatus() {
	    return status;
	}

	/**
	 * 设定采购状态
	 * @param status 采购状态
	 */
	public void setStatus(String status) {
	    this.status = status;
	}

	/**
	 * 获取发布时间(始)
	 * @return 发布时间(始)
	 */
	public String getPublishTimeStart() {
	    return publishTimeStart;
	}

	/**
	 * 设定发布时间(始)
	 * @param publishTimeStart 发布时间(始)
	 */
	public void setPublishTimeStart(String publishTimeStart) {
	    this.publishTimeStart = publishTimeStart;
	}

	/**
	 * 获取发布时间(末)
	 * @return 发布时间(末)
	 */
	public String getPublishTimeEnd() {
	    return publishTimeEnd;
	}

	/**
	 * 设定发布时间(末)
	 * @param publishTimeEnd 发布时间(末)
	 */
	public void setPublishTimeEnd(String publishTimeEnd) {
	    this.publishTimeEnd = publishTimeEnd;
	}

	/**
	 * 获取交易员
	 * @return 交易员
	 */
	public String getTradersName() {
	    return tradersName;
	}

	/**
	 * 设定交易员
	 * @param tradersName 交易员
	 */
	public void setTradersName(String tradersName) {
	    this.tradersName = tradersName;
	}

}
