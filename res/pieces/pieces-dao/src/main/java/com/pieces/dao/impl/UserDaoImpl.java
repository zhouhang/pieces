package com.pieces.dao.impl;

import java.util.List;

import com.pieces.dao.vo.UserVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;
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

	/**
	 * 通过用户名或者手机号查询用户
	 * @param userName
	 * @return
     */
	@Override
	public User findByUserName(String userName) {
		return getSqlSession().selectOne("com.pieces.dao.UserMapper.findByUserName", userName);
	}


	@Override
	public PageInfo<User> findByCondition(UserVo userVo, Integer pageNum, Integer pageSize) {
		List<User> list = getSqlSession().selectList("com.pieces.dao.UserMapper.findByCondition", userVo,new RowBounds(pageNum, pageSize));
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public PageInfo<User> findUserByVagueCondition(User user,Integer pageNum, Integer pageSize) {
		List<User> list = getSqlSession().selectList("com.pieces.dao.UserMapper.findUserByVagueCondition", user,new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public User findById(int id) {
		return getSqlSession().selectOne("com.pieces.dao.UserMapper.findById", id);
	}


	@Override
	public List<User> findAll() {
		return getSqlSession().selectList("com.pieces.dao.UserMapper.findAll");
	}

	@Override
	public PageInfo<User> find(int pageNum, int pageSize) {
		List<User> list = getSqlSession().selectList("com.pieces.dao.UserMapper.findAll", null,new RowBounds(pageNum, pageSize));
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public int deleteById(int id) {
		return getSqlSession().delete("com.pieces.dao.UserMapper.deleteById",id);
	}

	@Override
	public int create(User user) {
		return getSqlSession().insert("com.pieces.dao.UserMapper.create",user);
	}

	@Override
	public int update(User user) {
		return getSqlSession().update("com.pieces.dao.UserMapper.update",user);
	}

}
