package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;

import java.util.List;
@AutoMapper
public interface PayRecordDao extends ICommonDao<PayRecord>{

    public List<PayRecordVo> findByParams(PayRecordVo payRecordVo);

    public List<PayRecordVo> findByNormalRecord();

}
