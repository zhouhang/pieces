package com.jointown.zy.common.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 会员查询DTO
 * @author ldp
 *
 */
public class MemberSearchDto {

	private String regStartDate;
	
	private String regEndDate;
	
	private String userName;
	
	private String mobileNo;
	
	private String realName;
	
	//added by biran 20150804  业务员
	private String salesMan;
	

	public String getRegStartDate() {
		return regStartDate;
	}

	public void setRegStartDate(String regStartDate) {
		this.regStartDate = regStartDate;
	}

	public String getRegEndDate() {
		return regEndDate;
	}

	public void setRegEndDate(String regEndDate) {
		this.regEndDate = regEndDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	//业务员
    public String getSalesMan() {
		return salesMan;
	}
    
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
    
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}
