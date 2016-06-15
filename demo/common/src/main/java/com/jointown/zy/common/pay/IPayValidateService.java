package com.jointown.zy.common.pay;

import java.util.Map;

/**
 * 支付验签
 * @author ldp
 * 2015-2-5
 * version 0.0.1
 */
public interface IPayValidateService {

	/**
	 * 支付验签
	 * @author ldp
	 * @return
	 */
	public boolean payValidate(Map<String, String> params);
}
