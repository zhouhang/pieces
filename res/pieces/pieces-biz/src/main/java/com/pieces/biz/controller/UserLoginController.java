package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.vo.MessageVo;
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
	@ResponseBody
	public String login(Model model,String userName,String password,HttpServletRequest request) {
		// 页面数据验证逻辑
		MessageVo mv = new MessageVo();
		Gson gson = new Gson();
		try {
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			BizToken token = new BizToken(userName, password, false, userService.getRemoteHost(request), "");
			subject.login(token);
			// 存入用户信息到session
			User tu = new User();
			tu.setUserName(token.getUsername());
			List<User> users = userService.findUserByCondition(tu);
			BeanUtils.copyProperties(users.get(0), tu);
			tu.setId(users.get(0).getId());
			tu.setPassword(null);
			tu.setSalt(null);
			Session s = subject.getSession();
			s.setAttribute(RedisEnum.USER_SESSION_BOSS.getValue(), tu);
			mv.setResult("ok");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setResult("false");
		}
		return gson.toJson(mv);
	}
	
	@RequestMapping(value = "/registerLogin")
	public String registerLogin(Model model,String userName,String password,HttpServletRequest request) {
		// 页面数据验证逻辑
		try {
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			BizToken token = new BizToken(userName, password, false, userService.getRemoteHost(request), "");
			subject.login(token);
			// 存入用户信息到session
			User tu = new User();
			tu.setUserName(token.getUsername());
			List<User> users = userService.findUserByCondition(tu);
			BeanUtils.copyProperties(users.get(0), tu);
			tu.setId(users.get(0).getId());
			tu.setPassword(null);
			tu.setSalt(null);
			Session s = subject.getSession();
			s.setAttribute(RedisEnum.USER_SESSION_BOSS.getValue(), tu);
			return "user_info";
		} catch (Exception e) {
			e.printStackTrace();
			return "register";
		}
	}
	
	@RequestMapping(value = "/toLogin")
	public String toLogin(Model model,HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value = "/toFindPassword")
	public String toFindPassword(Model model,HttpServletRequest request) {
		return "find_password";
	}

	@RequestMapping(value = "/findPasswordOne")
	@ResponseBody
	public String findPasswordOne(HttpServletRequest request,Model model,String username,String mobile,String mobileCode) {
		Map codeMap = (Map)request.getSession().getAttribute(mobile);
		MessageVo mv = new MessageVo();
		User user = new User();
		user.setUserName(username);
		List<User> users = userService.findUserByCondition(user);
		if(user!=null&&users.size() != 0){
			if(users.get(0).getContactMobile().equals(mobile)){
				if(codeMap != null){
					String code = codeMap.get("code").toString();
					if(code!=null&&!code.equals("")){
						if(code.equals(mobileCode)){
							mv.setResult("ok");
						}else{
							mv.setResult("falseCode");
							mv.setResultMessage("验证码错误");
						}
					}else{
						mv.setResult("falseCode");
						mv.setResultMessage("验证码过期");
					}
				}else{
					mv.setResult("falseCode");
					mv.setResultMessage("请获取验证码");
				}
			}else{
				mv.setResult("falseMobile");
				mv.setResultMessage("手机号码错误");
			}
		}else{
			mv.setResult("falseName");
			mv.setResultMessage("用户名不存在");
		}
		Gson gson = new Gson();
		return gson.toJson(mv);
	}
	
	@RequestMapping(value = "/toFindPasswordTwo")
	public String toFindPasswordTwo(ModelMap model,HttpServletRequest request,String userName) {
		model.put("userName",userName);
		return "find_password_2";
	}
	
	@RequestMapping(value = "/findPasswordTwo")
	public String findPasswordTwo(Model model,String pwd,String userName) {
		try {
			User user = new User();
			user.setUserName(userName);
			List<User> users = userService.findUserByCondition(user);
			user = users.get(0);
			User updateUser = new User();
			updateUser.setId(user.getId());
			updateUser.setPassword(pwd);
			updateUser.setUpdateTime(new Date());
			updateUser = userService.creatPawAndSaltMd5(updateUser);
			userService.updateUserByCondition(updateUser);
			return "message_find_pwd";
		} catch (Exception e) {
			e.printStackTrace();
			return "message_find_pwd";
		}

	}
}
