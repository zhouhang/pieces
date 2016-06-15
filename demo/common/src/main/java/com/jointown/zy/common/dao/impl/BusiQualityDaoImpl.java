package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQualityDao;
import com.jointown.zy.common.model.BusiQuality;

@Repository
public class BusiQualityDaoImpl extends BaseDaoImpl implements BusiQualityDao{

	@Override
	public int deleteByPrimaryKey(Long qualityId) {
		return 0;
	}

	@Override
	public int insert(BusiQuality record) {
		return this.getSqlSession().insert("com.dao.BusiQualityMapper.insert", record);
	}

	@Override
	public int insertSelective(BusiQuality record) {
		return 0;
	}

	@Override
	public BusiQuality selectByPrimaryKey(Long qualityId) {
		return this.getSqlSession().selectOne("com.dao.BusiQualityMapper.selectByPrimaryKey", qualityId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQuality record) {
		return this.getSqlSession().update("com.dao.BusiQualityMapper.updateByPrimaryKeySelective", record);
	}

		
	@Override
	public int updateByPrimaryKey(BusiQuality record) {
		return 0;
	}

	@Override
	public int deleteByWlID(String wlID) {
		return this.getSqlSession().delete("com.dao.BusiQualityMapper.deleteByWlID", wlID);
	}
}
