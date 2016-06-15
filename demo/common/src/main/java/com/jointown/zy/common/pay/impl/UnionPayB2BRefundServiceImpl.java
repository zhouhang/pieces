package com.jointown.zy.common.pay.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.enums.RefundStatusEnums;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.IRefundInstructionService;
import com.jointown.zy.common.pay.UnionPayBase;
import com.unionpay.acp.sdk.SDKConfig;

public class UnionPayB2BRefundServiceImpl extends UnionPayBase implements
		IRefundInstructionService {

	private static final Logger log = LoggerFactory.getLogger(UnionPayB2BRefundServiceImpl.class);
	@Override
	public int refund(PayOrder payOrder) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("version", BankConfigConstant.UNION_B2B_VERSION);//M
		params.put("encoding", BankConfigConstant.UNION_ENCODING);//M
		params.put("signMethod", BankConfigConstant.UNION_B2B_SIGNMETHOD);//M
		//交易类型 00
		params.put("txnType", "04");//M
		//默认00
		params.put("txnSubType", "00");//M
		params.put("backUrl", BankConfigConstant.UNION_B2B_BACK_URL);
		//默认:000000
		params.put("bizType", "000000");//M
		params.put("channelType", BankConfigConstant.UNION_B2B_CHANNELTYPE);
		params.put("origQryId", payOrder.getQueryId());
		params.put("txnAmt", payOrder.getAmount());
		//0：普通商户直连接入2：平台类商户接入
		params.put("accessType", BankConfigConstant.UNION_B2B_ACCESSTYPE);//M
		params.put("merId", BankConfigConstant.UNION_B2B_MER_ID);//M
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//被查询交易的交易时间
		params.put("txnTime", sdf.format(payOrder.getCreateTime()));//M
		//被查询交易的订单号(注：不能与支付订单号一致)
		params.put("orderId", payOrder.getOrderId());//M

		String requestBackUrl = SDKConfig.getConfig().getBackRequestUrl();
		Map<String, String> resmap = submitDate(params,requestBackUrl);

		log.info(resmap.toString());
		String respCode = resmap.get("respCode");
		if (StringUtils.isNotBlank(respCode)) {
			if (!"00".equals(respCode)) {// 如果服务器返回不成功，结束流程，返回错误信息
				log.info("处理不成功");
				return RefundStatusEnums.REFUND_FAILED.getRefundStatus();
			} else {
				log.info("处理成功");
				return RefundStatusEnums.REFUND_PROCESSING.getRefundStatus();
			}
		} else {
			log.info("服务器返回错误");
			return RefundStatusEnums.REFUND_FAILED.getRefundStatus();
		}

	}

}
