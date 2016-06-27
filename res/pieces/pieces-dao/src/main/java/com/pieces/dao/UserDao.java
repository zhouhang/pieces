package com.pieces.dao;

import java.util.List;

import com.pieces.dao.model.User;

public interface UserDao {
	List<User> findUserByCondition(User user);

	int addUser(User user);
}
