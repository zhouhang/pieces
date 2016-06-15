package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.SyncDataLogDao;
import com.jointown.zy.common.model.SyncDataLog;
import com.jointown.zy.common.service.SyncDataLogService;

@Service
public class SyncDataLogServiceImpl implements SyncDataLogService {
	
	@Autowired
	private SyncDataLogDao syncDataLog;

	@Override
	public boolean addSyncDataLog(SyncDataLog dataLog) {
		return syncDataLog.addSyncDataLog(dataLog);
	}

	@Override
	public List<SyncDataLog> queryAllDataLog() {
		return syncDataLog.getAllSyncDataLog();
	}

}
