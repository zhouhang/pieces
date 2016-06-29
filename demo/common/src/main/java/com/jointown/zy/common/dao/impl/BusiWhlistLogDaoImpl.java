package com.jointown.zy.common.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiWhlistLogDao;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.BusiWhlistLog;
import com.jointown.zy.common.util.GsonFactory;

@Repository
public class BusiWhlistLogDaoImpl extends BaseDaoImpl implements BusiWhlistLogDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("com.jointown.zy.common.dao.BusiWhlistLogDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insertBusiWhlistLog(BusiWhlist busiWhlist, String remark, Long operatorId, String optype, boolean...recordSnapshot) {
		BusiWhlistLog  whlistlog = new BusiWhlistLog();
		whlistlog.setWlid(busiWhlist.getWlId());
		whlistlog.setUserid(operatorId);
		whlistlog.setBreedcode(busiWhlist.getBreedCode());
		whlistlog.setOptype(Short.valueOf(optype));
		whlistlog.setRemarks(remark);
		Date date = new Date();
		whlistlog.setCreatetime(date);
		whlistlog.setUpdatetime(date);
		whlistlog.setWltotal(new BigDecimal(busiWhlist.getWlTotal()));
		whlistlog.setWhListSnapshot(recordSnapshot.length>0&&recordSnapshot[0]?GsonFactory.createGson("yyyy-MM-dd HH:mm:ss.SSS").toJson(busiWhlist):null);
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiWhlistLogDao.insertBusiWhlistLog", whlistlog);
	}

	@Override
	public int insertSelective(BusiWhlistLog record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiWhlistLogDao.insertSelective", record);
	}

	@Override
	public BusiWhlistLog selectByPrimaryKey(Long id) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiWhlistLogDao.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiWhlistLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiWhlistLogDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(BusiWhlistLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiWhlistLogDao.updateByPrimaryKeyWithBLOBs", record);
	}

	@Override
	public int updateByPrimaryKey(BusiWhlistLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiWhlistLogDao.updateByPrimaryKey", record);
	}
}
