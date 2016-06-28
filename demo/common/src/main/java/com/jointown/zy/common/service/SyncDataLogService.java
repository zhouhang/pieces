package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.SyncDataLog;

public interface SyncDataLogService {
	
	public boolean addSyncDataLog(SyncDataLog log);
	
	public List<SyncDataLog> queryAllDataLog();

}
