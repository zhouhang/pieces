package com.jointown.zy.common.dto;

import java.util.Date;

/**
 * 
 * @ClassName:InfoWarehousDto
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:01:42
 * @version V1.0
 * @Description:入仓信息管理
 */
public class InfoWarehousDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	/** 发布人 **/
	private String applyName;
	/** 状态 **/
	private String status;
	/** 手机号 **/
	private String applyMobile;
	/** 信息来源 **/
	private String applyResource;
	/** 品种id **/
	private String breedId;
	/** 品种名称 **/
	private String breedName;
	/** 创建时间 **/
	private String createTimeStart;
	/** 创建时间 **/
	private String createTimeEnd;

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
	}

	public String getApplyResource() {
		return applyResource;
	}

	public void setApplyResource(String applyResource) {
		this.applyResource = applyResource;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
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