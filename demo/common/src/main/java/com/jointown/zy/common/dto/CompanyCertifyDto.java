package com.jointown.zy.common.dto;

/**
 * 
 * @author zhouji
 *
 * 2015年1月6日
 */
public class CompanyCertifyDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	private String certifyId;
	private Long userId;
	private String companyName;
	private String presidentName;
	private String licenceCode;
	private String licenceStartdate;
	private String licenceEnddate;
	private String orgCode;
	private Integer property;
	private Integer registeredCapital;
	private String licenceDate;
	private String picName;
	private String picPath;
	private Integer picType;
	private String picNameSmall;
	private String picSmallPath;
	private Integer picSmallType;
	private String picNameBig;
	private String picBigPath;
	private Integer picBigType;
	private Integer status;
	private String checkRemark;//审核备注
	
	private String picName1;
	private String picPath1;
	private Integer picType1;
	private String picNameSmall1;
	private String picSmallPath1;
	private Integer picSmallType1;
	private String picNameBig1;
	private String picBigPath1;
	private Integer picBigType1;
	
	public CompanyCertifyDto() {
		super();
	}
	/**
	 * @param certifyId
	 * @param userId
	 * @param companyName
	 * @param presidentName
	 * @param licenceCode
	 * @param licenceStartdate
	 * @param licenceEnddate
	 * @param orgCode
	 * @param property
	 * @param registeredCapital
	 * @param licenceDate
	 * @param picName
	 * @param picPath
	 * @param picType
	 * @param picNameSmall
	 * @param picSmallPath
	 * @param picSmallType
	 * @param picNameBig
	 * @param picBigPath
	 * @param picBigType
	 * @param status
	 * @param checkRemark
	 * @param picName1
	 * @param picPath1
	 * @param picType1
	 * @param picNameSmall1
	 * @param picSmallPath1
	 * @param picSmallType1
	 * @param picNameBig1
	 * @param picBigPath1
	 * @param picBigType1
	 */
	public CompanyCertifyDto(String certifyId, Long userId, String companyName,
			String presidentName, String licenceCode, String licenceStartdate,
			String licenceEnddate, String orgCode, Integer property,
			Integer registeredCapital, String licenceDate, String picName,
			String picPath, Integer picType, String picNameSmall,
			String picSmallPath, Integer picSmallType, String picNameBig,
			String picBigPath, Integer picBigType, Integer status,
			String checkRemark, String picName1, String picPath1,
			Integer picType1, String picNameSmall1, String picSmallPath1,
			Integer picSmallType1, String picNameBig1, String picBigPath1,
			Integer picBigType1) {
		super();
		this.certifyId = certifyId;
		this.userId = userId;
		this.companyName = companyName;
		this.presidentName = presidentName;
		this.licenceCode = licenceCode;
		this.licenceStartdate = licenceStartdate;
		this.licenceEnddate = licenceEnddate;
		this.orgCode = orgCode;
		this.property = property;
		this.registeredCapital = registeredCapital;
		this.licenceDate = licenceDate;
		this.picName = picName;
		this.picPath = picPath;
		this.picType = picType;
		this.picNameSmall = picNameSmall;
		this.picSmallPath = picSmallPath;
		this.picSmallType = picSmallType;
		this.picNameBig = picNameBig;
		this.picBigPath = picBigPath;
		this.picBigType = picBigType;
		this.status = status;
		this.checkRemark = checkRemark;
		this.picName1 = picName1;
		this.picPath1 = picPath1;
		this.picType1 = picType1;
		this.picNameSmall1 = picNameSmall1;
		this.picSmallPath1 = picSmallPath1;
		this.picSmallType1 = picSmallType1;
		this.picNameBig1 = picNameBig1;
		this.picBigPath1 = picBigPath1;
		this.picBigType1 = picBigType1;
	}
	
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getProperty() {
		return property;
	}
	public void setProperty(Integer property) {
		this.property = property;
	}
	public Integer getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(Integer registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getLicenceDate() {
		return licenceDate;
	}
	public void setLicenceDate(String licenceDate) {
		this.licenceDate = licenceDate;
	}
	public String getCertifyId() {
		return certifyId;
	}
	public void setCertifyId(String certifyId) {
		this.certifyId = certifyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPresidentName() {
		return presidentName;
	}
	public void setPresidentName(String presidentName) {
		this.presidentName = presidentName;
	}
	public String getLicenceCode() {
		return licenceCode;
	}
	public void setLicenceCode(String licenceCode) {
		this.licenceCode = licenceCode;
	}
	public String getLicenceStartdate() {
		return licenceStartdate;
	}
	public void setLicenceStartdate(String licenceStartdate) {
		this.licenceStartdate = licenceStartdate;
	}
	public String getLicenceEnddate() {
		return licenceEnddate;
	}
	public void setLicenceEnddate(String licenceEnddate) {
		this.licenceEnddate = licenceEnddate;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public String getPicNameSmall() {
		return picNameSmall;
	}
	public void setPicNameSmall(String picNameSmall) {
		this.picNameSmall = picNameSmall;
	}
	public String getPicSmallPath() {
		return picSmallPath;
	}
	public void setPicSmallPath(String picSmallPath) {
		this.picSmallPath = picSmallPath;
	}
	public Integer getPicSmallType() {
		return picSmallType;
	}
	public void setPicSmallType(Integer picSmallType) {
		this.picSmallType = picSmallType;
	}
	public String getPicNameBig() {
		return picNameBig;
	}
	public void setPicNameBig(String picNameBig) {
		this.picNameBig = picNameBig;
	}
	public String getPicBigPath() {
		return picBigPath;
	}
	public void setPicBigPath(String picBigPath) {
		this.picBigPath = picBigPath;
	}
	public Integer getPicBigType() {
		return picBigType;
	}
	public void setPicBigType(Integer picBigType) {
		this.picBigType = picBigType;
	}
	public String getPicName1() {
		return picName1;
	}
	public void setPicName1(String picName1) {
		this.picName1 = picName1;
	}
	public String getPicPath1() {
		return picPath1;
	}
	public void setPicPath1(String picPath1) {
		this.picPath1 = picPath1;
	}
	public Integer getPicType1() {
		return picType1;
	}
	public void setPicType1(Integer picType1) {
		this.picType1 = picType1;
	}
	public String getPicNameSmall1() {
		return picNameSmall1;
	}
	public void setPicNameSmall1(String picNameSmall1) {
		this.picNameSmall1 = picNameSmall1;
	}
	public String getPicSmallPath1() {
		return picSmallPath1;
	}
	public void setPicSmallPath1(String picSmallPath1) {
		this.picSmallPath1 = picSmallPath1;
	}
	public Integer getPicSmallType1() {
		return picSmallType1;
	}
	public void setPicSmallType1(Integer picSmallType1) {
		this.picSmallType1 = picSmallType1;
	}
	public String getPicNameBig1() {
		return picNameBig1;
	}
	public void setPicNameBig1(String picNameBig1) {
		this.picNameBig1 = picNameBig1;
	}
	public String getPicBigPath1() {
		return picBigPath1;
	}
	public void setPicBigPath1(String picBigPath1) {
		this.picBigPath1 = picBigPath1;
	}
	public Integer getPicBigType1() {
		return picBigType1;
	}
	public void setPicBigType1(Integer picBigType1) {
		this.picBigType1 = picBigType1;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
