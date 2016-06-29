package com.jointown.zy.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jointown.zy.common.util.SpringUtil;


/**
 * 登陆
 * 
 */
@Controller(value = "userLoginController")
public class UserLoginController  extends UserBaseController{
	
	private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(HttpServletRequest request,HttpServletResponse response){ 
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        //pay=>bus
        return "redirect:"+SpringUtil.getConfigProperties("jointown.url")+"/userlogout?service="+SpringUtil.getConfigProperties("jointown.url.pay");
    }
	
	@RequestMapping(value="/userlogout")
    public String userlogout(HttpServletRequest request,HttpServletResponse response){
		String service = request.getParameter("service");
		if (SpringUtil.getConfigProperties("jointown.url.pay").equals(service)) {
			return "redirect:"+SpringUtil.getConfigProperties("jointown.url");
		}else {
			Subject subject = SecurityUtils.getSubject();	
			//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
			subject.logout();
			//pay=>bus
			return "redirect:"+SpringUtil.getConfigProperties("jointown.url")+"/userlogout?service="+service;
		}
	}
}
