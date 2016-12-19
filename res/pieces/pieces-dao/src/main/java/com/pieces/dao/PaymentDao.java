package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Payment;
import com.pieces.dao.vo.PaymentVo;

import java.util.List;
@AutoMapper
public interface PaymentDao extends ICommonDao<Payment>{

    public List<PaymentVo> findByParams(PaymentVo paymentVo);

}
