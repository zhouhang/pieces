package com.jointown.zy.common.pay;

import com.jointown.zy.common.model.PayOrder;

/**
 * @author ldp
 * date 2015年2月5日
 * Verison 0.0.1
 */
public interface IRefundInstructionService {

	/**
	 * 退款(退货)指令
	 * @param payOrder
	 */
	public int refund(PayOrder payOrder);
}
