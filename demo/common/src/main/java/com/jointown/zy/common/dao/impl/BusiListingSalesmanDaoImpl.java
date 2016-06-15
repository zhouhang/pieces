package com.jointown.zy.common.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiListingSalesmanDao;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.model.BusiListingSalesman;

@Repository
public class BusiListingSalesmanDaoImpl extends BaseDaoImpl implements BusiListingSalesmanDao {

	
	@Override
	public void insertSalesMan(BusiListingSalesman salesMan) throws Exception{
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("com.jointown.zy.common.dao.BusiListingSalesmanDao.insertSalesMan", salesMan);
	}
	
	@Override
	public BusiListingSalesman findBusiListingSalesmanByListingId(String listingid) throws Exception{
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return (BusiListingSalesman)sqlSession.selectOne("com.jointown.zy.common.dao.BusiListingSalesmanDao.findBusiListingSalesmanByListingId", listingid);
	}
	
	@Override
	public void updateSalesManInfoByPRO() throws Exception{
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.selectOne("com.jointown.zy.common.dao.BusiListingSalesmanDao.updateSalesManInfoByPRO");
	}
}
