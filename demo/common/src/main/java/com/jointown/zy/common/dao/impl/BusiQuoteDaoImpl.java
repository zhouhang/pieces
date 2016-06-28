package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQuoteDao;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiQuoteVo;

@Repository
public class BusiQuoteDaoImpl extends BaseDaoImpl implements BusiQuoteDao {

	@Override
	public int deleteByPrimaryKey(String quoteId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertQuote(BusiQuote busiQuote) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiQuoteDao.insertQuoteInfo", busiQuote);
	}

	@Override
	public int insertSelective(BusiQuote record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiQuote selectByPrimaryKey(String quoteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQuote record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiQuoteDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BusiQuote record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<BusiQuoteVo> selectQuoteListByCondition(Page<BusiQuoteVo> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiQuoteDao.selectQuoteListByCondition", page);
	}

	@Override
	public List<BusiQuote> selectQuotePageByPurchaseId(Page<BusiQuote> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiQuoteDao.selectQuotePageByPurchaseId", page);
	}

	@Override
	public int updateQuoteByPurchaseIdAndQuoteId(BusiQuote record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiQuoteDao.updateQuoteByPurchaseIdAndQuoteId", record);
	}
	
	@Override
	public List<BusiQuote> selectQuoteByPurchaseCode(String purCode){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiQuoteDao.selectQuoteListByPurchaseCode", purCode);
	}

}
