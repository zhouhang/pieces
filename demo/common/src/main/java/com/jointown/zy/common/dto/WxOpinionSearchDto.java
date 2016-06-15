package com.jointown.zy.common.dto;

/**
 * 意见搜索
 *
 * @author aizhengdong
 *
 * @data 2015年8月24日
 */
public class WxOpinionSearchDto extends BaseDto {

	/**
	 * 联系人
	 * 
	 * @author aizhengdong 2015年8月24日
	 */
	private String userName;

	/**
	 * 开始时间
	 * 
	 * @author aizhengdong 2015年8月24日
	 */
	private String startDate;

	/**
	 * 结束时间
	 * 
	 * @author aizhengdong 2015年8月24日
	 */
	private String endDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
