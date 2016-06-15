package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CmsCategoryDao;
import com.jointown.zy.common.model.CmsCategory;
@Repository
public class CmsCategoryDaoImpl extends BaseDaoImpl implements CmsCategoryDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.CmsCategoryMapper.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(CmsCategory cmsCategory) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.CmsCategoryMapper.insert", cmsCategory);
	}

	@Override
	public int insertSelective(CmsCategory cmsCategory) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.CmsCategoryMapper.insertSelective", cmsCategory);
	}

	@Override
	public CmsCategory selectByPrimaryKey(Long id) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.CmsCategoryMapper.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(CmsCategory cmsCategory) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.CmsCategoryMapper.updateByPrimaryKeySelective", cmsCategory);
	}

	@Override
	public int updateByPrimaryKey(CmsCategory cmsCategory) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.CmsCategoryMapper.updateByPrimaryKey", cmsCategory);
	}

	@Override
	public List<CmsCategory> selectCmsCategoryByCmsSiteID(Long cmsSiteID) {
		return this.getSqlSession().selectList
				("com.jointown.zy.common.dao.CmsCategoryMapper.getAllCmsCategoryByCmsSiteId", cmsSiteID);
	}

}
