package com.ms.dao.vo;

import com.ms.dao.enums.PickEnum;
import com.ms.dao.model.Pick;

public class PickVo extends Pick{

    private String nickname;

    private String phone;

    private String statusText;

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