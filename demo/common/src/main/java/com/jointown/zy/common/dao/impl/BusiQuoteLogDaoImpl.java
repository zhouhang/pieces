package com.jointown.zy.common.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQuoteLogDao;

import com.jointown.zy.common.model.BusiQuoteLog;
import com.jointown.zy.common.model.BusiQuote;

@Repository
public class BusiQuoteLogDaoImpl extends BaseDaoImpl implements BusiQuoteLogDao {
	@Override
	public int insertBusiQuoteLog(BusiQuote  quoteInfo, String remark, String operatorId, String optype) {
		BusiQuoteLog log = new BusiQuoteLog();
		log.setPurchaseId(quoteInfo.getPurchaseId());
		log.setPurchaseCode(quoteInfo.getPurchaseCode());
		log.setQuotePrice(quoteInfo.getQuotePrice());
		log.setQuoteDescription(quoteInfo.getQuoteDescription());
		log.setQuoter(quoteInfo.getQuoter());
		log.setPhone(quoteInfo.getPhone());
		log.setOperator(operatorId);
		log.setStatus(quoteInfo.getStatus());
		Date date = new Date();
		log.setCreateTime(date);
		log.setUpdateTime(date);
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiQuoteLogDao.insertBusiQuoteLog", log);
	}
}
