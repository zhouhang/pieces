package com.jointown.zy.common.pay.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.pay.IPayValidateService;
import com.jointown.zy.common.pay.UnionPayBase;
import com.unionpay.acp.sdk.SDKUtil;

@Component(value="unionPayValidate")
public class UnionPayB2BValidateServiceImpl extends UnionPayBase implements
		IPayValidateService {
	private static final Logger log = LoggerFactory.getLogger(UnionPayB2BValidateServiceImpl.class);
	@Override
	public boolean payValidate(Map<String, String> params) {

		Map<String, String> valideData = null;
		if (null != params && !params.isEmpty()) {
			Iterator<Entry<String, String>> it = params.entrySet()
					.iterator();
			valideData = new HashMap<String, String>(params.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				try {
					value = new String(value.getBytes("UTF-8"), BankConfigConstant.UNION_ENCODING);
				} catch (UnsupportedEncodingException e1) {
					log.error("error is :", e1);
				}
				valideData.put(key, value);
			}
		}
		if (!SDKUtil.validate(valideData, BankConfigConstant.UNION_ENCODING)) {
			log.info("验证签名结果[失败].");
			return false;
		} else {
			log.info("验证签名结果[成功].");
			return true;
		}
	}

}
