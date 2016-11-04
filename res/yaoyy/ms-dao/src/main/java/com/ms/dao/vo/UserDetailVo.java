package com.ms.dao.vo;

import com.ms.dao.model.UserDetail;

public class UserDetailVo extends UserDetail{

    private Integer userType;//标记是否注册

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}