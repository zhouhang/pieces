package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.UserInfoDao;
import com.jointown.zy.common.model.UserInfo;

/**
 * @author kevinzhou
 * @date 2014-09-19 10:21:22
 */
@Repository(value = "userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {

	@Override
	public Integer addUserInfo(UserInfo userInfo) {
		return null;
	}

	@Override
	public UserInfo findUserInfoByUserName(String userName) {
		return null;
	}
}