package com.pieces.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pieces.dao.ICommonDao;
import com.pieces.service.AbsCommonService;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import org.springframework.transaction.annotation.Transactional;

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
	public boolean ifExistUserName(String userName){
		User user = new User();
		user.setUserName(userName);
		List<User> users = userDao.findUserByCondition(user);
		if(users != null && users.size() != 0){
			return true;
		}else{
			return false;
		}
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
	public String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
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
