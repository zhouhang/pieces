package com.pieces.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;

public interface UserDao extends ICommonDao<User>{
	List<User> findUserByCondition(User user);
	
	PageInfo<User> findUserByVagueCondition(User user,Integer pageNum, Integer pageSize);

	int addUser(User user);

	int updateUserByCondition(User user);

	User findByUserName(String userName);


	PageInfo<User> findByCondition(UserVo userVo, Integer pageNum, Integer pageSize);


}
