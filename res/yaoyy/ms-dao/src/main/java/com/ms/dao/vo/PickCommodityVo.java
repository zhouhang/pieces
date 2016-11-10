package com.ms.dao.vo;

import com.ms.dao.model.PickCommodity;

public class PickCommodityVo extends PickCommodity{

    private String name;

    private String origin;

    private String spec;

    private String priceUnit;

    private Integer realCommodityId;//商品表中的Id

    public Integer getRealCommodityId() {
        return realCommodityId;
    }

    public void setRealCommodityId(Integer realCommodityId) {
        this.realCommodityId = realCommodityId;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}