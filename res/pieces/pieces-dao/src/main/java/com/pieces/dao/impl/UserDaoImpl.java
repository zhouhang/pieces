package com.pieces.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public List<User> findUserByCondition(User user) {
		return getSqlSession().selectList("com.pieces.dao.UserMapper.findUserByCondition", user);
	}

	@Override
	public int addUser(User user) {
		return getSqlSession().insert("com.pieces.dao.UserMapper.addUser", user);
	}

	@Override
	public int updateUserByCondition(User user) {
		return getSqlSession().update("com.pieces.dao.UserMapper.updateUserByCondition", user);
	}

	@Override
	public List<User> findUserByVagueCondition(User user) {
		return getSqlSession().selectList("com.pieces.dao.UserMapper.findUserByVagueCondition", user);
	}

}
