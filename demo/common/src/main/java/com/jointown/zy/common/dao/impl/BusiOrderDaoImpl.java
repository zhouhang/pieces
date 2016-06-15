package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiGoodsOrderVo;
import com.jointown.zy.common.vo.BusiOrderVo;

@Repository
public class BusiOrderDaoImpl extends BaseDaoImpl implements BusiOrderDao {

	@Override
	public List<BusiOrderVo> selectOrderListByPage(Page<BusiOrderVo> page){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectOrderListByPage", page);
	}

	@Override
	public int insertOrder(BusiOrder busiOrder) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiOrderDao.insertOrderInfo", busiOrder);
	}

	@Override
	public int cancleOrderById(String orderid) {
		BusiOrder order = new BusiOrder();
		order.setOrderid(orderid);
		order.setUpdatetime(new Date());
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDao.cancelOrderById", order);
	}
	
	@Override
	public int deleteColosedOrderById(String orderId){
		return getSqlSession().delete("com.jointown.zy.common.dao.BusiOrderDao.deleteColosedOrderById", orderId);
	}

	@Override
	public List<BusiOrderVo> selectOrderListByCondition(Page<BusiOrderVo> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectOrderListByCondition", page);
	}

	@Override
	public int updateOrderState(BusiOrder record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDao.updateOrderState", record);
	}

	@Override
	public int updateDiscountPrice(BusiOrder record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDao.updateDiscountPrice", record);
	}

	@Override
	public List<BusiGoodsOrderVo> selectGoodsOrderList(
			Page<BusiGoodsOrderVo> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectGoodsOrderList", page);
	}
	
	@Override
	public BusiOrder selectBusiOrderById(String orderid){
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderDao.selectBusiOrderById",orderid);
	}

	@Override
	public List<BusiOrder> selectMyOrdering(HashMap<String, Object> map) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectMyOrdering",map);
	}

	@Override
	public int updateByIdSelective(BusiOrder record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDao.updateByIdSelective",record);
	}
	
	/**
	 * @see com.jointown.zy.common.dao.BusiOrderDao#selectOrderListByBreedName(java.util.Map)
	 * 
	 * @author aizhengdong
	 *
	 * @data 2015年3月31日
	 */
	@Override
	public List<BusiOrderVo> selectOrderListByBreedName(Map<String, Object> map) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectOrderListByBreedName",map);
	}

	@Override
	public int updateOrderAppealState(BusiOrder busiOrder) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDao.updateOrderAppealState",busiOrder);
	}

	@Override
	public List<BusiOrder> selectBusiOrdersByAttributes(BusiOrder record) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectBusiOrdersByAttributes",record);
	}

	@Override
	public List<BusiOrder> selectOverTimeOrdersByPlaced(Integer afterDays) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectOverTimeOrdersByPlaced",afterDays);
	}
	
	@Override
	public int updateOrderByWmsFreezeSuccess(BusiOrder record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDao.updateOrderByWmsFreezeSuccess",record);
	}

	@Override
	public String selectCertifyName(Integer userId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderDao.selectCertifyName",userId);
	}

	@Override
	public List<Map<String,Object>> selectSoonTermOrder(Map<String,Object> map) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDao.selectSoonTermOrder",map);
	}

	@Override
	public int selectOrdersCountByUserId(Long userId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderDao.selectOrdersCountByUserId", userId);
	}

}
