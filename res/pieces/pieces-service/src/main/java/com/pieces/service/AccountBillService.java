package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.AccountBillVo;

import java.util.List;

 public interface AccountBillService extends ICommonService<AccountBill>{

     PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo, Integer pageNum, Integer pageSize);

     AccountBill createBill(AccountBill accountBill);

     PageInfo<AccountBillVo> findVoAll(Integer pageNum, Integer pageSize);

    /**
     * 查询用户所有帐单
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
     PageInfo<AccountBillVo> findVoAll(Integer userId,Integer pageNum, Integer pageSize);

    /**
     * @param billId
     * @return
     */
     AccountBillVo findVoById(Integer billId);

    /**
     * 账单审核成功
     * @param billId
     */
     void auditSuccess(Integer billId, Integer memberId);

    /**
     * 账单审核失败
     * @param billId
     */
     void auditFail(Integer billId, String msg, Integer memberId);


    /**
     * 当用户付款单审核通过时改变账单数据
     * @param billId
     */
     void refreshStatus(Integer billId);

    /**
     * 为用户生成3个月账期的账单
     *
     */

     void generateBill(PayRecord payRecord,Integer memberId);


     Integer getNotHandleCount();


     List<Integer> getNotHandleIds();

    /**
     * 查询用户未付款的账单且距离到期时间为 1天和7天的.
     * @return
     */
    List<AccountBillVo> findUnpaidBill();

     /**
      * 根据用户ID 和订单ID 获取有效的账期
      * @param orderId
      * @param userId
      * @return
      */
    AccountBill findValidBillByOrderID(Integer orderId, Integer userId);

}
