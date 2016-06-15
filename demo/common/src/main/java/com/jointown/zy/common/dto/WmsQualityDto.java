package com.jointown.zy.common.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
/**
 * wms 仓单接口 ，质检信息Dto
 * @author ldp
 * date 2015年3月23日
 * version 1.0
 */
public class WmsQualityDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3546625771388706338L;
	
	/** 质检日期*/
	private String qualityCheckDate;
	/** 报告日期*/
	private String qualityReportDate;
	/** 质检人*/
	private String qualityPerson;
	/** 质检图片*/
	private String qualityPic;
	/** 规格等级*/
	private String grade;
	/** 检品数量*/
	private String checkNum;
	/** 等级评定*/
	private String levelEva;
	/** 质检项*/
//	private Map<String,String> qualityItems;
	/** 质检项信息*/
	private List<WmsQualityItemDto> qualityItemsInfo;
	/** add by fanyuna 2015.07.01 =======begin======*/
	/** QA质检描述*/
	private String QADesc;
	/** add by fanyuna 2015.07.01 =======end======*/
	
	public WmsQualityDto() {
		super();
	}
	/*public WmsQualityDto(String qualityCheckDate, String qualityReportDate,
			String qualityPerson, String qualityPic, String grade,
			String checkNum, Map<String, String> qualityItems) {
		super();
		this.qualityCheckDate = qualityCheckDate;
		this.qualityReportDate = qualityReportDate;
		this.qualityPerson = qualityPerson;
		this.qualityPic = qualityPic;
		this.grade = grade;
		this.checkNum = checkNum;
		this.qualityItems = qualityItems;
	}*/

	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getQualityCheckDate() {
		return qualityCheckDate;
	}
	public void setQualityCheckDate(String qualityCheckDate) {
		this.qualityCheckDate = qualityCheckDate;
	}
	public String getQualityReportDate() {
		return qualityReportDate;
	}
	public void setQualityReportDate(String qualityReportDate) {
		this.qualityReportDate = qualityReportDate;
	}
	public String getQualityPerson() {
		return qualityPerson;
	}
	public void setQualityPerson(String qualityPerson) {
		this.qualityPerson = qualityPerson;
	}
	public String getQualityPic() {
		return qualityPic;
	}
	public void setQualityPic(String qualityPic) {
		this.qualityPic = qualityPic;
	}
	/*public Map<String,String> getQualityItems() {
		return qualityItems;
	}
	public void setQualityItems(Map<String,String> qualityItems) {
		this.qualityItems = qualityItems;
	}*/
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getLevelEva() {
		return levelEva;
	}

	public void setLevelEva(String levelEva) {
		this.levelEva = levelEva;
	}

	public List<WmsQualityItemDto> getQualityItemsInfo() {
		return qualityItemsInfo;
	}

	public void setQualityItemsInfo(List<WmsQualityItemDto> qualityItemsInfo) {
		this.qualityItemsInfo = qualityItemsInfo;
	}

	public String getQADesc() {
		return QADesc;
	}

	public void setQADesc(String qADesc) {
		QADesc = qADesc;
	}
	

}
