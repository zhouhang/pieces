package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.JztTaskDao;
import com.jointown.zy.common.model.JztTask;
import com.jointown.zy.common.service.JztTaskService;

@Service
public class JztTaskServiceImpl implements JztTaskService {

	@Autowired
	private JztTaskDao jztTaskDao;

	@Override
	public int insertTask(JztTask record) {
		return jztTaskDao.insertTask(record);
	}

	@Override
	public int insertSelective(JztTask record) {
		return jztTaskDao.insertSelective(record);
	}

	@Override
	public List<JztTask> selectTaskList() {
		return jztTaskDao.selectTaskList();
	}
}
