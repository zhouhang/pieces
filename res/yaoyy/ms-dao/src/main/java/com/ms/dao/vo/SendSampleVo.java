package com.ms.dao.vo;

import com.ms.dao.model.Commodity;
import com.ms.dao.model.SendSample;
import com.ms.dao.enums.SampleEnum;

import java.util.List;

public class SendSampleVo extends SendSample{

    //联系人姓名
    private String nickname;

    //联系电话
    private String phone;

    //地区
    private String area;

    //状态描述
    private String statusText;

    //寄样商品列表
    private List<Commodity> commodityList;

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

    private String intentionText;


    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public String getStatusText() {
        return SampleEnum.findByValue(getStatus());
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}