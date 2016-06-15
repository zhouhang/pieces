package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CmsArticleDao;
import com.jointown.zy.common.model.CmsArticle;
@Repository
public class CmsArticleDaoImpl extends BaseDaoImpl implements
		CmsArticleDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(CmsArticle record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.CmsArticleMapper.insert", record);
	}

	@Override
	public int insertSelective(CmsArticle record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CmsArticle selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CmsArticle record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CmsArticle record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(CmsArticle record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
