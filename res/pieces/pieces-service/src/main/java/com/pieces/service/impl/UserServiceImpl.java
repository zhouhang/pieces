package com.pieces.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.service.utils.MobileCodeUtil;
import com.pieces.tools.utils.SendMessage;

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
	public String getMobileCode(HttpServletRequest request) {
		String mobileNo = request.getParameter("memberMobile").trim();
		if(mobileNo == null || "".equals(mobileNo)){
			return "n";
		}
		HttpSession session = request.getSession();
		Map<String, Object> moCode = (Map<String, Object>) session.getAttribute(MobileCodeUtil.MOBILE_CODE);
		Date time = new Date();
		if(moCode!=null&&moCode.get("mobileNo").equals(mobileNo)){
			Date reSendDate = (Date) moCode.get("reSendDate");
			if(reSendDate.after(time)){
				return "eorr";
			}
		}
		//获取手机号、验证码及过期时间
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobileNo);
		//发送短信
		String sendFlag = SendMessage.sendMessage(mobileNo,(String) mobileCode.get("mobileCode"));
		if ("y".equals(sendFlag)) {//y标识短信发送前提交成功,短信提交成功后，再存session
			session.setAttribute(MobileCodeUtil.MOBILE_CODE,mobileCode);//存session
			return (String) mobileCode.get("mobileCode");
		}
		return sendFlag;
	}

	@Override
	public boolean checkMobileCode(String targetMobileCode) {
		// TODO Auto-generated method stub
		return false;
	}

}
