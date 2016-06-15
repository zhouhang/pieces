package com.jointown.zy.common.service;

import com.jointown.zy.common.model.PayFlowLog;

/**
 * @ClassName: PayFlowLogService
 * @Description: 支付流水日志
 * @Author: ldp
 * @Date: 2015年4月11日
 * @Version: 1.0
 */
public interface PayFlowLogService {

	/**
	 * 新增支付流水
	 * @Author: ldp
	 * @Date: 2015年4月11日
	 * @param payFlowLog
	 * @return
	 */
	public int addPayFlowLog(PayFlowLog payFlowLog);
}
