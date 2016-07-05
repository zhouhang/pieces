package com.pieces.biz.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pieces.dao.model.User;
import com.pieces.service.AreaService;
import com.pieces.service.UserService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.vo.MessageVo;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {
	@Autowired
    private AreaService areaService;
	@Autowired
	private UserService userService;


	@RequestMapping()
	public String userInfo(ModelMap model,HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		model.put("user", user);
		return "user_info";
	}

	@RequestMapping(value = "/material")
	public String material(Model model,HttpServletRequest request) {
		return "user_info";
	}
	
	@RequestMapping(value = "/toUserUpdatePassword")
	public String toUserUpdatePassword(ModelMap model,HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		model.put("user", user);
		return "user_update_password";
	}
	
	@RequestMapping(value = "/userUpdatePassword")
	@ResponseBody
	public String userUpdatePassword(ModelMap model,String pwdOld,String pwd,HttpServletRequest request) {
		MessageVo mv = new MessageVo();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		User oldUser = new User();
		BeanUtils.copyProperties(user, oldUser);
		oldUser.setPassword(pwdOld);
		if(userService.getPwdAndSaltMd5(oldUser).getPassword().equals(user.getPassword())){
			user.setPassword(pwd);
			user.setUpdateTime(new Date());
			user = userService.createPwdAndSaltMd5(user);
			userService.updateUserByCondition(user);
			mv.setResult("ok");
		}else{
			mv.setResult("false");
			mv.setResultMessage("原密码有误");
		}
		Gson gson = new Gson();
		return gson.toJson(mv);
	}
}
