package com.pieces.service;

import java.util.List;

import com.pieces.dao.model.User;

/**
 * 前台用户service
 * @author feng
 *
 */
public interface UserService {
	List<User> findUserByCondition(User user);

	int addUser(User user);
}
