package com.pieces.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.User;

/**
 * 前台用户service
 * @author feng
 *
 */
public interface UserService extends ICommonService<User>{
	List<User> findUserByCondition(User user);
	
	PageInfo<User> findUserByVagueCondition(User user,Integer pageNum, Integer pageSize);

	int addUser(User user);
	
	User creatPawAndSaltMd5(User user);
	
	User getPawAndSaltMd5(User user);
	
	int updateUserByCondition(User user);
	
	boolean ifExistMobile(String contactMobile);
	
	boolean checkUserName(String userName);
	
	boolean checkMobileCode(String targetMobileCode);
	
	String valid(User user);
}
