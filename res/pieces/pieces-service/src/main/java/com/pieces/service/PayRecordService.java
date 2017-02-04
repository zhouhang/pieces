package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;

import java.util.List;

public interface PayRecordService extends ICommonService<PayRecord>{

    public PageInfo<PayRecordVo> findByParams(PayRecordVo payRecordVo, Integer pageNum, Integer pageSize);
    public List<PayRecordVo> findByParams(PayRecordVo payRecordVo);


    public PayRecord createForBill(PayRecordVo payRecordVo, String[] imgs,Integer userId);

    public PageInfo<PayRecordVo> findByNormalRecord(Integer pageNum, Integer pageSize);

    public PageInfo<PayRecordVo> findByNormalRecord(PayRecordVo payRecordVo,Integer pageNum, Integer pageSize);

    public PayRecord create(PayRecordVo payRecordVo,String[] img,Integer userId);

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

    public PageInfo<PayRecordVo> findByUserId(PayRecordVo payRecordVo,Integer pageNum, Integer pageSize);

    public Integer getNotHandleCount();


    /**
     * 第三方支付成功
     * @param payRecordVo
     * @return
     */
    public PayRecordVo paySuccess(PayRecordVo payRecordVo);

    public List<Integer> getNotHandleIds();


}
