package com.jointown.zy.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.enums.WxAdTypeEnum;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxAdService;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.vo.WxAdVo;


/**
 * 登陆
 * 
 */
@Controller(value = "userLoginController")
public class UserLoginController extends WxUserBaseController{
	@Autowired
	private WxAdService wxAdService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	
	@RequestMapping(value={"/login"},method=RequestMethod.GET)
	public String loginForm(ModelMap model,HttpServletRequest request){
		logger.debug("12312312");
		//判断是否登陆
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
		if(user!=null){
			return "redirect:info";
		}
		
		String username = request.getParameter("username");
		
		WxAdVo wxAd = wxAdService.findWxAdsByPostionName("登录页(720*601)");
		String go = request.getParameter("go");
		model.put("go", go);
		model.put("wxAd", wxAd);
		// 广告类型
		Map<String, String> wxAdTypeMap = WxAdTypeEnum.toMap();
		model.put("wxAdTypeMap", wxAdTypeMap);
		model.put("username", username);
		return "/login";
	}
	
	@RequestMapping(value={"/registerController"},method=RequestMethod.GET)
	public String registerForm(Model model){
		return "/register";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(ModelMap model){ 
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();  
        return "redirect:/login";
    } 
	
	@RequestMapping("/403")
	public String unauthorizedRole(){
		return "/403";
	}
}
