package com.jointown.zy.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jointown.zy.common.service.UserInfoService;


/**
 * 登陆
 * 
 */
@Controller(value = "userLoginController")
public class UserLoginController  extends UserBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {        
		logger.info("info|test log|test log|test log|test log|test log|test log");
		logger.error("error|logback");
        return "index";
    }
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginForm(Model model){
		logger.info("info|test log|test log|test log|test log|test log|test log");
		logger.error("error|logback");
		return "/login";
	}
	
	/*
	 * 注释内容
	 * 1.功能简单描述
	 * 2.开发者 修改者
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		userInfoService.findUserInfoByUserName("test");
		
		Subject subject = SecurityUtils.getSubject();
		
		String username = (String)subject.getPrincipal();
		PrincipalCollection principals = subject.getPrincipals();
		boolean isAuthenticated = subject.isAuthenticated();
		
		logger.info("test log");
		
		ModelAndView mav=new ModelAndView();
        String str="这是返回给freemarker页面的值";
        mav.addObject("haha", str);
        
		Map<String,String> map=new HashMap<String,String>();
        map.put("name", username);
        map.put("age", principals+"");
        map.put("address", isAuthenticated+"");
        map.put("ad", "广州");
        map.remove("ad");
        mav.addObject("maplist", map);
        
        try{
        SecurityUtils.getSubject().login(new UsernamePasswordToken("admin", "admin"));
        }catch(Exception e){
        	//qiantai xxxx
        }
        mav.setViewName("/user");
        return mav;
        
	}
	
//	@RequestMapping(value="/logout",method=RequestMethod.GET)  
//    public String logout(){ 
//		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
//        SecurityUtils.getSubject().logout();  
//        return "redirect:/login";
//    } 
	
	@RequestMapping("/403")
	public String unauthorizedRole(){
		return "/403";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String user(Model model){
		return "/user";
	}
	
}
