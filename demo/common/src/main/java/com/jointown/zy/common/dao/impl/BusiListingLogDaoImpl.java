package com.jointown.zy.common.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiListingLogDao;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiListingLog;
import com.jointown.zy.common.util.GsonFactory;

@Repository
public class BusiListingLogDaoImpl extends BaseDaoImpl implements BusiListingLogDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("com.jointown.zy.common.dao.BusiListingLogDao.deleteByPrimaryKey", id);
	}
	
	@Override
	public int insertBusiListingLog(BusiListing  listingInfo, String remark, Long operatorId, String optype, boolean...recordSnapshot) {
		BusiListingLog log = new BusiListingLog();
		log.setListingid(listingInfo.getListingid());
		Date date = new Date();
		log.setCreatetime(date);
		log.setUpdatetime(date);
		log.setUserid(operatorId);
		log.setWlid(listingInfo.getWlid());
		log.setOptype(Short.valueOf(optype));
		log.setRemarks(remark);
		log.setListingSnapshot(recordSnapshot.length>0&&recordSnapshot[0]?GsonFactory.createGson("yyyy-MM-dd HH:mm:ss.SSS").toJson(listingInfo):null);
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiListingLogDao.insertListing", log);
	}

	@Override
	public int insertListing(BusiListingLog record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiListingLogDao.insertListing", record);
	}

	@Override
	public int insertSelective(BusiListingLog record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiListingLogDao.insertSelective", record);
	}

	@Override
	public BusiListingLog selectByPrimaryKey(Long id) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingLogDao.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiListingLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiListingLogDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(BusiListingLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiListingLogDao.updateByPrimaryKeyWithBLOBs", record);
	}

	@Override
	public int updateByPrimaryKey(BusiListingLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiListingLogDao.updateByPrimaryKey", record);
	}

	@Override
	public int insertBusiListingLog(BusiListing listingInfo,
			BusinessLogEnum logType, Long userId, Object... data) {
		BusiListingLog log = new BusiListingLog();
		log.setListingid(listingInfo.getListingid());
		Date date = new Date();
		log.setCreatetime(date);
		log.setUpdatetime(date);
		log.setUserid(userId);
		log.setWlid(listingInfo.getWlid());
		log.setOptype(Short.valueOf(logType.getCode()));
		log.setRemarks(logType.getMessage(data));
		log.setListingSnapshot(listingInfo!=null?GsonFactory.createGson("yyyy-MM-dd HH:mm:ss.SSS").toJson(listingInfo):null);
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiListingLogDao.insertListing", log);
	}
	
}
