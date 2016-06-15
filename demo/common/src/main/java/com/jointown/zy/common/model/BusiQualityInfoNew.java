package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiQualityInfoNew implements Serializable {
    private Long qualityInfoId;

    private String wlid;

    private Date qualityTime;

    private String qualityPerson;

    private String grade;

    private String checkNumber;

    private Date reportDate;

    private String levelEva;

    private Date createTime;

    private Date updateTime;
    
    //QA质检描述
    private String qaDesc;

    public Long getQualityInfoId() {
        return qualityInfoId;
    }

    public void setQualityInfoId(Long qualityInfoId) {
        this.qualityInfoId = qualityInfoId;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public Date getQualityTime() {
        return qualityTime;
    }

    public void setQualityTime(Date qualityTime) {
        this.qualityTime = qualityTime;
    }

    public String getQualityPerson() {
        return qualityPerson;
    }

    public void setQualityPerson(String qualityPerson) {
        this.qualityPerson = qualityPerson;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getLevelEva() {
        return levelEva;
    }

    public void setLevelEva(String levelEva) {
        this.levelEva = levelEva;
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

	public String getQaDesc() {
		return qaDesc;
	}

	public void setQaDesc(String qaDesc) {
		this.qaDesc = qaDesc;
	}
    
    
}