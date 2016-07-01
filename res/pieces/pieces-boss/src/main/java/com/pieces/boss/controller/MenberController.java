package com.pieces.boss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pieces.dao.model.User;
import com.pieces.service.UserService;

@Controller
@RequestMapping(value = "/menber")
public class MenberController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/get/user")
	public String getUser(ModelMap model,User user,HttpServletRequest request) {
		List<User> users = userService.findUserByVagueCondition(user);
		
		model.put("users", users);
		model.put("user", user);
		
		return "customers";
	}
}
