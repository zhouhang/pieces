package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;

import java.util.List;
@AutoMapper
public interface PayRecordDao extends ICommonDao<PayRecord>{

    public List<PayRecordVo> findByParams(PayRecordVo payRecordVo);

    public List<PayRecordVo> findByNormalRecord();

    public List<PayRecordVo> findByNormalRecord(PayRecordVo payRecordVo);

    public List<PayRecordVo> findByUserId(PayRecordVo payRecordVo);

    /**
     * 待处理支付记录数目
     * @return
     */
    public Integer getNotHandleCount();

}
