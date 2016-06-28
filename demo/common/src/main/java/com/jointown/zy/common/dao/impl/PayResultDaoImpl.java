package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.PayResultDao;
import com.jointown.zy.common.model.PayResult;

@Repository
public class PayResultDaoImpl extends BaseDaoImpl implements PayResultDao {

	@Override
	public int addPayResult(PayResult payResult) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.PayResultDao.addPayResult", payResult);
	}

	@Override
	public List<PayResult> selectPayResults(Integer sourceSys) {
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("sourceSys", sourceSys);
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.PayResultDao.selectPayResults", params);
	}

	@Override
	public int updatePayResultByResultId(Integer resultId,Integer status) {
		Map<String, Object> pramsMap = new HashMap<String, Object>();
		pramsMap.put("resultId", resultId);
		pramsMap.put("status", status);
		pramsMap.put("doneTime", new Date());
		return this.getSqlSession().update("com.jointown.zy.common.dao.PayResultDao.updatePayResultByResultId", pramsMap);
	}

}
