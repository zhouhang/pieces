package com.pieces.tools.log.api;

/**
 * Created by kevin1 on 7/12/16.
 * 当前登入用户信息
 */
public class LogUser {

    private Long userId;

    private String userName;

    private String userCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
