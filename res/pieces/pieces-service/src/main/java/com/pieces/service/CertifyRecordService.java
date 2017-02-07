package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;

import java.util.List;

public interface CertifyRecordService extends ICommonService<CertifyRecord>{

    public PageInfo<CertifyRecordVo> findByParams(CertifyRecordVo certifyRecordVo,Integer pageNum,Integer pageSize);

    public void saveRecord(CertifyRecord certifyRecord,UserCertificationVo certificationVo,List<UserQualificationVo> userQualificationVos);

    public void passCertify(CertifyRecordVo certifyRecordVo);

    public CertifyRecordVo getLatest(Integer userId);

    public void saveCertify(UserCertificationVo certificationVo,List<UserQualificationVo> userQualificationVos);

    public Integer getNotHandleCount();

    public List<Integer> getNotHandleIds();
}
