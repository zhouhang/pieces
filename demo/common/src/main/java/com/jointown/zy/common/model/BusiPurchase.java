package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: BusiPurchase
 * @Description: 采购表model
 * @Author: fanyuna
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public class BusiPurchase extends BusiPurchaseBatch implements Serializable {
	/** TODO */
	private static final long serialVersionUID = -7968707355791613232L;
	// 采购单ID
	private String purchaseId;
	// 采购品种ID
	private Long breedId;
	// 采购品种名称
	private String breedName;
	// 采购数量
	private String quantity;
	// 采购单位的编码
	private String wunitCode;
	// 规格等级
	private String standardLevel;
	// 产地
	private String origin;
	// 质量要求
	private String qualityDescription;
	// 交货省份
	// private String deliveryProvince;
	// 交货城市
	// private String deliveryCity;
	// 交货区县
	// private String deliveryDistrict;
	// 交货街道
	// private String deliveryStreet;
	// 交货地址
	private String deliveryAddress;
	// 预计交货时间
	private Date expectDeliveryTime;
	// 发票要求编码
	private String receiptCode;
	// 发票要求: 普通发票,增值税专用发票,无需发票
	private String receipt;
	// 其他备注
	private String note;
	// 采购状态
	private Integer status;
	// 是否有效，Y：有效，N：无效
	private String isValid;
	// 交易员 工作账号
	private String operator;
	// 修改时间
	private Date updateTime;
	// 审核人工作账号
	private String auditor;
	// 审核时间
	private Date auditTime;
	// 初始审核时间
	private Date firstAuditTime;
	// 成交报价ID
	private String adoptedQuoteId;

	// 审核拒绝理由
	private String rejectReason;

	// private Set<BusiPurchaseLog> busiPurchaseLogsPurchaseId = new
	// HashSet(0);;
	//
	// private Set<BusiQuote> busiQuotesPurchaseId = new HashSet(0);;

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getBreedId() {
		return breedId;
	}

	public void setBreedId(Long breedId) {
		this.breedId = breedId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getWunitCode() {
		return wunitCode;
	}

	public void setWunitCode(String wunitCode) {
		this.wunitCode = wunitCode;
	}

	public String getStandardLevel() {
		return standardLevel;
	}

	public void setStandardLevel(String standardLevel) {
		this.standardLevel = standardLevel;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getQualityDescription() {
		return qualityDescription;
	}

	public void setQualityDescription(String qualityDescription) {
		this.qualityDescription = qualityDescription;
	}

	public Date getExpectDeliveryTime() {
		return expectDeliveryTime;
	}

	public void setExpectDeliveryTime(Date expectDeliveryTime) {
		this.expectDeliveryTime = expectDeliveryTime;
	}

	public String getReceiptCode() {
		return receiptCode;
	}

	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getFirstAuditTime() {
		return firstAuditTime;
	}

	public void setFirstAuditTime(Date firstAuditTime) {
		this.firstAuditTime = firstAuditTime;
	}

	public String getAdoptedQuoteId() {
		return adoptedQuoteId;
	}

	public void setAdoptedQuoteId(String adoptedQuoteId) {
		this.adoptedQuoteId = adoptedQuoteId;
	}

	// public void setBusiPurchaseLogsPurchaseId(Set busiPurchaseLogsPurchaseId)
	// {
	// this.busiPurchaseLogsPurchaseId=busiPurchaseLogsPurchaseId;
	// }
	//
	// public Set<BusiPurchaseLog> getBusiPurchaseLogsPurchaseId() {
	// return busiPurchaseLogsPurchaseId;
	// }
	//
	// public void setBusiQuotesPurchaseId(Set busiQuotesPurchaseId) {
	// this.busiQuotesPurchaseId=busiQuotesPurchaseId;
	// }
	//
	// public Set<BusiQuote> getBusiQuotesPurchaseId() {
	// return busiQuotesPurchaseId;
	// }

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

}