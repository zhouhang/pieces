package com.jointown.zy.common.pay;

import com.jointown.zy.common.model.PayOrder;


/**
 * 支付指令
 * @author ldp
 * date 2015年2月4日
 * Verison 0.0.1
 */
public interface IPayInstructionService {

	/**
	 * 支付指令
	 * @param payOrder
	 * @return
	 */
	public String pay(PayOrder payOrder);
	
}
