package com.ms.dao.vo;

import com.ms.dao.model.Category;

public class CategoryVo extends Category{

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}