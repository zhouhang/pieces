package com.ms.dao.vo;

import com.ms.dao.enums.PickTrackingTypeEnum;
import com.ms.dao.model.PickTracking;

public class PickTrackingVo extends PickTracking{

    private String recordTypeText;

    public String getRecordTypeText() {
        return PickTrackingTypeEnum.findByValue(getRecordType());
    }

    public void setRecordTypeText(String recordTypeText) {
        this.recordTypeText = recordTypeText;
    }
}