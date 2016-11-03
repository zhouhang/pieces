package com.ms.dao.vo;

import com.ms.dao.model.Commodity;
import com.ms.dao.model.SendSample;
import com.ms.dao.enums.SampleEnum;

import java.util.List;

public class SendSampleVo extends SendSample{



    //状态描述
    private String statusText;

    //寄样商品列表
    private List<CommodityVo> commodityList;

    private String intentionText;

    public String getIntentionText() {
        intentionText="";
        if (commodityList!=null) {
            for (Commodity commodity : commodityList) {
                intentionText = intentionText + commodity.getName() + ' ' + commodity.getOrigin() + ' ' + commodity.getSpec() + "<br>";
            }
        }
        return intentionText;
    }

    public void setIntentionText(String intentionText) {
        this.intentionText = intentionText;
    }




    public List<CommodityVo> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityVo> commodityList) {
        this.commodityList = commodityList;
    }

    public String getStatusText() {
        return SampleEnum.findByValue(getStatus());
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


}