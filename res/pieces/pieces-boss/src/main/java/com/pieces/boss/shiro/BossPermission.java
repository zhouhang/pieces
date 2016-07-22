package com.pieces.boss.shiro;

import org.apache.shiro.authz.Permission;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by wangbin on 2016/7/13.
 */
public class BossPermission implements Permission,Serializable {

    private static final long serialVersionUID = 1L;

    private String url;

    public BossPermission(String url){
        this.url = url;
    }

    @Override
    public boolean implies(Permission permission) {
        BossPermission bp =   (BossPermission)permission;
        return true;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
