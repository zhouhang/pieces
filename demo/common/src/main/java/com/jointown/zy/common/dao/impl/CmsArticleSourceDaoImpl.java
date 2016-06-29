package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CmsArticleSourceDao;
import com.jointown.zy.common.model.CmsArticleSource;
import com.jointown.zy.common.vo.CmsArticleSourceVo;
@Repository
public class CmsArticleSourceDaoImpl extends BaseDaoImpl implements
		CmsArticleSourceDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(CmsArticleSource record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.CmsArticleSourceMapper.insert", record);
	}

	@Override
	public int insertSelective(CmsArticleSource record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.CmsArticleSourceMapper.insertSelective", record);
	}

	@Override
	public CmsArticleSource selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CmsArticleSource record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(CmsArticleSource record) {
		return 0;
	}

	@Override
	public List<CmsArticleSourceVo> selectAll(HashMap<String,String> queryString) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.CmsArticleSourceMapper.selectAll",queryString);
	}
}
