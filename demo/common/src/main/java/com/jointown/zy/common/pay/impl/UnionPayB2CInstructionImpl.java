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

@Component("unionPayB2C")
public class UnionPayB2CInstructionImpl extends UnionPayBase implements IPayInstructionService {

	//private static final Logger log = LoggerFactory.getLogger(UnionPayB2BInstructionImpl.class);
	
	@Override
	public String pay(PayOrder payOrder) {
		/**
		 * 交易请求url 从配置文件读取
		 */
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		//"20140820145625";// --订单发送时间
		String txnTime = sdf1.format(new Date());
		//"201408201508395217";// --商户订单号
		String orderId = String.valueOf(payOrder.getFlowId());
		// --交易金额
		String txnAmt = String.valueOf(payOrder.getAmount().multiply(new BigDecimal(100)).intValue());

		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", BankConfigConstant.UNION_B2C_VERSION);
		// 字符集编码 默认"UTF-8"
		data.put("encoding", BankConfigConstant.UNION_ENCODING);
		// 签名方法 01 RSA
		data.put("signMethod", BankConfigConstant.UNION_B2C_SIGNMETHOD);
		// 交易类型 01-消费
		data.put("txnType", BankConfigConstant.UNION_B2C_TXNTYPE);
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", BankConfigConstant.UNION_B2C_TXNSUBTYPE);
		// 业务类型 000201 B2C网关支付
		data.put("bizType", BankConfigConstant.UNION_B2C_BIZTYPE);
		// 渠道类型 07-互联网渠道
		data.put("channelType", BankConfigConstant.UNION_B2C_CHANNELTYPE);
		// 商户/收单前台接收地址 选送
		//后台服务对应的写法参照 FrontRcvResponse.java
		data.put("frontUrl", BankConfigConstant.UNION_B2C_FRONT_URL);
		// 商户/收单后台接收地址 必送
		//后台服务对应的写法参照 BackRcvResponse.java
		data.put("backUrl", BankConfigConstant.UNION_B2C_BACK_URL);
		// 接入类型:商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", BankConfigConstant.UNION_B2C_ACCESSTYPE);
		// 商户号码
		data.put("merId", BankConfigConstant.UNION_B2C_MERID);
		// 订单号 商户根据自己规则定义生成，每订单日期内不重复
		data.put("orderId", orderId);
		// 订单发送时间 格式： YYYYMMDDhhmmss 商户发送交易时间，根据自己系统或平台生成
		data.put("txnTime", txnTime);
		// 交易金额 分
		data.put("txnAmt", txnAmt);
		// 交易币种
		data.put("currencyCode", BankConfigConstant.UNION_CURRENCYCODE);
		//默认支付方式:网银支付
		data.put("defaultPayType", "0201");
		//支持支付方式:网银支付,储值卡支付
		data.put("supPayType", "0201,0004");
		/**
		 * 创建表单
		 */
		Map<String, String> submitFromData = signData(data);
		String html = createHtml(requestFrontUrl, submitFromData);
		return html;
	}
	
	

}
