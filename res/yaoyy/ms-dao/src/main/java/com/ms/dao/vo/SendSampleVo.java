package com.ms.dao.vo;

import com.ms.dao.model.SendSample;

public class SendSampleVo extends SendSample{

    //联系人姓名
    private String nickname;

    //联系电话
    private String phone;

    //地区
    private String area;

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