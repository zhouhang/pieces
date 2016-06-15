package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.PayResultDao;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.model.PayResult;
import com.jointown.zy.common.service.PayResultService;

@Service
public class PayResultServiceImpl implements PayResultService {
	
	private static final Logger log = LoggerFactory.getLogger(PayResultServiceImpl.class);

	@Autowired
	private PayResultDao payResultDao;
	
	@Override
	public int addPayResult(PayOrder payOrder) throws Exception {
		PayResult payResult = new PayResult();
		payResult.setSourceSys(Integer.parseInt(payOrder.getSourceSys()));
		payResult.setOrderId(payOrder.getOrderId());
		payResult.setAmount(payOrder.getAmount());
		payResult.setAmtType(payOrder.getAmtType());
		payResult.setFlowId(payOrder.getFlowId());
		payResult.setCreateTime(new Date());
		payResult.setStatus(0);
		return payResultDao.addPayResult(payResult);
	}

	@Override
	public List<PayResult> selectPayResults(Integer sourceSys, String num)throws Exception {
		List<PayResult> payResultList = payResultDao.selectPayResults(sourceSys);
		if (StringUtils.isBlank(num)) {
			return payResultList;
		}
		Integer numm = Integer.parseInt(num);
		int resultNum = Math.min(numm.intValue(), payResultList.size());
		List<PayResult> pResultList = new ArrayList<PayResult>();
		for (int i = 0; i < resultNum; i++) {
			pResultList.add(payResultList.get(i));
		}
		log.info("pay result list is:" + payResultList);
		return pResultList;
	}

	@Override
	public int updatePayResultByResultId(Integer resultId,Integer status)throws Exception {
		return payResultDao.updatePayResultByResultId(resultId,status);
	}

}
