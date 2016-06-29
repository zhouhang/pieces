package com.jointown.zy.common.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @ClassName: CustomerAccountDto
 * @Description: 客户账务统计Dto
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
public class CustomerAccountDto extends BaseDto implements Serializable {
	
	/** TODO */
	private static final long serialVersionUID = -6723153018775808188L;
	/**开始时间*/
	private String startDate;
	/**结束时间*/
	private String endDate;
	/**大区ID*/
	private String orgId;
	/**业务人员*/
	private String salesMan;
	/**会员名*/
	private String userName;
	/**真实姓名*/
	private String realName;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSalesMan() {
		return salesMan;
	}
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
	

}
