package com.pieces.service;

import com.pieces.dao.model.TestUser;

public interface TestUserService {
	TestUser getTestUserByUserName(String userName);
}
