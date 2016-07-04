package com.pieces.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.UserDao;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.BasicConstants;
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
		user = creatPawAndSaltMd5(user);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setStatus(BasicConstants.USER_STATUS_VALID);
		user.setOnlineStatus(BasicConstants.USER_ONLINESTATUS_ONLINE);
		user.setBindErp(BasicConstants.USER_BINDERP_NO);
		user.setCreateChannel(BasicConstants.USER_CREATECHANNEL_BIZ);
		return userDao.addUser(user);
	}
	
	public User creatPawAndSaltMd5(User user){
		Password pass = EncryptUtil.PiecesEncode(user.getPassword());
		user.setPassword(pass.getPassword());
		user.setSalt(pass.getSalt());
		return user;
	}
	
	public User getPawAndSaltMd5(User user){
		Password pass = EncryptUtil.PiecesEncode(user.getPassword(),user.getSalt(),"");
		user.setPassword(pass.getPassword());
		user.setSalt(pass.getSalt());
		return user;
	}
	
	@Override
	public boolean ifExistMobile(String contactMobile){
		User user = new User();
		user.setContactMobile(contactMobile);
		List<User> users = userDao.findUserByCondition(user);
		if(users != null && users.size() != 0){
			return true;
		}else{
			return false;
		}
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
	public boolean checkMobileCode(String targetMobileCode) {
		// TODO Auto-generated method stub
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
	public int updateUserByCondition(User user) {
		return userDao.updateUserByCondition(user);
	}

	@Override
	public PageInfo<User> findUserByVagueCondition(User user,Integer pageNum, Integer pageSize) {
		return userDao.findUserByVagueCondition(user,pageNum,pageSize);
	}

}
