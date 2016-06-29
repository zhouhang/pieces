package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQualityInfoDao;
import com.jointown.zy.common.model.BusiQualityinfo;

/**
 * 质检管理DaoImpl
 * @author wangjunhu
 * 	2014-12-25
 */
@Repository
public class BusiQualityInfoDaoImpl extends BaseDaoImpl implements BusiQualityInfoDao {

	@Override
	public int deleteByPrimaryKey(Long qualityinfoid) {
		return this.getSqlSession().delete("deleteByPrimaryKey", qualityinfoid);
	}

	@Override
	public int insertQualityInfo(BusiQualityinfo record) {
		return this.getSqlSession().insert("insertBusiQualityinfo", record);
	}

	@Override
	public int insertSelective(BusiQualityinfo record) {
		return this.getSqlSession().insert("insertSelectiveBusiQualityinfo", record);
	}

	@Override
	public BusiQualityinfo selectByPrimaryKey(Long qualityinfoid) {
		return this.getSqlSession().selectOne("selectByPrimaryKey", qualityinfoid);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQualityinfo record) {
		return this.getSqlSession().update("updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BusiQualityinfo record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiQualityInfoDao.updateByPrimaryKey", record);
	}

	@Override
	public int updateByWLIDSelective(BusiQualityinfo record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiQualityInfoDao.updateByWLIDSelective", record);
	}
	
	@Override
	public BusiQualityinfo selectByWLID(String wlid) {
		return this.getSqlSession().selectOne("selectByWLID", wlid);
	}
}
