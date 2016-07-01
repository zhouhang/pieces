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
	
	List<User> findUserByVagueCondition(User user);

	int addUser(User user);
	
	User creatPawAndSaltMd5(User user);
	
	User getPawAndSaltMd5(User user);
	
	int updateUserByCondition(User user);
	
	boolean ifExistMobile(String contactMobile);
	
	boolean ifExistUserName(String userName);
	
	boolean checkMobileCode(String targetMobileCode);
	
	String getRemoteHost(HttpServletRequest request);
}
