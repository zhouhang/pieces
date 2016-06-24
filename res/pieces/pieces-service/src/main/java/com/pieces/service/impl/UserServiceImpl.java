package com.pieces.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> findUserByCondition(User user) {
		return userDao.findUserByCondition(user);
	}

	@Override
	public int addUser(User user) {
		Password pass = EncryptUtil.PiecesEncode(user.getPassword());
		user.setPassword(pass.getPassword());
		user.setSalt(pass.getSalt());
		return userDao.addUser(user);
	}

}
