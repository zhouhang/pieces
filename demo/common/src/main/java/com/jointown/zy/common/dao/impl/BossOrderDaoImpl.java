/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BossOrderDao;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderInfoVo;
import com.jointown.zy.common.vo.BossOrderListVo;
import com.jointown.zy.common.vo.OrderMobileEmailVo;

/**
 * @ClassName: BossOrderDaoImpl
 * @Description: 后台订单相关操作DAO实现类
 * @Author: 赵航
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
@Repository
public class BossOrderDaoImpl extends BaseDaoImpl implements BossOrderDao{

	@Override
	public List<BossOrderListVo> selectOrderListByPage(
			Page<BossOrderListVo> page) throws Exception {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BossOrderDao.selectOrderListByPage", page);
	}

	@Override
	public BossOrderInfoVo selectOrderInfoById(String orderId) throws Exception {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BossOrderDao.selectOrderInfoById", orderId);
	}

	@Override
	public int updateOrderExpireTime(BusiOrder order) throws Exception {
		return getSqlSession().update("com.jointown.zy.common.dao.BossOrderDao.updateOrderExpireTime", order);
	}

	@Override
	public int updateOrderStateById(String orderId, String stateKey) throws Exception {
		BusiOrder order = new BusiOrder();
		order.setOrderid(orderId);
		order.setUpdatetime(new Date());
		order.setOrderstate(Integer.valueOf(stateKey));
		return getSqlSession().update("com.jointown.zy.common.dao.BossOrderDao.updateOrderStateById", order);
	}

	@Override
	public List<BusiOrder> selectOverdueOrderList(Map<String, Object> params)
			throws Exception {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BossOrderDao.selectOverdueOrderList", params);
	}
	
	@Override
	public int updateOrderDepositByOrderId(BusiOrder busiOrder) throws Exception {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BossOrderDao.updateOrderDepositByOrderId", busiOrder);
	}

	@Override
	public int updateTermOrder(String orderId) throws Exception {
		BusiOrder order = new BusiOrder();
		order.setOrderid(orderId);
		order.setUpdatetime(new Date());
		return getSqlSession().update("com.jointown.zy.common.dao.BossOrderDao.updateTermOrder", order);
	}

	@Override
	public OrderMobileEmailVo selectOrderMobileEmail(String orderId)
			throws Exception {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BossOrderDao.selectOrderMobileEmail", orderId);
	}

}
