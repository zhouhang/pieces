package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Payment;
import com.pieces.dao.vo.PaymentVo;

import java.util.Map;

public interface PaymentService extends ICommonService<Payment>{

    public PageInfo<PaymentVo> findByParams(PaymentVo paymentVo,Integer pageNum,Integer pageSize);

    public PaymentVo getByVo(PaymentVo paymentVo);

    public void save(Payment payment);

    public void handleResult(Map<String,String> params);
}
