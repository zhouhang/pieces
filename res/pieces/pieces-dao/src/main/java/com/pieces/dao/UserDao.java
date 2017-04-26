package com.pieces.dao;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;
import org.apache.ibatis.annotations.Param;

@AutoMapper
public interface UserDao extends ICommonDao<User>{
	List<User> findUserByCondition(User user);

	int updateUserByCondition(User user);

	User findByUserName(String userName);

	List<UserVo> findVoByCondition(UserVo userVo);

	List<UserVo> findProxyUser(UserVo userVo);

	UserVo findVoById(Integer id);

	List<UserVo> findUpdateUser(@Param("updateTime")Date updateTime);

	User findByAccount(String accountName);

	/**
	 * 查询所有注册用户的openId
	 * @return
     */
	List<String> findOpenIds();

	Integer updateByWxInfo(List<User> list);
}
