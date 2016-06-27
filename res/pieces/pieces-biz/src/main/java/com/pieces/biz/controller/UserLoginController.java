package com.pieces.biz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.vo.TestUserVo;

@Controller(value = "userLoginController")
@RequestMapping
public class UserLoginController{
	
	@Autowired
	private UserService userService;
	
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
	public String login(Model model) {
		User tu = new User();
		// 页面数据验证逻辑
		try {
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			BizToken token = new BizToken("ycg", "111111", false, "127.0.0.1", "");
			subject.login(token);
			// 存入用户信息到session
			tu.setUserName(token.getUsername());
			List<User> users = userService.findUserByCondition(tu);
			BeanUtils.copyProperties(users.get(0), tu);
			tu.setId(users.get(0).getId());
			tu.setPassword(null);
			tu.setSalt(null);
			Session s = subject.getSession();
			s.setAttribute(RedisEnum.USER_SESSION_BOSS.getValue(), tu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "public/login";
	}

}
