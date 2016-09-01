package com.pieces.dao.vo;

import com.pieces.dao.model.Member;

import java.lang.reflect.Field;

/**
 * Created by wangbin on 2016/7/8.
 */
public class MemberVo extends Member{

    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
