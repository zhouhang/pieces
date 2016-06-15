package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQualityInfoNewDao;
import com.jointown.zy.common.model.BusiQualityInfoNew;

@Repository
public class BusiQualityInfoNewDaoImpl extends BaseDaoImpl implements BusiQualityInfoNewDao {

	@Override
	public int deleteByPrimaryKey(Long qualityInfoId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusiQualityInfoNew record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiQualityInfoNewDao.insert", record);
	}

	@Override
	public int insertSelective(BusiQualityInfoNew record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiQualityInfoNew selectByPrimaryKey(Long qualityInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQualityInfoNew record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiQualityInfoNewDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BusiQualityInfoNew record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiQualityInfoNew selectQualityByWlId(String wlId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiQualityInfoNewDao.selectQualityByWlId",wlId);
	}

}
