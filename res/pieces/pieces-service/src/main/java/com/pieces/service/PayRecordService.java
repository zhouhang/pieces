package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.constant.bean.Result;

public interface PayRecordService extends ICommonService<PayRecord>{

    public PageInfo<PayRecordVo> findByParams(PayRecordVo payRecordVo, Integer pageNum, Integer pageSize);

    public PayRecordVo findVoById(Integer id);

    /**
     * 支付成功
     * @param payId
     * @return
     */
    public void success(Integer payId, Member member);

    /**
     * 支付失败
     * @param payId
     * @param msg
     * @return
     */
    public void fail(Integer payId, String msg, Member member);
}
