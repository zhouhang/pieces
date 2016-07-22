package com.pieces.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.tools.utils.WebUtil;

/**
 * 处理主页业务
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;


    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        return "home";
    }
    
	/**
	 * 是否弹框登录
	 * 
	 * @param model
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pop", method = RequestMethod.POST)
	public void login(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated() && !subject.isRemembered()){
			Result result = new Result(true);
			WebUtil.print(response, result);
			return;
		}
		Result result = new Result(false);
		WebUtil.print(response, result);
	}


    /**
	 * 进入弹框登录页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/popLogin", method = RequestMethod.GET)
	public String toLogin(ModelMap model, HttpServletRequest request) {
		return "login_mini";
	}
	

}
