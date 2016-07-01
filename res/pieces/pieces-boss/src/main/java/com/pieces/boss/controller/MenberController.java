package com.pieces.boss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.utils.SendMessage;
import com.pieces.service.utils.YPSendMessage;
import com.pieces.service.vo.ValidFromVo;

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
	
	@RequestMapping(value = "/add/user")
	public String addUser(ModelMap model,User user,HttpServletRequest request) {
		List<User> users = userService.findUserByVagueCondition(user);
		
		model.put("users", users);
		model.put("user", user);
		
		return "customers";
	}
	
	@RequestMapping(value = "/to/add/user")
	public String toAddUser(ModelMap model,HttpServletRequest request) {
		return "customers-add";
	}
	
	@RequestMapping(value="/ifexist/username")
	@ResponseBody
	public String ifExistUserName(Model model,HttpServletRequest request){
		String userName = request.getParameter("param");
		ValidFromVo vo = new ValidFromVo();
		if(userService.ifExistUserName(userName)){
			vo.setStatus("n");
			vo.setInfo("用户名重复");
		}else{
			vo.setStatus("y");
		}
		Gson gson = new Gson();
		return gson.toJson(vo);
	}
	
	@RequestMapping(value="/ifexist/mobile")
	@ResponseBody
	public String ifExistMobile(Model model,HttpServletRequest request){
		String contactMobile = request.getParameter("param");
		ValidFromVo vo = new ValidFromVo();
		if(userService.ifExistMobile(contactMobile)){
			vo.setStatus("n");
			vo.setInfo("手机号重复");
		}else{
			vo.setStatus("y");
		}
		Gson gson = new Gson();
		return gson.toJson(vo);
	}
	
	@RequestMapping(value="/get/mobilecode")
	@ResponseBody
	public String getMobileCode(Model model,String contactMobile,HttpServletRequest request){
		ValidFromVo vo = new ValidFromVo();
		SendMessage sm = new YPSendMessage();
		if(sm.sendMessage(request, contactMobile)){
			vo.setStatus("y");
		}else{
			vo.setStatus("n");
			vo.setInfo("短信发送失败");
		}
		Gson gson = new Gson();
		return gson.toJson(vo);
	}
}
