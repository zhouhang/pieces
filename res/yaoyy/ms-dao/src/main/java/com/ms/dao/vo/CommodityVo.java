package com.ms.dao.vo;

import com.ms.dao.model.Commodity;

public class CommodityVo extends Commodity{

    // 品种名
    private String categoryName = "";

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}