package com.pieces.dao.vo;

import java.util.List;

/**
 * Created by xiao on 2016/11/21.
 */
public class CertifyParamVo {
    //修改认证信息传递的参数

    private List<UserQualificationVo> userQualificationVos;

    private UserCertificationVo userCertificationVo;


    private CertifyRecordVo certifyRecordVo;

    public CertifyRecordVo getCertifyRecordVo() {
        return certifyRecordVo;
    }

    public void setCertifyRecordVo(CertifyRecordVo certifyRecordVo) {
        this.certifyRecordVo = certifyRecordVo;
    }

    public List<UserQualificationVo> getUserQualificationVos() {
        return userQualificationVos;
    }

    public void setUserQualificationVos(List<UserQualificationVo> userQualificationVos) {
        this.userQualificationVos = userQualificationVos;
    }

    public UserCertificationVo getUserCertificationVo() {
        return userCertificationVo;
    }

    public void setUserCertificationVo(UserCertificationVo userCertificationVo) {
        this.userCertificationVo = userCertificationVo;
    }
}
