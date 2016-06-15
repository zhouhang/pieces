package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxUserDao;
import com.jointown.zy.common.model.UcUser;

@Repository
public class WxUserDaoImpl extends BaseDaoImpl implements WxUserDao {

	@Override
	public UcUser findByCondition(String userName) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxUserDao.selectByCondition", userName);
	}

}
