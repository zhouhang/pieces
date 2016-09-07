package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.vo.AccountBillVo;

import java.util.List;
@AutoMapper
public interface AccountBillDao extends ICommonDao<AccountBill>{

    public List<AccountBillVo> findByParams(AccountBillVo accountBillVo);

    public List<AccountBillVo> findVoAll();

    public AccountBillVo findVoById(Integer billId);
}
