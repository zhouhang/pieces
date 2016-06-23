package com.pieces.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pieces.dao.TestUserDao;
import com.pieces.dao.model.TestUser;

@Repository
public class TestUserDaoImpl extends BaseDaoImpl implements TestUserDao {
	
	@Override
	public TestUser getTestUserByUserName(String userName) {
			return getSqlSession().selectOne("com.pieces.dao.TestUserMapper.selecctTestUserByUserName", userName);
	}

}
