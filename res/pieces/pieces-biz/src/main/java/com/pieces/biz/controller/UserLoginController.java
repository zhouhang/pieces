package com.pieces.biz.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.dao.model.TestUser;
import com.pieces.service.TestUserService;
import com.pieces.service.vo.TestUserVo;

@Controller(value = "userLoginController")
@RequestMapping
public class UserLoginController{
	
	@Autowired
	private TestUserService testUserService;
	
    public TestUserVo getUserInfo(HttpServletRequest request){
    	
    	return null;
    }
    
	@RequestMapping(value = "/logout")
	public String logout(ModelMap model) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	@RequestMapping(value = "/login")
	public String welcome(Model model) {
		
		TestUser tu = testUserService.getTestUserByUserName("ff");
		
		System.out.println(tu);
		return "public/login";
	}

}
