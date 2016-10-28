package com.ms.dao.vo;

import com.ms.dao.model.Ad;

public class AdVo extends Ad{

    /**
     * 广告类型名称
     */
    private String adTypeName;

    public String getAdTypeName() {
        return adTypeName;
    }

    public void setAdTypeName(String adTypeName) {
        this.adTypeName = adTypeName;
    }
}