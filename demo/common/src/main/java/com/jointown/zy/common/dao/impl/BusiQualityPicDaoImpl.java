package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiQualityPicDao;
import com.jointown.zy.common.model.BusiQualitypic;

/**
 * 质检图片管理DaoImpl
 * @author wangjunhu
 * 	2014-12-25
 * ------------华丽分割线---------------
 * 修改人：Mr.songwei
 * 修改日期：2014-12-26
 */
@Repository
public class BusiQualityPicDaoImpl extends BaseDaoImpl implements BusiQualityPicDao {

	@Override
	public int deleteByPrimaryKey(Long qcid) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiQualityPicDao.deleteByPrimaryKey", qcid);
	}

	@Override
	public int insertBusiQualitypic(BusiQualitypic record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiQualityPicDao.insertBusiQualitypic", record);
	}

	@Override
	public int insertSelective(BusiQualitypic record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiQualityPicDao.insertSelectiveBusiQualitypic", record);
	}

	@Override
	public BusiQualitypic selectByPrimaryKey(Long qcid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiQualityPicDao.selectByPrimaryKey",qcid);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQualitypic record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiQualityPicDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BusiQualitypic record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiQualityPicDao.updateByPrimaryKey", record);
	}
	
	@Override
	public int updateByWlIdAndPicIndex(BusiQualitypic record){
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiQualityPicDao.updateByWlIdAndPicIndex", record);
    }
    
	@Override
	public List<BusiQualitypic> selectAllPicByWLID(String wlid) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiQualityPicDao.selectAllPicByWLID", wlid);
	}

	@Override
	public int deleteByWlID(String wlID) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiQualityPicDao.deleteByWlID", wlID);
	}


	@Override
	public BusiQualitypic selectZJPicByWLID(String wlid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiQualityPicDao.selectZJPicByWLID", wlid);
	}

	@Override
	public int deleteQualityPicByWlID(String wlid) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiQualityPicDao.deleteQualityPicByWlID", wlid);
	}

	@Override
	public int deleteWlPicByID(String wlid) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiQualityPicDao.deleteWlPicByID", wlid);
	}

	@Override
	public List<BusiQualitypic> selectPicByWLID(String wlId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiQualityPicDao.selectPicByWLID", wlId);
	}

}