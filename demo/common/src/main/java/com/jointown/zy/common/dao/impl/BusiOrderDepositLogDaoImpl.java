/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiOrderDepositLogDao;
import com.jointown.zy.common.model.BusiOrderDeposit;
import com.jointown.zy.common.model.BusiOrderDepositLog;

/**
 * @ClassName: BusiOrderDepositLogDaoImpl
 * @Description: 订单划账操作日志DAO实现类
 * @Author: 赵航
 * @Date: 2015年5月18日
 * @Version: 1.0
 */
@Repository
public class BusiOrderDepositLogDaoImpl extends BaseDaoImpl implements BusiOrderDepositLogDao{

	@Override
	public int insertDepositLog(BusiOrderDeposit deposit, Long operattorId,
			String operattorIp, String result, String remark) throws Exception {
		BusiOrderDepositLog depositLog = new BusiOrderDepositLog();
		depositLog.setOrderId(deposit.getOrderId());
		depositLog.setDepositAmount(deposit.getDepositAmount());
		depositLog.setSellerId(deposit.getSellerId());
		depositLog.setSellerAmount(deposit.getSellerAmount());
		depositLog.setBuyerId(deposit.getBuyerId());
		depositLog.setBuyerAmount(deposit.getBuyerAmount());
		depositLog.setPlatformAmount(deposit.getPlatformAmount());
		depositLog.setDepositType(deposit.getDepositType());
		depositLog.setOperationResult(Short.valueOf(result));
		depositLog.setResultRemark(remark);
		depositLog.setOperattorId(operattorId);
		depositLog.setOperattorIp(operattorIp);
		depositLog.setCreatetime(new Date());
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiOrderDepositLogDao.insertDepositLog", depositLog);
	}

}
