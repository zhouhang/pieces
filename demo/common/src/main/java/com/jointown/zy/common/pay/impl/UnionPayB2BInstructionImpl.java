package com.jointown.zy.common.pay.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.IPayInstructionService;
import com.jointown.zy.common.pay.UnionPayBase;
import com.unionpay.acp.sdk.SDKConfig;

@Component("unionPay")
public class UnionPayB2BInstructionImpl extends UnionPayBase implements IPayInstructionService {

	//private static final Logger log = LoggerFactory.getLogger(UnionPayB2BInstructionImpl.class);
	
	@Override
	public String pay(PayOrder payOrder) {
		Map<String, Object> contentData = new HashMap<String, Object>();
		String merId = BankConfigConstant.UNION_B2B_MER_ID;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String txnTime = sdf1.format(new Date());//"20140820145625";// --订单发送时间
		String orderId = String.valueOf(payOrder.getFlowId());//sdf2.format(new Date());//"201408201508395217";// --商户订单号
		String txnAmt = String.valueOf(payOrder.getAmount().multiply(new BigDecimal(100)).intValue());
		//固定填写
		contentData.put("version", BankConfigConstant.UNION_B2B_VERSION);//M
		//默认取值：UTF-8
		contentData.put("encoding", BankConfigConstant.UNION_ENCODING);//M
		//01RSA 02MD5 (暂不支持)
		contentData.put("signMethod", BankConfigConstant.UNION_B2B_SIGNMETHOD);//M
		//取值：01 
		contentData.put("txnType", BankConfigConstant.UNION_B2B_TXNTYPE);//M
		//01：自助消费，通过地址的方式区分前台消费和后台消费（含无跳转支付）
		contentData.put("txnSubType", BankConfigConstant.UNION_B2B_TXNSUBTYPE);//M
		//业务类型
		contentData.put("bizType", BankConfigConstant.UNION_B2B_BIZTYPE);//M
		contentData.put("channelType", BankConfigConstant.UNION_B2B_CHANNELTYPE);//M
		//前台返回商户结果时使用，前台类交易需上送
		contentData.put("frontUrl", BankConfigConstant.UNION_B2B_FRONT_URL);//C
		//后台返回商户结果时使用，如上送，则发送商户后台交易结果通知
		contentData.put("backUrl", BankConfigConstant.UNION_B2B_BACK_URL);//M
		//0：普通商户直连接入2：平台类商户接入
		contentData.put("accessType", BankConfigConstant.UNION_B2B_ACCESSTYPE);//M
		contentData.put("merId", merId);//M
		//商户端生成
		contentData.put("orderId", orderId);//M
		//商户发送交易时间
		contentData.put("txnTime", txnTime);//M
		//交易单位为分
		contentData.put("txnAmt", txnAmt);//M
		//默认为156交易 参考公参
		contentData.put("currencyCode", BankConfigConstant.UNION_CURRENCYCODE);//M
		contentData.put("issInsCode", payOrder.getBankCode());//O
		
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
		Map<String, String> submitFromData = signData(contentData);
		/**
		 * 创建表单
		 */
		String html = createHtml(requestFrontUrl, submitFromData);
		return html;
	}

}
