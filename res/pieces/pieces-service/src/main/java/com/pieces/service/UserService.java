package com.pieces.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;

/**
 * 前台用户service
 * @author feng
 *
 */
public interface UserService extends ICommonService<User>{
	List<User> findUserByCondition(User user);
	
	PageInfo<User> findUserByVagueCondition(User user,Integer pageNum, Integer pageSize);

	int addUser(User user);

	int updateUser(User user);
	
	User createPwdAndSaltMd5(User user);

	User getPwdAndSaltMd5(User user);
	
	int updateUserByCondition(User user);
	
	boolean ifExistMobile(String contactMobile);
	
	boolean checkUserName(String userName);

	User findByUserName(String userName);
	
	boolean checkMobileCode(String targetMobileCode);

	PageInfo<User> findByCondition(UserVo userVo, Integer pageNum, Integer pageSize);


}
