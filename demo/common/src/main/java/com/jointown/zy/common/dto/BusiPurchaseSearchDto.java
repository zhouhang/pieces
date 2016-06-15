package com.jointown.zy.common.dto;

/**
 * 
 * @ClassName: BusiPurchaseSearchDto
 * @Description: 用户中心 管理采购 列表页 搜索条件DTO
 * @Author: fanyuna
 * @Date: 2015年10月21日
 * @Version: 1.0
 */
public class BusiPurchaseSearchDto  extends BaseDto{
	

	//采购批次号
	private String purchaseCode;
	
	//采购品种
	private String breedName;
	
	//采购状态
	private String status;
	
	//发布时间 开始时间
	private String createTimeStart;
	
	//发布时间 结束时间
	private String createTimeEnd;
	

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	
	
}
