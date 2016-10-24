package com.ms.dao.vo;

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
}