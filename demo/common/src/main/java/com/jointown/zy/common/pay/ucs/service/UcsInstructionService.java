package com.jointown.zy.common.pay.ucs.service;

import ucs.creditpay.entities.requests.Request1007;



public interface UcsInstructionService {
	/**
	 * 支付指令
	 * @param payOrder
	 * @return
	 * @throws Exception 
	 */
	public String payLogin(Request1007 loginRequest) throws Exception;
}
