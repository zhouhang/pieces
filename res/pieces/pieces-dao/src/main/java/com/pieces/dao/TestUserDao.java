package com.pieces.dao;

import com.pieces.dao.model.TestUser;

public interface TestUserDao {
	TestUser getTestUserByUserName(String userName);
}
