package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;

public interface PayRecordService extends ICommonService<PayRecord>{

    public PageInfo<PayRecordVo> findByParams(PayRecordVo payRecordVo, Integer pageNum, Integer pageSize);
}
