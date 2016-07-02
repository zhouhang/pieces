package com.pieces.boss.controller;

import java.util.List;
import java.util.Map;

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
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.utils.SendMessage;
import com.pieces.service.utils.YPSendMessage;
import com.pieces.service.vo.ValidFromVo;

@Controller
@RequestMapping(value = "/menber")
public class MenberController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/get/userlist")
	public String getUserList(ModelMap model,User user,HttpServletRequest request) {
		List<User> users = userService.findUserByVagueCondition(user);
		
		model.put("users", users);
		model.put("user", user);
		
		return "customers";
	}
	
	@RequestMapping(value = "/get/user")
	public String getUser(ModelMap model,User user,HttpServletRequest request) {
		List<User> users = userService.findUserByCondition(user);
		
		model.put("user", users.get(0));
		
		return "customers-info";
	}
	
	@RequestMapping(value = "/add/user")
	@ResponseBody
	public String addUser(ModelMap model,User user,HttpServletRequest request) {
		ValidFromVo mv = new ValidFromVo();
		if(user.getPassword()!=null&&!user.getPassword().equals("")){
			user.setStatus(BasicConstants.USER_STATUS_VALID);
			user.setOnlineStatus(BasicConstants.USER_ONLINESTATUS_ONLINE);
			user.setBindErp(BasicConstants.USER_BINDERP_NO);
			user.setCreateChannel(BasicConstants.USER_CREATECHANNEL_BIZ);
			userService.addUser(user);
			mv.setStatus("y");
		}else{
			if(getMobileCode(user.getContactMobile(),request)){
				Map codeMap = (Map)request.getSession().getAttribute(user.getContactMobile());
				if(codeMap != null){
					String code = codeMap.get("code").toString();
					if(code!=null&&!code.equals("")){
						user.setPassword(code);
						user.setStatus(BasicConstants.USER_STATUS_VALID);
						user.setOnlineStatus(BasicConstants.USER_ONLINESTATUS_ONLINE);
						user.setBindErp(BasicConstants.USER_BINDERP_NO);
						user.setCreateChannel(BasicConstants.USER_CREATECHANNEL_BIZ);
						userService.addUser(user);
						mv.setStatus("y");
					}else{
						mv.setStatus("n");
						mv.setInfo("验证码过期");
					}
				}else{
					mv.setStatus("n");
					mv.setInfo("短信发送失败");
				}
			}else{
				mv.setStatus("n");
				mv.setInfo("短信发送失败");
			}
		}
		Gson gson = new Gson();
		return gson.toJson(mv);
	}
	
	@RequestMapping(value = "/to/add/user")
	public String toAddUser(ModelMap model,HttpServletRequest request) {
		return "customers-add";
	}
	
	@RequestMapping(value = "/to/add/account")
	public String toAddAccount(ModelMap model,String id,HttpServletRequest request) {
		User user = new User();
		user.setId(Integer.parseInt(id));
		List<User> users = userService.findUserByCondition(user);
		model.put("user", users.get(0));
		return "customers-account";
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
	
	public boolean getMobileCode(String contactMobile,HttpServletRequest request){
		SendMessage sm = new YPSendMessage();
		if(sm.sendMessage(request, contactMobile)){
			return true;
		}else{
			return false;
		}
	}
}
