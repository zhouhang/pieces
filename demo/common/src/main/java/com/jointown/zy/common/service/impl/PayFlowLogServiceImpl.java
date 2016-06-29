package com.jointown.zy.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.PayFlowLogDao;
import com.jointown.zy.common.model.PayFlowLog;
import com.jointown.zy.common.service.PayFlowLogService;

@Service
public class PayFlowLogServiceImpl implements PayFlowLogService {

	@Autowired
	private PayFlowLogDao payFlowLogDao;
	
	@Override
	public int addPayFlowLog(PayFlowLog payFlowLog) {
		return payFlowLogDao.addPayFlowLog(payFlowLog);
	}

}
