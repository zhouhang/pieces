package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQualityItemDao;
import com.jointown.zy.common.model.BusiQualityItem;
@Repository
public class BusiQualityItemDaoImpl extends BaseDaoImpl implements BusiQualityItemDao {

	@Override
	public int deleteByPrimaryKey(Long qualityItemId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusiQualityItem record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiQualityItemDao.insert", record);
	}

	@Override
	public int insertSelective(BusiQualityItem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiQualityItem selectByPrimaryKey(Long qualityItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQualityItem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusiQualityItem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delItemByQualityId(String wlId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiQualityItemDao.delItemByQualityId", wlId);
	}

	@Override
	public List<BusiQualityItem> selectQualityItemByWlId(String wlId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiQualityItemDao.selectQualityItemByWlId", wlId);
	}

}
