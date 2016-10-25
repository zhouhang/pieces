package com.ms.dao.vo;

import com.ms.dao.enums.IdentityTypeEnum;
import com.ms.dao.enums.UserEnum;
import com.ms.dao.model.User;

public class UserVo extends User{

    /*
       是否登录，通过session可以获取
     */
    private boolean islogin;


    public boolean islogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }
    private String nickname;

    private String area;

    private String name;

    private String remark;

    private Integer identityType;

    private String typeName;

    private String identityTypeName;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getTypeName() {
        return UserEnum.get(super.getType());
    }

    public String getIdentityTypeName() {
        return IdentityTypeEnum.get(identityType);
    }
}