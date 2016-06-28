package com.jointown.zy.common.dto;

/**
 * 微信求购信息查询Dto
 *
 * @author aizhengdong
 *
 * @data 2015年3月27日
 */
public class WxDemandSearchDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	/** 发布人姓名 */
	private String userName;
	
	/**  
	 * 联系电话 
	 * @author aizhengdong 2015年6月15日
	 */  
	private String userMobile;
	
    /** 品种名称 */
    private String breedName;
    
    /**  
	 * 状态
	 * @author aizhengdong 2015年6月15日
	 */  
	private Short status;
	
	/**  
	 * 信息来源
	 * @author aizhengdong 2015年6月15日
	 */  
	private String applyResource;
	
    /** 规格名称 */
    private String breedStandardLevel;
	
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

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getApplyResource() {
		return applyResource;
	}

	public void setApplyResource(String applyResource) {
		this.applyResource = applyResource;
	}
	
}
