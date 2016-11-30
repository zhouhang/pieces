package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.AccountBillVo;

import java.util.List;

public interface AccountBillService extends ICommonService<AccountBill>{

    public PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo, Integer pageNum, Integer pageSize);

    public AccountBill createBill(AccountBill accountBill);

    public PageInfo<AccountBillVo> findVoAll(Integer pageNum, Integer pageSize);

    /**
     * 查询用户所有帐单
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<AccountBillVo> findVoAll(Integer userId,Integer pageNum, Integer pageSize);

    /**
     * 查询所有用户帐单
     * @param billId
     * @return
     */
    public AccountBillVo findVoById(Integer billId);

    /**
     * 账单审核成功
     * @param billId
     */
    public void auditSuccess(Integer billId, Integer memberId);

    /**
     * 账单审核失败
     * @param billId
     */
    public void auditFail(Integer billId, String msg, Integer memberId);


    /**
     * 当用户付款单审核通过时改变账单数据
     * @param billId
     */
    public void refreshStatus(Integer billId);

    /**
     * 为用户生成3个月账期的账单
     *
     */

    public void generateBill(PayRecord payRecord,Integer memberId);


    public Integer getNotHandleCount();

}
