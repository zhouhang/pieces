package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.PayOrderDao;
import com.jointown.zy.common.dto.PayFlowListDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.vo.PayFlowListVo;

/**
 * 支付流水daoImpl
 * 
 * @author ldp date 2015-04-03
 */
@Repository
public class PayOrderDaoImpl extends BaseDaoImpl implements PayOrderDao {

	@Override
	public int insertPayOrder(PayOrder payOrder) {
		return this.getSqlSession().insert(
				"com.jointown.zy.common.dao.PayOrderDao.insertPayOrder",
				payOrder);
	}

	@Override
	public int updatePayOrder(PayOrder payOrder) {
		return this.getSqlSession().update(
				"com.jointown.zy.common.dao.PayOrderDao.updatePayOrder",
				payOrder);
	}

	@Override
	public List<PayFlowListVo> selectPayFlowList(Page<PayFlowListVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.PayOrderDao.getQueryPageList",page);
	}
	
	public List<PayFlowListVo> getPayFlowList(PayFlowListDto pfl){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.PayOrderDao.getList",pfl);
	}

	@Override
	public PayOrder getPayOrderByFlowId(String flowId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.PayOrderDao.getPayFlowByFlowId", flowId);
	}

	@Override
	public List<PayOrder> getPayOrderByOrderId(String orderId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.PayOrderDao.getPayFlowByOrderId", orderId);
	}
	
	public PayOrder getPaySuccessOrderByOrderId(String orderId){
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.PayOrderDao.getPaySuccessByOrderId", orderId);
	}

	@Override
	public int updatePayVoucherByFlowId(PayOrder payOrder) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowId", payOrder.getFlowId());
		params.put("confirmorId", payOrder.getConfirmorId());
		params.put("confirmTime", payOrder.getConfirmTime());
		params.put("status", payOrder.getStatus());
		params.put("payTime", payOrder.getPayTime());
		return this.getSqlSession().update("com.jointown.zy.common.dao.PayOrderDao.updatePayVoucherByFlowId", params);
	}

	@Override
	public PayFlowListVo getPayFlowVoByFlowId(String flowId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.PayOrderDao.getPayFlowVoByFlowId",flowId);
	}

	@Override
	public List<PayOrder> getPayFlowByCondition(PayOrder payOrder) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.PayOrderDao.getPayFlowByCondition", payOrder);
	}

	@Override
	public int updatePayFlow(PayOrder payOrder) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.PayOrderDao.updatePayFlow", payOrder);
	}

}
