package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.vo.AccountBillVo;

import java.util.List;

public interface AccountBillService extends ICommonService<AccountBill>{

    public PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo, Integer pageNum, Integer pageSize);

    public AccountBill createBill(AccountBill accountBill);

    public PageInfo<AccountBillVo> findVoAll(Integer pageNum, Integer pageSize);

    public AccountBillVo findVoById(Integer billId);

}
