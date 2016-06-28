package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.PayFlowListDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.vo.PayFlowListVo;

/**
 * 支付service
 * @author  ldp
 * @version V1.0
 * @date   2015-04-07
 */
public interface PayOrderService {

	/**
	 * 生成支付流水
	 * @param payOrder
	 * @return
	 */
	public int insertPayOrder(PayOrder payOrder);
	
	/**
	 * 根据支付流水号查询支付流水
	 * @Author: ldp
	 * @Date: 2015年4月10日
	 * @param flowId
	 * @return 返回支付流水信息
	 */
	public PayOrder getPayOrderByFlowId(String flowId);
	
	
	/**
	 * 根据支付流水号修改支付流水
	 * @Author: ldp
	 * @Date: 2015年4月10日
	 * @param payOrder
	 * @return
	 */
	public int updatePayOrder(PayOrder payOrder);
	
	/**
	 * 资金流水查询分页列表
	 */
	public List<PayFlowListVo> getPageList(Page<PayFlowListVo> page);
	
	
	/**
	 * 资金流水列表
	 * @return
	 */
	public List<PayFlowListVo> getPayFlowList(PayFlowListDto pfl);
	/**
	 * 根据交易订单号查询支付订单对象
	 * @param orderId
	 * @return
	 */
	public List<PayOrder> getPayOrderByOrderId(String orderId);
	
	public PayOrder getPaySuccessOrderByOrderId(String orderId) ;
	
}
