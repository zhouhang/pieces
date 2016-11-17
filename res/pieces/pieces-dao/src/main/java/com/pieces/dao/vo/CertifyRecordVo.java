package com.pieces.dao.vo;

import com.pieces.dao.enums.CertifyRecordStatusEnum;
import com.pieces.dao.model.CertifyRecord;

import java.util.Date;

public class CertifyRecordVo extends CertifyRecord{

    public Date startTime;

    public Date endTime;

    public Date startFollowTime;

    public Date endFollowTime;

    private String statusText;

    public String getStatusText() {
        return CertifyRecordStatusEnum.findByValue(getStatus());
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Date getStartFollowTime() {
        return startFollowTime;
    }

    public void setStartFollowTime(Date startFollowTime) {
        this.startFollowTime = startFollowTime;
    }

    public Date getEndFollowTime() {
        return endFollowTime;
    }

    public void setEndFollowTime(Date endFollowTime) {
        this.endFollowTime = endFollowTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}