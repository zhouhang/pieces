package com.pieces.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pieces.dao.model.User;

/**
 * 前台用户service
 * @author feng
 *
 */
public interface UserService {
	List<User> findUserByCondition(User user);

	int addUser(User user);
	
	boolean ifExistMobile(String contactMobile);
	
	boolean ifExistUserName(String userName);
	
	String getMobileCode(HttpServletRequest request);
	
	boolean checkMobileCode(String targetMobileCode);
}
