package com.jointown.zy.common.pay.ucs.service;

import java.util.List;

import com.jointown.zy.common.model.RefuseRemitFlow;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.model.RemitFlowLog;
import com.jointown.zy.common.model.RemitResult;

public interface RemitAccountService {
	public int addRemitFlow(RemitFlow remitFlow);
	public int addRemitFlowLog(RemitFlowLog remitFlowLog);
	public int addRemitResult(RemitResult remitResult);
	public int updateRemitFlow(RemitFlow remitFlow);
	public RemitFlow getRemitFlowById(String flowId);
	
	
	
	/**--added by biran 
	 * 20150706
	 * 订单查看拒绝划账流水使用
	 * param RemitFlow
	 **/
	public List<RefuseRemitFlow> getRefuseRemitFlowsByOrderId(RemitFlow remitFlow) throws Exception; 
}
