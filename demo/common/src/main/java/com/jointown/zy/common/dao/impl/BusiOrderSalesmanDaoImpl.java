package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.model.BusiOrderSalesman;

@Repository
public class BusiOrderSalesmanDaoImpl extends BaseDaoImpl implements BusiOrderSalesmanDao {

	@Override
	public int deleteByPrimaryKey(Long infoId) throws Exception{
		return getSqlSession().delete("com.jointown.zy.common.dao.BusiOrderSalesmanDao.deleteByPrimaryKey", infoId);
	}

	@Override
	public int insert(BusiOrderSalesman record) throws Exception{
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiOrderSalesmanDao.insert", record);
	}


	@Override
	public BusiOrderSalesman selectByPrimaryKey(Long infoId) throws Exception{
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderSalesmanDao.selectByPrimaryKey", infoId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiOrderSalesman record) throws Exception{
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderSalesmanDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BusiOrderSalesman record) throws Exception{
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderSalesmanDao.updateByPrimaryKey", record);
	}
	
	
	/*订单ID查找对应的业务员信息*/
	@Override
	public BusiOrderSalesman selectByOrderid(String orderid) throws Exception{
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderSalesmanDao.selectByOrderid", orderid);
	}
}
