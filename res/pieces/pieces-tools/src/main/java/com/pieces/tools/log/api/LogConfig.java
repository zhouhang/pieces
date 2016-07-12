package com.pieces.tools.log.api;

/**
 * Created by kevin1 on 7/12/16.
 * 用户的配置信息
 * 通过Spring来注入相关信息
 */
public class LogConfig {

    public static GetLogUser user;

    public GetLogUser getUser() {
        return user;
    }

    public void setUser(GetLogUser user) {
        LogConfig.user = user;
    }


    public void init() {
        // TODO: 初始化整个项目相关的配置并注入对应的对象.

    }
}
