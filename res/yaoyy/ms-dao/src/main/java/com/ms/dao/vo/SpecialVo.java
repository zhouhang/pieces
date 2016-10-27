package com.ms.dao.vo;

import com.ms.dao.model.Special;

public class SpecialVo extends Special{

    //商品id号用数目隔开
    private String commodities;

    public String getCommodities() {
        return commodities;
    }

    public void setCommodities(String commodities) {
        this.commodities = commodities;
    }
}