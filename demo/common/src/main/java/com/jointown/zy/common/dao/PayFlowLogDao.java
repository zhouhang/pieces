package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.PayFlowLog;

/**
 * @ClassName: PayFlowLogDao
 * @Description: 支付流水日志记录Dao
 * @Author: ldp
 * @Date: 2015年4月11日
 * @Version: 1.0
 */
public interface PayFlowLogDao {

	/**
	 * 添加支付流水日志
	 * @Author: ldp
	 * @Date: 2015年4月11日
	 * @param payFlowLog
	 * @return
	 */
	public int addPayFlowLog(PayFlowLog payFlowLog);
}
