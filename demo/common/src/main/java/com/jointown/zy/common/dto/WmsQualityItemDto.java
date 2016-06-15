package com.jointown.zy.common.dto;

import java.io.Serializable;

public class WmsQualityItemDto implements Serializable {

	private static final long serialVersionUID = -1903957599920930788L;
	
	/** 质检类型*/
	private String qualityType;
	
	/** 质检项*/
	private String qualityItem;
	
	/** 质检标准*/
	private String qualityStandard;
	
	/** 质检结果*/
	private String qualityResult;

	public String getQualityType() {
		return qualityType;
	}

	public void setQualityType(String qualityType) {
		this.qualityType = qualityType;
	}

	public String getQualityItem() {
		return qualityItem;
	}

	public void setQualityItem(String qualityItem) {
		this.qualityItem = qualityItem;
	}

	public String getQualityStandard() {
		return qualityStandard;
	}

	public void setQualityStandard(String qualityStandard) {
		this.qualityStandard = qualityStandard;
	}

	public String getQualityResult() {
		return qualityResult;
	}

	public void setQualityResult(String qualityResult) {
		this.qualityResult = qualityResult;
	}
	
}
