package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiQuality implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7536835109653436334L;

	private Long qualityId;

    private String wlid;

    private String qualityItemCode;

    private String qualityItemName;

    private String qualityItemResult;

    private Date qualityTime;

    private String qualityPerson;

    private Date reportDate;

    private Date createTime;

    private Date updateTime;
    
    private String grade;
    
    private String checkNum;

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

	public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public String getQualityItemCode() {
        return qualityItemCode;
    }

    public void setQualityItemCode(String qualityItemCode) {
        this.qualityItemCode = qualityItemCode;
    }

    public String getQualityItemName() {
        return qualityItemName;
    }

    public void setQualityItemName(String qualityItemName) {
        this.qualityItemName = qualityItemName;
    }

    public String getQualityItemResult() {
        return qualityItemResult;
    }

    public void setQualityItemResult(String qualityItemResult) {
        this.qualityItemResult = qualityItemResult;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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
}