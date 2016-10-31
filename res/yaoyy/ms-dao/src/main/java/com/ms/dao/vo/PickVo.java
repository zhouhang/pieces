package com.ms.dao.vo;

import com.ms.dao.enums.PickEnum;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Pick;

import java.util.List;


public class PickVo extends Pick{

    private String nickname;

    private String phone;

    private String statusText;

    private String area;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatusText() {
        return PickEnum.findByValue(getStatus());
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
}