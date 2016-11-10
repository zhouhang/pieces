package com.ms.dao.vo;

import com.ms.dao.enums.BizSampleEnum;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.SendSample;
import com.ms.dao.enums.SampleEnum;
import org.apache.commons.lang.StringUtils;


import java.util.ArrayList;
import java.util.List;

public class SendSampleVo extends SendSample{



    //状态描述
    private String statusText;

    //寄样商品列表
    private List<HistoryCommodityVo> commodityList;

    private String intentionText;


    private String intentCommodityIds;

    private Integer  getSample;//标记是否确认收货

    private String bizStatusText;

    public String getBizStatusText() {
        return BizSampleEnum.findByValue(getStatus());
    }

    public void setBizStatusText(String bizStatusText) {
        this.bizStatusText = bizStatusText;
    }

    public Integer getGetSample() {
        return getSample;
    }

    public void setGetSample(Integer getSample) {
        this.getSample = getSample;
    }

    public String getIntentCommodityIds() {
        List<Integer> ids = new ArrayList<>();
        if (commodityList != null) {
            commodityList.forEach(c -> {
                ids.add(c.getCommodityId());
            });
            return StringUtils.join(ids,',');
        }

        return null;
    }

    public void setIntentCommodityIds(String intentCommodityIds) {
        this.intentCommodityIds = intentCommodityIds;
    }

    public String getIntentionText() {
        intentionText="";
        if (commodityList!=null) {
            for (HistoryCommodityVo commodity : commodityList) {
                intentionText = intentionText + commodity.getName() + ' ' + commodity.getOrigin() + ' ' + commodity.getSpec() + "<br>";
            }
        }
        return intentionText;
    }

    public void setIntentionText(String intentionText) {
        this.intentionText = intentionText;
    }

    public List<HistoryCommodityVo> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<HistoryCommodityVo> commodityList) {
        this.commodityList = commodityList;
    }

    public String getStatusText() {
        return SampleEnum.findByValue(getStatus());
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


}