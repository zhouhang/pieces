package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CertifyDataVo;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;

import java.util.List;

public interface CertifyRecordService extends ICommonService<CertifyRecord>{

    PageInfo<CertifyRecordVo> findByParams(CertifyRecordVo certifyRecordVo,Integer pageNum,Integer pageSize);

    void saveRecord(CertifyRecord certifyRecord,UserCertificationVo certificationVo,List<UserQualificationVo> userQualificationVos);

    void passCertify(CertifyRecordVo certifyRecordVo);

    CertifyRecordVo getLatest(Integer userId);

    void saveCertify(UserCertificationVo certificationVo,List<UserQualificationVo> userQualificationVos);

    Integer getNotHandleCount();

    List<Integer> getNotHandleIds();

    /**
     * 微信端资质认证保存接口
     * @param certifyDataVo
     * @param user
     */
    void saveCertify(CertifyDataVo certifyDataVo, User user);
}
