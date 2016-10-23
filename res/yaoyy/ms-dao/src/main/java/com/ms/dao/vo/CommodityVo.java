package com.ms.dao.vo;

import com.ms.dao.model.Commodity;
import com.ms.dao.model.Gradient;

import java.util.List;

public class CommodityVo extends Commodity{

    // 品种名
    private String categoryName;

    // 量大价优价格梯度
    private List<Gradient> gradient;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Gradient> getGradient() {
        return gradient;
    }

    public void setGradient(List<Gradient> gradient) {
        this.gradient = gradient;
    }
}