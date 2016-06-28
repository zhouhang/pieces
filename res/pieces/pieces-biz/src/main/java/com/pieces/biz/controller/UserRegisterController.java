package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.utils.MobileCodeUtil;
import com.pieces.service.utils.ValidationCodeUtil;

@Controller(value = "userRegisterController")
@RequestMapping
public class UserRegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/toRegister")
	public String toRegister(Model model) {
		return "public/register";
	}
	
	@RequestMapping(value = "/register")
	public String register(Model model,HttpServletRequest request,User user) {
		//1.获取页面参数
		//2.校验手机验证码
		//3.用户名，手机号是否重复，
		//4.生成盐，加密密码
		//5.保存数据库
		try {
			Map<String, Object> mobileCodeMap = (Map<String, Object>) request
					.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
			String verityResult = MobileCodeUtil.verityMobileCode(mobileCodeMap,"567890",user.getContactMobile());
			if (!"y".equals(verityResult)) {
				System.out.println("手机验证码错误");
			}
			User vUser = new User();
			vUser.setUserName(user.getUserName());
			List<User> users = userService.findUserByCondition(vUser);
			if(users!=null||users.size()!=0){
				System.out.println("用户名重复");
			}
			vUser.setUserName(null);
			vUser.setContactMobile(user.getContactMobile());
			users = userService.findUserByCondition(vUser);
			if(users!=null||users.size()!=0){
				System.out.println("手机号重复");
			}
			int result = userService.addUser(user);
			if(result > 0){
				System.out.println("成功添加会员");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "public/login";
	}
}
