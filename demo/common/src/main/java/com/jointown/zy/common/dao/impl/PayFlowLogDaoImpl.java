package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.PayFlowLogDao;
import com.jointown.zy.common.model.PayFlowLog;

@Repository
public class PayFlowLogDaoImpl extends BaseDaoImpl implements PayFlowLogDao {

	@Override
	public int addPayFlowLog(PayFlowLog payFlowLog) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.PayFlowLogDao.insertPayFlowLog", payFlowLog);
	}

}
