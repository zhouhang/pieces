package com.jointown.zy.common.solr;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

import com.jointown.zy.common.util.TimeUtil;

public class SolrPurchaseVo implements Serializable {
	private static final long serialVersionUID = 6107528073820114278L;
	
	
	public SolrPurchaseVo() {
	}
	
	/**
	 * 采购ID
	 */
	@Field
	private String purchaseId;
	
	/**
	 * 采购批次号
	 */
	@Field
	private String purchaseCode;
	
	/**
	 * 采购标题
	 */
	@Field
	private String purchaseTitle;
	
	/**
	 * 品种名称
	 */
	@Field
	private String breedName;
	
	/**
	 * 规格等级
	 */
	@Field
	private String standardLevel;
	
	/**
	 * 产地
	 */
	@Field
	private String origin;
	
	/**
	 * 采购数量
	 */
	@Field
	private Double quantity;
	
	/**
	 * 单位
	 */
	@Field
	private String wunitName;
	
	/**
	 * 质量要求
	 */
	@Field
	private String qualityDescription;
	
	/**
	 * 业务员姓名
	 */
	@Field
	private String salesman;
	
	/**
	 * 业务员手机号
	 */
	@Field
	private String salesmanMobile;
	
	/**
	 * 发布时间
	 */
	@Field
	private Date publishTime;
	
	/**
	 * 审核时间
	 */
	@Field
	private Date auditTime;
	
	/**
	 * 有效期
	 */
	@Field
	private String validPeriod;
	
	/**
	 * 过期时间
	 */
	@Field
	private Date expireTime;
	
	/**
	 * 审核状态
	 */
	@Field
	private Integer status;


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
	public String getPurchaseTitle() {
		return purchaseTitle;
	}
	public void setPurchaseTitle(String purchaseTitle) {
		this.purchaseTitle = purchaseTitle;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String breedName) {
		this.breedName = breedName;
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getWunitName() {
		return wunitName;
	}
	public void setWunitName(String wunitName) {
		this.wunitName = wunitName;
	}
	public String getQualityDescription() {
		return qualityDescription;
	}
	public void setQualityDescription(String qualityDescription) {
		this.qualityDescription = qualityDescription;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getSalesmanMobile() {
		return salesmanMobile;
	}
	public void setSalesmanMobile(String salesmanMobile) {
		this.salesmanMobile = salesmanMobile;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getRemainingDays(){
		int day1 = TimeUtil.daysOfTwo(publishTime, new Date());
		int day2 = 0;
		if(StringUtils.isNotEmpty(validPeriod)){
			day2 = Integer.valueOf(validPeriod);
		}
		return day2 - day1;
	}
    
}
