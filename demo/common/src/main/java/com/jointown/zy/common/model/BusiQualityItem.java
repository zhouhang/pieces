package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiQualityItem implements Serializable {
    private Long qualityItemId;

    private String wlid;

    private Long qualityInfoId;

    private String qualityItemType;

    private String qualityItemName;

    private String qualityItemStandard;

    private String qualityItemResult;

    private Date createTime;

    private Date updateTime;

    public Long getQualityItemId() {
        return qualityItemId;
    }

    public void setQualityItemId(Long qualityItemId) {
        this.qualityItemId = qualityItemId;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public Long getQualityInfoId() {
        return qualityInfoId;
    }

    public void setQualityInfoId(Long qualityInfoId) {
        this.qualityInfoId = qualityInfoId;
    }

    public String getQualityItemType() {
        return qualityItemType;
    }

    public void setQualityItemType(String qualityItemType) {
        this.qualityItemType = qualityItemType;
    }

    public String getQualityItemName() {
        return qualityItemName;
    }

    public void setQualityItemName(String qualityItemName) {
        this.qualityItemName = qualityItemName;
    }

    public String getQualityItemStandard() {
        return qualityItemStandard;
    }

    public void setQualityItemStandard(String qualityItemStandard) {
        this.qualityItemStandard = qualityItemStandard;
    }

    public String getQualityItemResult() {
        return qualityItemResult;
    }

    public void setQualityItemResult(String qualityItemResult) {
        this.qualityItemResult = qualityItemResult;
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