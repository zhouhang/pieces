package com.pieces.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieces.dao.TestUserDao;
import com.pieces.dao.model.TestUser;
import com.pieces.service.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService {
	
	@Autowired
	private TestUserDao testUserDao;
	
	@Override
	public TestUser getTestUserByUserName(String userName) {
		return testUserDao.getTestUserByUserName(userName);
	}

}
