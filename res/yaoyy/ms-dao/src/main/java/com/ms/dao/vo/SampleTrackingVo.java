package com.ms.dao.vo;

import com.ms.dao.enums.TrackingEnum;
import com.ms.dao.model.SampleTracking;

public class SampleTrackingVo extends SampleTracking{

    private String recordTypeText;

    public String getRecordTypeText() {
        return TrackingEnum.findByValue(getRecordType());

    }

    public void setRecordTypeText(String recordTypeText) {
        this.recordTypeText = recordTypeText;
    }
}