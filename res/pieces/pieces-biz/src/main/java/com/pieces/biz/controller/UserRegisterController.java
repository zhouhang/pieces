package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.utils.MobileCodeUtil;
import com.pieces.service.utils.SendMessage;
import com.pieces.service.utils.ValidationCodeUtil;
import com.pieces.service.utils.YPSendMessage;
import com.pieces.service.vo.MessageVo;

@Controller(value = "userRegisterController")
@RequestMapping
public class UserRegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/toRegister")
	public String toRegister(Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/register")
	@ResponseBody
	public String register(Model model,HttpServletRequest request) {
		//1.获取页面参数
		//2.校验手机验证码
		//3.生成盐，加密密码
		//4.保存数据库
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setCompanyFullName(request.getParameter("companyFullName"));
		user.setProvinceCode(request.getParameter("provinceCode"));
		user.setCityCode(request.getParameter("cityCode"));
		user.setCountyCode(request.getParameter("countyCode"));
		user.setContactName(request.getParameter("contactName"));
		user.setContactMobile(request.getParameter("contactMobile"));
		user.setAreaFull(request.getParameter("areaFull"));
		String mobileCode = request.getParameter("mobileCode");
		MessageVo mv = new MessageVo();
		try {
			Map codeMap = (Map)request.getSession().getAttribute(user.getContactMobile());
			if(codeMap != null){
				String code = codeMap.get("code").toString();
				if(code!=null&&!code.equals("")){
					if(code.equals(mobileCode)){
						userService.addUser(user);
						mv.setResult("ok");
					}else{
						mv.setResult("false");
						mv.setResultMessage("验证码错误");
					}
				}else{
					mv.setResult("false");
					mv.setResultMessage("验证码过期");
				}
			}else{
				mv.setResult("false");
				mv.setResultMessage("请获取验证码");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setResult("false");
			mv.setResultMessage("请求出错");
		}
		Gson gson = new Gson();
		return gson.toJson(mv);

	}
	
	@RequestMapping(value="/ifExistUserName")
	@ResponseBody
	public String ifExistUserName(Model model,String userName){
		if(userService.ifExistUserName(userName)){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequestMapping(value="/ifExistMobile")
	@ResponseBody
	public String ifExistMobile(Model model,String contactMobile){
		if(userService.ifExistMobile(contactMobile)){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequestMapping(value="/getMobileCode")
	@ResponseBody
	public String getMobileCode(Model model,String contactMobile,HttpServletRequest request){
		SendMessage sm = new YPSendMessage();
		if(sm.sendMessage(request, contactMobile)){
			return "true";
		}else{
			return "false";
		}
	}
}
