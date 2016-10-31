package com.ms.dao.vo;

import com.ms.dao.model.Category;

public class CategoryVo extends Category{

    private String parentName;

    private Integer defaultCommodityId;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getDefaultCommodityId() {
        return defaultCommodityId;
    }

    public void setDefaultCommodityId(Integer defaultCommodityId) {
        this.defaultCommodityId = defaultCommodityId;
    }
}