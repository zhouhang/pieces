package com.jointown.zy.common.pay.impl;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ucs.creditpay.entities.notices.UcsApiMessage;
import ucs.creditpay.entities.requests.Request1003;
import ucs.creditpay.entities.responses.Response1003;
import ucs.creditpay.http.HttpServices;
import ucs.creditpay.security.SignatureFactory;
import ucs.creditpay.utils.Base64Utils;
import ucs.creditpay.utils.GsonUtils;
import ucs.creditpay.utils.UcsConfig;

import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.IQueryInstructionService;
import com.jointown.zy.common.pay.PayBackResult;
import com.jointown.zy.common.pay.ucs.util.UcsBase;
/**
 * @ClassName: CMBQueryInstructionServiceImpl
 * @Description: 珍药宝支付单笔查询接口实现
 * @Author: ldp
 * @Date: 2015年4月29日
 * @Version: 1.0
 */
@Component("cmbQuery")
public class CMBQueryInstructionServiceImpl extends UcsBase implements IQueryInstructionService {

	private static final Logger log = LoggerFactory.getLogger(CMBQueryInstructionServiceImpl.class);
	
	@Override
	public PayBackResult query(PayOrder payOrder) {
		try{
			String MallId = "U1000001";
			String PaymentNo = "633a3115b2854ae3a20bb488b551d8da";
			Request1003 request1003 = new Request1003();
			request1003.setMallId(MallId);
			request1003.setOptType(Integer.parseInt("1003"));
			request1003.setPaymentNo(PaymentNo);
			
			String jsonReqData = GsonUtils.toJson(request1003);

			String requestData = Base64Utils.encode(jsonReqData.getBytes(Charset.forName("UTF-8")));
			//签名
			String requestSigndata = SignatureFactory.getSigner().signature(jsonReqData);
	
			HttpServices httpServices = new HttpServices();
	
			UcsApiMessage respMsg = httpServices.doPost(UcsConfig.getConfig().getBackRequestUrl(), requestData,requestSigndata);
	
			Response1003 response1003 = new Response1003(respMsg.getData(),
					respMsg.getSigndata());
			log.info("respData is:" + response1003.getResponsePlainText());
		}catch(Exception e){
			log.error("cmd query order error is:",e);
		}
		return null;
	}

}
