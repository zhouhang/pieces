package com.jointown.zy.common.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @ClassName: BusiPurchaseDto
 * @Description: 珍药采购Dto
 * @Author: fanyuna
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public class BusiPurchaseDto {
	
	//主键
	private String purchaseId;
	//采购批次号
	private String purchaseCode;
    //采购单位
    private String purchaserOrg;
    //联系人
    private String contact;
    //联系电话
    private String contactPhone;
    //采购品种ID
    private Long breedId;
    //采购品种名称
    private String breedName;
    //采购数量
    private String quantity;
    //采购单位的编码
    private String wunitCode;
    //规格等级
    private String standardLevel;
    //产地
    private String origin;
    //质量要求
    private String qualityDescription;
    
    //交货地址
    private String deliveryAddress;
    //预计交货时间	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expectDeliveryTime;
    //采购有效期
    private String validPeriod;
    //发票要求编码
    private String receiptCode;
    //发票要求: 普通发票,增值税专用发票,无需发票
    private String receipt;
    //其他备注
    private String note;
    
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getPurchaserOrg() {
		return purchaserOrg;
	}
	public void setPurchaserOrg(String purchaserOrg) {
		this.purchaserOrg = purchaserOrg;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
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
    
	public String validate(){
		if(StringUtils.isBlank(purchaserOrg)){
			return "请填写采购单位";
		}
		if(StringUtils.isBlank(contact)){
			return "请填写联系人";
		}
		if(StringUtils.isBlank(contactPhone)){
			return "请填写联系电话";
		}
		if(StringUtils.isBlank(breedName)){
			return "请填写品种名称";
		}
		if(StringUtils.isBlank(quantity)){
			return "请填写采购数量";
		}
		if(StringUtils.isBlank(standardLevel)){
			return "请填写规格等级";
		}
		if(StringUtils.isBlank(origin)){
			return "请填写产地要求";
		}
		//暂住，待验证后加上
//		if(StringUtils.isBlank(qualityDescription)){
//			return "请填写对药材的质量要求";
//		}
		return "success";
	}
    
}
