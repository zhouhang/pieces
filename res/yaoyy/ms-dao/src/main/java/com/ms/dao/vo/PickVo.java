package com.ms.dao.vo;

import com.ms.dao.enums.BizPickEnum;
import com.ms.dao.enums.PickEnum;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Pick;

import java.util.List;


public class PickVo extends Pick{



    private String statusText;

    private String bizStatusText;

    public String getBizStatusText() {
        return BizPickEnum.findByValue(getStatus());
    }

    public void setBizStatusText(String bizStatusText) {
        this.bizStatusText = bizStatusText;
    }

    private List<PickCommodityVo> pickCommodityVoList;

    private float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<PickCommodityVo> getPickCommodityVoList() {
        return pickCommodityVoList;
    }

    public void setPickCommodityVoList(List<PickCommodityVo> pickCommodityVoList) {
        this.pickCommodityVoList = pickCommodityVoList;
    }


    public String getStatusText() {
        return PickEnum.findByValue(getStatus());
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


}