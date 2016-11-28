package com.pieces.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;

@AutoMapper
public interface UserDao extends ICommonDao<User>{
	List<User> findUserByCondition(User user);
	
	List<User> findUserByVagueCondition(User user);

	int addUser(User user);

	int updateUserByCondition(User user);

	User findByUserName(String userName);

	List<User> findByCondition(UserVo userVo);

	List<UserVo> findVoByCondition(UserVo userVo);

	List<UserVo> findProxyUser(UserVo userVo);

	UserVo findVoById(Integer id);

}
