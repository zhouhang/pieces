package com.jointown.zy.common.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiTermOrderDetailDao;
import com.jointown.zy.common.model.BusiTermOrderDetail;

@Repository
public class BusiTermOrderDetailDaoImpl extends BaseDaoImpl implements BusiTermOrderDetailDao {

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusiTermOrderDetail record) {
		record.setCreateTime(new Date());
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiTermOrderDetailDao.insert", record);
	}

	@Override
	public int insertSelective(BusiTermOrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiTermOrderDetail selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusiTermOrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusiTermOrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiTermOrderDetail selectTermOrderByOrderId(String orderId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiTermOrderDetailDao.selectTermOrderByOrderId", orderId);
	}

}
