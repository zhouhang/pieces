package com.jointown.zy.common.pay.ucs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jointown.zy.common.dao.RemitAccountDao;
import com.jointown.zy.common.model.RefuseRemitFlow;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.model.RemitFlowLog;
import com.jointown.zy.common.model.RemitResult;
import com.jointown.zy.common.pay.ucs.service.RemitAccountService;
import com.jointown.zy.common.pay.ucs.util.UcsBase;
import com.jointown.zy.common.service.UcUserService;

@Component
public class RemitAccountServiceImpl  extends UcsBase implements RemitAccountService {
	@Autowired
	private RemitAccountDao remitAccountDao;

	@Override
	public int addRemitFlow(RemitFlow remitFlow) {
		return remitAccountDao.addRemitFlow(remitFlow);
	}

	@Override
	public int addRemitFlowLog(RemitFlowLog remitFlowLog) {
		return remitAccountDao.addRemitFlowLog(remitFlowLog);
	}

	@Override
	public int addRemitResult(RemitResult remitResult) {
		return remitAccountDao.addRemitResult(remitResult);
	}

	@Override
	public int updateRemitFlow(RemitFlow remitFlow) {
		return remitAccountDao.updateRemitFlow(remitFlow);
	}

	@Override
	public RemitFlow getRemitFlowById(String flowId) {
		return remitAccountDao.getRemitFlowById(flowId);
	}
	
	
	/**--added by biran 
	 * 20150706
	 * 订单查看拒绝划账流水使用
	 * param RemitFlow
	 * @throws Exception 
	 **/
	public List<RefuseRemitFlow> getRefuseRemitFlowsByOrderId(RemitFlow remitFlow) throws Exception{
		return remitAccountDao.getRefuseRemitFlowsByOrderId(remitFlow);
	}
	
	
}
