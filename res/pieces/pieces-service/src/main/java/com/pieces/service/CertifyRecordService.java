package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.vo.CertifyRecordVo;

public interface CertifyRecordService extends ICommonService<CertifyRecord>{

    public PageInfo<CertifyRecordVo> findByParams(CertifyRecordVo certifyRecordVo,Integer pageNum,Integer pageSize);
}
