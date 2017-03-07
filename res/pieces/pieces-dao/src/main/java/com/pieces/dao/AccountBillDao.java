package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.vo.AccountBillVo;

import java.util.List;
@AutoMapper
public interface AccountBillDao extends ICommonDao<AccountBill>{

    public List<AccountBillVo> findByParams(AccountBillVo accountBillVo);

    public List<AccountBillVo> findVoAll();

    public List<AccountBillVo> findVoAll(AccountBillVo accountBillVo);

    public AccountBillVo findVoById(Integer billId);


    /**
     * 待处理账单数目
     * @return
     */
    public Integer getNotHandleCount();

    public List<Integer> getNotHandleIds();

    List<AccountBillVo> findUnpaidBill();
}
