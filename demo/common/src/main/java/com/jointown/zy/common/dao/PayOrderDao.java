package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.dto.PayFlowListDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.vo.PayFlowListVo;

/**
 * 支付流水Dao
 * @author ldp
 *
 */
public interface PayOrderDao {
	
	/**
	 * 生成支付流水
	 * @param payOrder
	 * @return
	 */
	public int insertPayOrder(PayOrder payOrder);
	
	/**
	 * 根据字付流水ID获取支付流水订单
	 * @Author: ldp
	 * @Date: 2015年4月10日
	 * @param flowId
	 * @return
	 */
	public PayOrder getPayOrderByFlowId(String flowId);
	
	/**
	 * 修改支付流水
	 * @param payOrder
	 * @return
	 */
	public int updatePayOrder(PayOrder payOrder);
	
	/**
	 * 后台资金流水分页查询列表
	 */
	public List<PayFlowListVo> selectPayFlowList(Page<PayFlowListVo> page);
	
	/**
	 * 获取资金流水列表
	 * @return
	 */
	public List<PayFlowListVo> getPayFlowList(PayFlowListDto pfl);
	/**
	 * 根据交易订单号查询支付订单对象
	 * @param orderId
	 * @return
	 */
	public List<PayOrder> getPayOrderByOrderId(String orderId);
	
	public PayOrder getPaySuccessOrderByOrderId(String orderId);
	/**
	 * 根据支付流水号，更新线下支付凭证
	 * @Author: ldp
	 * @Date: 2015年5月19日
	 * @param payOrder
	 * @return
	 */
	public int updatePayVoucherByFlowId(PayOrder payOrder);
	
	/**
	 * 根据支付流水ID，获取PayFlowVo
	 * @Author: ldp
	 * @Date: 2015年5月20日
	 * @param flowId
	 * @return
	 */
	public PayFlowListVo getPayFlowVoByFlowId(String flowId);
	/**
	 * 根据条件获取PayFlow多条记录
	 * @Author: zhouji
	 * @Date: 2015年7月15日
	 * @param payOrder
	 * @return
	 */
	public List<PayOrder> getPayFlowByCondition(PayOrder payOrder);
	
	/**
	 * 更新线下支付凭证
	 * @Author: zhouji
	 * @Date: 2015年7月15日
	 * @param payOrder
	 * @return
	 */
	public int updatePayFlow(PayOrder payOrder);
	
}
