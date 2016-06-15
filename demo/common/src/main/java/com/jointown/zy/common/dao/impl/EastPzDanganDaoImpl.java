package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastPzDanganDao;
import com.jointown.zy.common.model.EastPzDangan;

/**
 * 品种档案DaoImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月6日
 */
@Repository
public class EastPzDanganDaoImpl extends BaseDaoImpl implements EastPzDanganDao {

	@Override
	public int deleteByPrimaryKey(String ycnam) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.EastPzDanganDao.deleteByPrimaryKey",ycnam);
	}

	@Override
	public int insert(EastPzDangan record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.EastPzDanganDao.insert", record);
	}

	@Override
	public int insertSelective(EastPzDangan record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.EastPzDanganDao.insertSelective", record);
	}

	@Override
	public EastPzDangan selectByPrimaryKey(String ycnam) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.EastPzDanganDao.selectByPrimaryKey", ycnam);
	}

	@Override
	public int updateByPrimaryKeySelective(EastPzDangan record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastPzDanganDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(EastPzDangan record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastPzDanganDao.updateByPrimaryKey", record);
	}

	@Override
	public List<String> selectBreedNameByName(String name) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastPzDanganDao.selectBreedNameByName",name);
	}
}
