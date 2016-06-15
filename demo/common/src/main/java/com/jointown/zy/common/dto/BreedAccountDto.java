package com.jointown.zy.common.dto;

public class BreedAccountDto extends BaseDto  {
	
	private static final long serialVersionUID = 1L;
	
	private String startTime;
	private String endTime;
	private String breedInfo;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBreedInfo() {
		return breedInfo;
	}
	public void setBreedInfo(String breedInfo) {
		this.breedInfo = breedInfo;
	}
	

}
