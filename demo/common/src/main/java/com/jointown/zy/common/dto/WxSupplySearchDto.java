package com.jointown.zy.common.dto;

/**
 * 微信供应信息查询Dto
 *
 * @author aizhengdong
 *
 * @data 2015年3月17日
 */
public class WxSupplySearchDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	
	/**  
	 * 编号
	 * @author aizhengdong 2015年8月4日
	 */  
	private Long supplyId;

	/** 发布人姓名 */
	private String userName;
	
	/** 发布人手机号 */
    private String userMobile;
    
    /** 品种名称 */
    private String breedName;
	
    /** 规格名称 */
    private String breedStandardLevel;
    
    /** 产地名称 */
    private String breedPlace;
    
    /** 状态 */
    private Short status;
    
    /** 信息来源 */
    private Short sypplyResource;

	/** 开始时间 */
	private String startDate;
	
	/** 结束时间 */
	private String endDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
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

	public String getBreedPlace() {
		return breedPlace;
	}

	public void setBreedPlace(String breedPlace) {
		this.breedPlace = breedPlace;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getSypplyResource() {
		return sypplyResource;
	}

	public void setSypplyResource(Short sypplyResource) {
		this.sypplyResource = sypplyResource;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
	}
	
}
