package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CmsArticleDataDao;
import com.jointown.zy.common.model.CmsArticleData;
@Repository
public class CmsArticleDataDaoImpl extends BaseDaoImpl implements
		CmsArticleDataDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return 0;
	}

	@Override
	public int insert(CmsArticleData cmsArticleData) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.CmsArticleDataMapper.insert", cmsArticleData);
	}

	@Override
	public int insertSelective(CmsArticleData cmsArticleData) {
		return 0;
	}

	@Override
	public CmsArticleData selectByPrimaryKey(Long id) {
		
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CmsArticleData cmsArticleData) {
		
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CmsArticleData cmsArticleData) {
		
		return 0;
	}

	@Override
	public int updateByPrimaryKey(CmsArticleData cmsArticleData) {
		
		return 0;
	}

}
