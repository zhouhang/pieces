package com.jointown.zy.common.dto;

import com.jointown.zy.common.model.BusiQualityinfo;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 质检管理Dto
 * @author wangjunhu
 * 2014-12-26
 */
public class BusiQualityInfoDto {

	private String wlid;
	private String acceptchecktime;
	private String reportdate;
	private String mainparacter;
	private String grade;
	private String detailed;
	private String numberofjc;
	private String water;
	private String ash;
	private String sulfurdioxidein;
	private String contentcheck;
	private BusiQualityinfo busiqualityinfo;
	
	public BusiQualityInfoDto() {
		super();
		busiqualityinfo = new BusiQualityinfo();
	}

	public String getWlid() {
		return wlid;
	}

	public void setWlid(String wlid) {
		this.wlid = wlid;
		busiqualityinfo.setWlid(wlid);
	}

	public String getAcceptchecktime() {
		return acceptchecktime;
	}

	public void setAcceptchecktime(String acceptchecktime) {
		this.acceptchecktime = acceptchecktime;
		if(!acceptchecktime.isEmpty()){
			busiqualityinfo.setAcceptchecktime(TimeUtil.parseYMDHM(acceptchecktime));
		}
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
		if(!reportdate.isEmpty()){
			busiqualityinfo.setReportdate(TimeUtil.parseYMDHM(reportdate));
		}
	}

	public String getMainparacter() {
		return mainparacter;
	}

	public void setMainparacter(String mainparacter) {
		this.mainparacter = mainparacter;
		busiqualityinfo.setMainparacter(mainparacter);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
		busiqualityinfo.setGrade(grade);
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
		busiqualityinfo.setDetailed(detailed);
	}

	public String getNumberofjc() {
		return numberofjc;
	}

	public void setNumberofjc(String numberofjc) {
		this.numberofjc = numberofjc;
		busiqualityinfo.setNumberofjc(numberofjc);
	}

	public String getWater() {
		return water;
	}

	public void setWater(String water) {
		this.water = water;
		busiqualityinfo.setWater(water);
	}

	public String getAsh() {
		return ash;
	}

	public void setAsh(String ash) {
		this.ash = ash;
		busiqualityinfo.setAsh(ash);
	}

	public String getSulfurdioxidein() {
		return sulfurdioxidein;
	}

	public void setSulfurdioxidein(String sulfurdioxidein) {
		this.sulfurdioxidein = sulfurdioxidein;
		busiqualityinfo.setSulfurdioxidein(sulfurdioxidein);
	}

	public String getContentcheck() {
		return contentcheck;
	}

	public void setContentcheck(String contentcheck) {
		this.contentcheck = contentcheck;
		busiqualityinfo.setContentcheck(contentcheck);
	}

	public BusiQualityinfo getBusiqualityinfo() {
		return busiqualityinfo;
	}

	public void setBusiqualityinfo(BusiQualityinfo busiqualityinfo) {
		this.busiqualityinfo = busiqualityinfo;
	}
}
