package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastYaocaiDao;
import com.jointown.zy.common.model.EastYaocai;

/**
 * 药材DaoImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月5日
 */
@Repository
public class EastYaocaiDaoImpl extends BaseDaoImpl implements EastYaocaiDao {

	@Override
	public int deleteByPrimaryKey(String ycnam) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.EastYaocaiDao.deleteByPrimaryKey",ycnam);
	}

	@Override
	public int insert(EastYaocai record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.EastYaocaiDao.insert", record);
	}

	@Override
	public int insertSelective(EastYaocai record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.EastYaocaiDao.insertSelective", record);
	}

	@Override
	public EastYaocai selectByPrimaryKey(String ycnam) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.EastYaocaiDao.selectByPrimaryKey", ycnam);
	}

	@Override
	public int updateByPrimaryKeySelective(EastYaocai record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastYaocaiDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(EastYaocai record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastYaocaiDao.updateByPrimaryKey", record);
	}

	@Override
	public List<EastYaocai> selectEastYaocaiPzs() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastYaocaiDao.selectEastYaocaiPzs");
	}

}
