package com.jointown.zy.common.pay;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jointown.zy.common.enums.SyslogEnum;
import com.jointown.zy.common.model.PayFlowLog;
import com.jointown.zy.common.service.PayFlowLogService;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SysLogUtil;

@Component("payManager")
public class PayManager {

//	private static final Logger log = LoggerFactory.getLogger(PayManager.class);
	
	@Autowired
	private PayFlowLogService payFlowLogService;
	
	/**
	 * 支付流水日志记录
	 * @Author: ldp
	 * @Date: 2015年4月13日
	 * @param orderId
	 * 			交易订单号				
	 * @param sourceSys
	 * 			交易系统标识
	 * @param returnCode
	 * 			返回码
	 * @param status
	 * 			支付状态
	 * @param note
	 * 			备注
	 */
	public void payFlowLogAdd(String orderId,Integer sourceSys,String returnCode,Integer status,String note){
		PayFlowLog payFlowLog = new PayFlowLog();
		payFlowLog.setCreateTime(new Date());
		payFlowLog.setOrderId(orderId);
		payFlowLog.setSourceSys(sourceSys);
		payFlowLog.setReturnCode(returnCode);
		payFlowLog.setStatus(status);
		payFlowLog.setNote(note);
		payFlowLogService.addPayFlowLog(payFlowLog);
		SysLogUtil.logForPay(GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(payFlowLog));
	}
	
}
