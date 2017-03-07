package com.pieces.dao.vo;

import java.util.List;

/**
 * Created by xiao on 2017/3/7.
 * 提交认证传递的参数
 */
public class CertifyDataVo {

    private List<UserQualificationVo> userQualificationVos;

    private Integer type;

    public List<UserQualificationVo> getUserQualificationVos() {
        return userQualificationVos;
    }

    public void setUserQualificationVos(List<UserQualificationVo> userQualificationVos) {
        this.userQualificationVos = userQualificationVos;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
