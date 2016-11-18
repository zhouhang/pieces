package com.pieces.dao.vo;

import com.pieces.dao.enums.CertifyRecordStatusEnum;
import com.pieces.dao.enums.CertifyTypeEnum;
import com.pieces.dao.model.UserCertification;

public class UserCertificationVo extends UserCertification{

    private String typeText;

    public String getTypeText() {
        return CertifyTypeEnum.findByValue(getType());
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }
}