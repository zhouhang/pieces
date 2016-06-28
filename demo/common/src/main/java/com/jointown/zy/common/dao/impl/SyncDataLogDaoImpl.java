package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.SyncDataLogDao;
import com.jointown.zy.common.model.SyncDataLog;

@Repository
public class SyncDataLogDaoImpl extends BaseDaoImpl implements  SyncDataLogDao{
	
	private String getSql(String sqlName){
		return "com.jointown.zy.common.dao.SyncDataLogDao."+sqlName;
	}

	@Override
	public boolean addSyncDataLog(SyncDataLog dataLog) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.insert(getSql("addSyncDataLog"),dataLog)>0?true:false;
	}

	@Override
	public List<SyncDataLog> getAllSyncDataLog() {
		return getSqlSession().selectList(getSql("findAllSyncDataLog"));
	}

}
