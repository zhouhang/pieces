package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.vo.AccountBillVo;

public interface AccountBillService extends ICommonService<AccountBill>{

    public PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo, Integer pageNum, Integer pageSize);
}
