package com.pieces.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.pieces.dao.ICommonDao;
import com.pieces.service.AbsCommonService;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.dto.Password;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.WebUtil;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbsCommonService<User> implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> findUserByCondition(User user) {
		return userDao.findUserByCondition(user);
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
     */
	@Override
	@Transactional
	public int addUser(User user) {
		createPwdAndSaltMd5(user);
		user.setIsDel(false);
		user.setOnlineStatus(false);
		user.setBindErp(false);
		user.setCreateTime(new Date());
		user.setSource(0);
		return this.create(user);
	}


	/**
	 * 修改用户
	 * @param user
	 * @return
     */
	@Override
	@Transactional
	public int updateUser(User user) {
		if(StringUtils.isNotBlank(user.getPassword())){
			createPwdAndSaltMd5(user);
		}
		user.setUpdateTime(new Date());
		return this.update(user);
	}


	@Override
	public boolean ifExistMobile(String contactMobile){
		User user = new User();
		user.setContactMobile(contactMobile);
		List<User> users = userDao.findUserByCondition(user);
		return (users != null && users.size() != 0);
	}
	
	@Override
	public boolean checkUserName(String userName){
		User user = new User();
		user.setUserName(userName);
		List<User> users = userDao.findUserByCondition(user);
		return ValidUtils.listNotBlank(users);
	}


	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public boolean checkMobileCode(String targetMobileCode) {
		return false;
	}

	@Override
	@Transactional
	public int updateUserByCondition(User user) {
		return userDao.updateUserByCondition(user);
	}

	@Override
	public PageInfo<User> findUserByVagueCondition(User user,Integer pageNum, Integer pageSize) {
		return userDao.findUserByVagueCondition(user,pageNum,pageSize);
	}

	@Override
	public ICommonDao<User> getDao() {
		return userDao;
	}

	/**
	 * 生成密码加盐
 	 * @param user
	 * @return
     */
	public User createPwdAndSaltMd5(User user){
		Password pass = EncryptUtil.PiecesEncode(user.getPassword());
		user.setPassword(pass.getPassword());
		user.setSalt(pass.getSalt());
		return user;
	}

	/**
	 * 得到密码加盐
	 * @param user
	 * @return
     */
	public User getPwdAndSaltMd5(User user){
		Password pass = EncryptUtil.PiecesEncode(user.getPassword(),user.getSalt(),"");
		user.setPassword(pass.getPassword());
		user.setSalt(pass.getSalt());
		return user;
	}

}
