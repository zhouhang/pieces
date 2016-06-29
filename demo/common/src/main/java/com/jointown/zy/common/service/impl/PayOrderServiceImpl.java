package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.PayOrderDao;
import com.jointown.zy.common.dto.PayFlowListDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.vo.PayFlowListVo;
@Service
public class PayOrderServiceImpl implements PayOrderService {
	@Autowired
	private PayOrderDao payOrderDao;

	public int insertPayOrder(PayOrder payOrder) {
		return payOrderDao.insertPayOrder(payOrder);
	}
	
	/**
	 * 资金流水分页查询
	 */
	public List<PayFlowListVo> getPageList(Page<PayFlowListVo> page) {
		return payOrderDao.selectPayFlowList(page);
	}
	
	/**
	 * 资金流水列表
	 * @return
	 */
	public List<PayFlowListVo> getPayFlowList(PayFlowListDto pfl){
		return payOrderDao.getPayFlowList(pfl);
	}
	
	
	@Override
	public PayOrder getPayOrderByFlowId(String flowId) {
		return payOrderDao.getPayOrderByFlowId(flowId);
	}

	@Override
	public int updatePayOrder(PayOrder payOrder) {
		return payOrderDao.updatePayOrder(payOrder);
	}
	
	public PayOrder getPaySuccessOrderByOrderId(String orderId) {
		return payOrderDao.getPaySuccessOrderByOrderId(orderId);
	}

	@Override
	public List<PayOrder> getPayOrderByOrderId(String orderId) {
		return payOrderDao.getPayOrderByOrderId(orderId);
	}

}
