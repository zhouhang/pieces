package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.RemitAccountDao;
import com.jointown.zy.common.model.RemitResult;
import com.jointown.zy.common.pay.RemitResultResp;
import com.jointown.zy.common.service.RemitResultService;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.TimeUtil;
/**
 * @ClassName: RemitResultServiceImpl
 * @Description: 划账流水结果处理
 * @Author: ldp
 * @Date: 2015年7月3日
 * @Version: 1.0
 */
@Service
public class RemitResultServiceImpl implements RemitResultService {

	private static final Logger log = LoggerFactory.getLogger(RemitMangeServiceImpl.class);
	
	@Autowired
	private RemitAccountDao remitAccountDao;
	
	@Override
	public List<RemitResultResp> queryRemitResult(Integer sysSource, Integer num)
			throws Exception {
		log.info("request param source_sys is:" + sysSource);
		log.info("request param num is:" + num);
		List<RemitResultResp> rrresp = new ArrayList<RemitResultResp>();
		List<RemitResult> remitResultLists = remitAccountDao.selectRemitResult(sysSource, num);
		if (remitResultLists == null || remitResultLists.size() == 0 ) {
			return rrresp;
		}
		Iterator<RemitResult> iterator = remitResultLists.iterator();
		while (iterator.hasNext()) {
			RemitResultResp remitResultResp = new RemitResultResp();
			RemitResult remitResult = iterator.next();
			remitResultResp.setOrderId(remitResult.getOrderid());
			remitResultResp.setRemitFlowId(remitResult.getFlowId().toString());
			remitResultResp.setRemitResultId(remitResult.getResultId().toString());
			remitResultResp.setRemitStatus(remitResult.getOprateStatus().toString());
			if (null != remitResult.getRemitTime()) {
				remitResultResp.setRemitTime(TimeUtil.formate_YYYY_MM_DD_HH_MI_SS.format(remitResult.getRemitTime()));
			}
			remitResultResp.setRemitType(remitResult.getRemitType().toString());
			rrresp.add(remitResultResp);
		}
		log.info("queryRemitResult is :" + BeanUtil.objectToJson(rrresp));
		return rrresp;
	}

	@Override
	public String doneRemitResult(Integer remitResultId) throws Exception {
		int flag = remitAccountDao.updateRemitResult(remitResultId);
		return String.valueOf(flag);
	}

}
