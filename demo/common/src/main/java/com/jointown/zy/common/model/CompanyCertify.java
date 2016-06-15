package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author zhouji
 *
 * 2015年1月5日
 */
public class CompanyCertify implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	//主键ID
	private Integer certifyId;
	//会员ID
	private Integer userId;
	//企业名称
	private String companyName;
	//组织机构代码
	private String orgCode;
	//法人名称
	private String presidentName;
	//营业执照编码
	private String licenceCode;
	//营业执照开始时间
	private Date licenceStartdate;
	//营业执照结束时间
	private Date licenceEnddate;
	//认证状态（0：待审核，1：审核通过 2：审核不通过）
	private Integer status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//提交时间
	private Date submitTime;
	//审批时间
	private Date approveTime;
	//审批人ID
	private Integer approverid;
	//公司性质(0:公司1:个体商户)
	private Integer property;
	//注册资金
	private Integer registeredCapital;
	//营业执照成立日期
	private Date licenceDate;
	//拒绝原因
	private Integer rejectmemo;
	public CompanyCertify() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyCertify(Integer certifyId, Integer userId,
			String companyName, String orgCode, String presidentName,
			String licenceCode, Date licenceStartdate, Date licenceEnddate,
			Integer status, Date createTime, Date updateTime, Date submitTime,
			Date approveTime, Integer approverid, Integer property,
			Integer registeredCapital, Date licenceDate, Integer rejectmemo) {
		super();
		this.certifyId = certifyId;
		this.userId = userId;
		this.companyName = companyName;
		this.orgCode = orgCode;
		this.presidentName = presidentName;
		this.licenceCode = licenceCode;
		this.licenceStartdate = licenceStartdate;
		this.licenceEnddate = licenceEnddate;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.submitTime = submitTime;
		this.approveTime = approveTime;
		this.approverid = approverid;
		this.property = property;
		this.registeredCapital = registeredCapital;
		this.licenceDate = licenceDate;
		this.rejectmemo = rejectmemo;
	}
	public Integer getCertifyId() {
		return certifyId;
	}
	public void setCertifyId(Integer certifyId) {
		this.certifyId = certifyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
	public Date getLicenceStartdate() {
		return licenceStartdate;
	}
	public void setLicenceStartdate(Date licenceStartdate) {
		this.licenceStartdate = licenceStartdate;
	}
	public Date getLicenceEnddate() {
		return licenceEnddate;
	}
	public void setLicenceEnddate(Date licenceEnddate) {
		this.licenceEnddate = licenceEnddate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
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
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public Integer getApproverid() {
		return approverid;
	}
	public void setApproverid(Integer approverid) {
		this.approverid = approverid;
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
	public Date getLicenceDate() {
		return licenceDate;
	}
	public void setLicenceDate(Date licenceDate) {
		this.licenceDate = licenceDate;
	}
	public Integer getRejectmemo() {
		return rejectmemo;
	}
	public void setRejectmemo(Integer rejectmemo) {
		this.rejectmemo = rejectmemo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
