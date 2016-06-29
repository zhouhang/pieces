package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.UserInfo;

/**
 * @author kevinzhou
 * @date 2014-09-19 10:21:22
 */
public interface UserInfoDao {

	/**
	 * 添加记录
	 * 
	 * @param userInfo userInfo
	 * @return PrimaryKey
	 */
	public Integer addUserInfo(UserInfo userInfo);

	/**
	 * 根据name获取记录
	 * 
	 * @param userName
	 * @return UserInfo
	 */
	public UserInfo findUserInfoByUserName(String userName);

}