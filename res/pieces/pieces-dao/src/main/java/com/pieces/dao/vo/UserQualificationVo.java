package com.pieces.dao.vo;

import com.pieces.dao.enums.QualificationTypeEnum;
import com.pieces.dao.model.QualificationPics;
import com.pieces.dao.model.UserQualification;

import java.util.List;

public class UserQualificationVo extends UserQualification{


    private String typeText;

    /**
     * 上传的照片
     */
    private List<QualificationPicsVo> pictures;

    public String getTypeText() {
        return QualificationTypeEnum.findByValue(getType());
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public List<QualificationPicsVo> getPictures() {
        return pictures;
    }

    public void setPictures(List<QualificationPicsVo> pictures) {
        this.pictures = pictures;
    }
}