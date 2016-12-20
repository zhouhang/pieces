package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Payment;
import com.pieces.dao.vo.PaymentVo;

public interface PaymentService extends ICommonService<Payment>{

    public PageInfo<PaymentVo> findByParams(PaymentVo paymentVo,Integer pageNum,Integer pageSize);

    public PaymentVo getByVo(PaymentVo paymentVo);
}
