package com.ms.dao.vo;

import com.ms.dao.model.Member;

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
