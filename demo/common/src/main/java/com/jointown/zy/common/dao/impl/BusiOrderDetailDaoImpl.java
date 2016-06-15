/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiOrderDetailDao;
import com.jointown.zy.common.model.BusiOrderDetail;

/**
 * @ClassName: BusiOrderDetailDaoImpl
 * @Description: TODO
 * @Author: 刘漂
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
@Repository
public class BusiOrderDetailDaoImpl extends BaseDaoImpl implements BusiOrderDetailDao {
	
	private String sql(String sqlName){
		return BusiOrderDetailDao.class.getName()+"."+sqlName;
	}
	

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.BusiOrderDetailDao#selectOrderDetailById(java.lang.String)
	 */
	/**
	 * @Description: TODO
	 * @Author: 刘漂
	 * @Date: 2015年4月16日
	 * @param orderDetailId
	 * @return
	 * @throws Exception
	 */
	@Override
	public BusiOrderDetail selectOrderDetailById(Long orderDetailId)
			throws Exception {
		return getSqlSession().selectOne(sql("selectOrderDetailById"), orderDetailId);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.BusiOrderDetailDao#selectOrderDetailByOrderId(java.lang.String)
	 */
	/**
	 * @Description: TODO
	 * @Author: 刘漂
	 * @Date: 2015年4月16日
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BusiOrderDetail> selectOrderDetailByOrderId(String orderId)
			throws Exception {
		return getSqlSession().selectList(sql("selectOrderDetailByOrderId"), orderId);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.BusiOrderDetailDao#insertOrderDetail(com.jointown.zy.common.model.BusiOrderDetail)
	 */
	/**
	 * @Description: TODO
	 * @Author: 刘漂
	 * @Date: 2015年4月16日
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertOrderDetail(BusiOrderDetail order){
		return getSqlSession().insert(sql("insertOrderDetail"), order);
	}
}

