package com.pieces.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserService;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.service.utils.ValidUtils;


@Service
@Transactional
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
	public int addUser(User user) {
		createPwdAndSaltMd5(user);
		user.setIsDel(false);
		user.setOnlineStatus(false);
		user.setBindErp(false);
		user.setCreateTime(new Date());
		return this.create(user);
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
		return ValidUtils.listBlank(users);
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
	 * user后台验证
	 */
	public String valid(User user){
		StringBuffer message = new StringBuffer();
		Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{5,19}$");
		Matcher matcher = pattern.matcher(user.getUserName());
		if(StringUtils.isBlank(user.getUserName()) || !matcher.matches()){
			message.append("用户名错误");
		}
		if(this.checkUserName(user.getUserName())){
			message.append("用户名重复");
		}
		if(StringUtils.isBlank(user.getPassword())){
			message.append("密码不能为空");
		}
		if(StringUtils.isBlank(user.getCompanyFullName())){
			message.append("企业全称不能为空");
		}
		if(user.getAreaId() >= 10000){
			message.append("注册地有误");
		}
		if(StringUtils.isBlank(user.getContactName())){
			message.append("联系人姓名不能为空");
		}
		pattern = Pattern.compile("^1[345678]\\d{9}$");
		matcher = pattern.matcher(user.getContactMobile());
		if(StringUtils.isBlank(user.getContactMobile()) || !matcher.matches()){
			message.append("联系人手机错误");
		}
		return message.toString();
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
