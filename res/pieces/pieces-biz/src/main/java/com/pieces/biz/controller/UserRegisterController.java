package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "register";
	}
	
	@RequestMapping(value = "/register")
	public String register(Model model,HttpServletRequest request,User user,String mobileCode) {
		//1.获取页面参数
		//2.校验手机验证码
		//3.生成盐，加密密码
		//4.保存数据库
		try {
			user.setStatus(0);
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message_register";
	}
	
	@RequestMapping(value="/getMobileCode")
	public String getMobileCode(Model model,HttpServletRequest request){
		
		return "aaa";
	}
	
	@RequestMapping(value="/ifExistUserName")
	@ResponseBody
	public String ifExistUserName(Model model,String userName){
		if(userService.ifExistUserName(userName)){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequestMapping(value="/ifExistMobile")
	@ResponseBody
	public String ifExistMobile(Model model,String contactMobile){
		if(userService.ifExistMobile(contactMobile)){
			return "false";
		}else{
			return "true";
		}
	}
}
