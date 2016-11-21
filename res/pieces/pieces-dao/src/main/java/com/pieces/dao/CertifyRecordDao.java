package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.vo.CertifyRecordVo;

import java.util.List;
@AutoMapper
public interface CertifyRecordDao extends ICommonDao<CertifyRecord>{

    public List<CertifyRecordVo> findByParams(CertifyRecordVo certifyRecordVo);

    public CertifyRecordVo getLatest(Integer userId);

}
