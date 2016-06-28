package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.SyncDataLog;

public interface SyncDataLogDao {
	
	public boolean addSyncDataLog(SyncDataLog dataLog);
	
	public List<SyncDataLog> getAllSyncDataLog();

}
