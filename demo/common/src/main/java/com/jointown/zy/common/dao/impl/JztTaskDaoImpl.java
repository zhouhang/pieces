package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.JztTaskDao;
import com.jointown.zy.common.model.JztTask;

@Repository
public class JztTaskDaoImpl extends BaseDaoImpl implements JztTaskDao {

	@Override
	public int insertTask(JztTask record) {
		return getSqlSession().insert("insertTask", record);
	}

	@Override
	public int insertSelective(JztTask record) {
		return getSqlSession().insert("insertSelective", record);
	}

	@Override
	public List<JztTask> selectTaskList() {
		return getSqlSession().selectList("selectTaskList");
	}
}
