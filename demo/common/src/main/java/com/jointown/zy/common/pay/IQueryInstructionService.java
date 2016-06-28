package com.jointown.zy.common.pay;

import com.jointown.zy.common.model.PayOrder;

/**
 * @author ldp
 * date 2015年2月5日
 * Verison 0.0.1
 */
public interface IQueryInstructionService {

	/**
	 * 查询指令
	 * @author ldp
	 * @param payOrder
	 * @return
	 */
	public PayBackResult query(PayOrder payOrder);
}
