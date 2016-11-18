package com.pieces.dao.vo;

import com.pieces.dao.enums.QualificationTypeEnum;
import com.pieces.dao.model.UserQualification;

public class UserQualificationVo extends UserQualification{


    private String typeText;

    public String getTypeText() {
        return QualificationTypeEnum.findByValue(getType());
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }
}