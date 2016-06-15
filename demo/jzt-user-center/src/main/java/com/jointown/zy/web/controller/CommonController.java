package com.jointown.zy.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.service.UcUserService;

/**
 * 描述：无用、被遗弃的controller<br/>
 * 日期：2015.1.27
 */
@Controller
@RequestMapping(value="/common")
public class CommonController extends UserBaseController{
	@Autowired
	private UcUserService ucUserService;
	
	@RequestMapping(value="/header",method=RequestMethod.GET)
	public void getHeader(HttpServletRequest req, HttpServletResponse resp, ModelMap model){
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
//		UcUser user=(UcUser)subject.getSession().getAttribute("user");
//		if(user==null && userName!=null && !"".equals(userName)){
//			user = ucUserService.findUcUserByUserName(userName);
//			subject.getSession().setAttribute("user", user);
//		}
		setNoCache(resp);
		try {
			writePlain(req, resp, (userName!=null && !"".equals(userName))?userName:"");
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
