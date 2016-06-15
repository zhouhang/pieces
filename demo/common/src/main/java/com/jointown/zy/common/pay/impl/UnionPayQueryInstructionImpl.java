package com.jointown.zy.common.pay.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.IQueryInstructionService;
import com.jointown.zy.common.pay.PayBackResult;
import com.jointown.zy.common.pay.UnionPayBase;
import com.unionpay.acp.sdk.SDKConfig;

public class UnionPayQueryInstructionImpl extends UnionPayBase implements
		IQueryInstructionService {

	private static final Logger log = LoggerFactory.getLogger(UnionPayQueryInstructionImpl.class);
	
	@Override
	public PayBackResult query(PayOrder payOrder) {
		PayBackResult pbr = new PayBackResult();
		pbr.setStatus(String.valueOf(PayStatusEnum.UNPAY.getStatus()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("version", BankConfigConstant.UNION_B2B_VERSION);// M
		params.put("encoding", BankConfigConstant.UNION_ENCODING);// M
		// params.put("certId", certId);// M
		// 填写对报文摘要的签名
		// params.put("signature", signature);//M
		params.put("signMethod", BankConfigConstant.UNION_B2B_SIGNMETHOD);// M
		params.put("txnType", "00");// M
		params.put("txnSubType", "00");// M
		params.put("bizType", "000000");// M
		params.put("accessType", BankConfigConstant.UNION_B2B_ACCESSTYPE);// M
		params.put("merId", BankConfigConstant.UNION_B2B_MER_ID);// M
		params.put("txnTime", sdf.format(payOrder.getCreateTime()));// M
		params.put("orderId", payOrder.getOrderId());// M
		params.put("queryId", payOrder.getQueryId());// C
		/** 交易请求url 从配置文件读取*/
		 String singleQueryUrl = SDKConfig.getConfig().getSingleQueryUrl();
		
		Map<String, String> resmap = submitDate(params,singleQueryUrl);
		log.info("order query back result info is:" + resmap);
		String respCode = resmap.get("respCode");
		BigDecimal amt = new BigDecimal(resmap.get("txnAmt")).divide(new BigDecimal(100));
		if(!"00".equals(respCode)){
			log.info("query response failed!");
			return pbr;
		}
		if ("00".equals(resmap.get("origRespCode"))) {
			//与银联返回金额(单位:分)作比较,返回金额需除以100
			if ((payOrder.getAmount()).compareTo(amt)!=0) {
				log.info("query:order amt not equal!");
				return pbr;
			}
			log.info("order pay success!");
			pbr.setStatus(String.valueOf(PayStatusEnum.SUCCESS.getStatus()));
			pbr.setOrderId(resmap.get("orderId"));
			return pbr;
		}else if ("01".equals(resmap.get("origRespCode"))) {
			log.info("order pay failed!");
			pbr.setStatus(String.valueOf(PayStatusEnum.FAILED.getStatus()));
			return pbr;
		}
		return pbr;
	}

}
