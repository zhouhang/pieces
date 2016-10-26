package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.UserDao;
import com.ms.dao.enums.UserEnum;
import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;
import com.ms.service.UserService;
import com.ms.service.enums.RedisEnum;
import com.ms.service.redis.RedisManager;
import com.ms.service.sms.SmsUtil;
import com.ms.tools.entity.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl  extends AbsCommonService<User> implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private SmsUtil smsUtil;

	@Autowired
	private RedisManager redisManager;


	@Override
	public PageInfo<UserVo> findByParams(UserVo userVo,Integer pageNum,Integer pageSize) {
		if (pageNum == null || pageSize == null) {
			pageNum = 1;
			pageSize = 10;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<UserVo> list = userDao.findByParams(userVo);
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public UserVo findByPhone(String phone) {
            return userDao.findByPhone(phone);
	}

	@Override
	public UserVo findById(Integer id) {
		UserVo vo = new UserVo();
		vo.setId(id);
		return userDao.findByParams(vo).get(0);
	}

	@Override
	@Transactional
	public void disable(Integer id) {
		User user =new User();
		user.setId(id);
		user.setType(-1);
		userDao.update(user);
	}

	@Override
	public void login(Subject subject, UsernamePasswordToken token) {
		try{
			subject.login(token);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Shiro 登入错误.");
		}

		User user = findByPhone(token.getUsername());
		user.setPassword(null);
		user.setSalt(null);
		Session s = subject.getSession();
		s.setAttribute(RedisEnum.USER_SESSION_BIZ.getValue(), user);

	}

	@Override
	public void logout() {
		SecurityUtils.getSubject().logout();
	}

	@Override
	@Transactional
	public void loginSms(String phone, String code) {
		String rcode = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA_LOGIN.getValue()+phone);
		if (!code.equalsIgnoreCase(rcode)) {
			throw new RuntimeException("验证码错误");
		}
		User user =  findByPhone(phone);

		if (user == null) {
			user = new User();
			user.setPhone(phone);
			user.setType(UserEnum.auto.getType());
			create(user);
		}
	}

	@Override
	@Transactional
	public void register(String phone, String code, String password) {

		if (findByPhone(phone) != null) {
			throw new RuntimeException("电话号码已经存在");
		}

		String rcode = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA_REGISTER.getValue()+phone);
		if (!code.equalsIgnoreCase(rcode)) {
			throw new RuntimeException("验证码错误");
		}
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		user.setType(UserEnum.enable.getType());
		create(user);
	}

	@Override
	public void sendRegistSms(String phone) {
		try {
			smsUtil.sendRegistCaptcha(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendLoginSms(String phone) {
		try {
			smsUtil.sendLoginCaptcha(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ICommonDao<User> getDao() {
		return userDao;
	}

}
