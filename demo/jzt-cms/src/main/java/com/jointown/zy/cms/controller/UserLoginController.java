package com.jointown.zy.cms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jointown.zy.common.vo.BossUserVo;


/**
 * 登陆
 * 
 */
@Controller(value = "userLoginController")
public class UserLoginController  extends UserBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	
	//@Resource(name = "userInfoService")
	//private UserInfoService userInfoService;
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {        
        return "index";
    }
	*/
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginForm(Model model){
		logger.info("test log");
		return "/login";
	}
	
	/*
	 * 注释内容
	 * 1.功能简单描述
	 * 2.开发者 修改者
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		userInfoService.findUserInfoByUserName("test");
		
		Subject subject = SecurityUtils.getSubject();
		
		String username = (String)subject.getPrincipal();
		PrincipalCollection principals = subject.getPrincipals();
		boolean isAuthenticated = subject.isAuthenticated();
		
		logger.info("test log");
		
		ModelAndView mav=new ModelAndView();
        String str="这是返回给freemarker页面的值";
        mav.addObject("haha", str);
        
		Map<String,String> map=new HashMap<String,String>();
        map.put("name", username);
        map.put("age", principals+"");
        map.put("address", isAuthenticated+"");
        map.put("ad", "广州");
        map.remove("ad");
        mav.addObject("maplist", map);
        
        try{
        SecurityUtils.getSubject().login(new UsernamePasswordToken("admin", "admin"));
        }catch(Exception e){
        	//qiantai xxxx
        }
        mav.setViewName("/user");
        return mav;
        
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(){ 
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();  
        return "redirect:/login";
    } 
	
	@RequestMapping("/403")
	public String unauthorizedRole(){
		return "/403";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String user(Model model){
		return "/user";
	}
	
	@RequestMapping(value="/public/{userCode}",method=RequestMethod.GET)  
    public String publicTest(@PathVariable("userCode") String userCode,ModelMap map){ 
		BossUserVo vo = new BossUserVo();
		vo.setUserCode(userCode);
		vo.setUserName("boss user name");
		map.addAttribute("user", vo);
        return "test";
    } 
	
	/**
	 * 跳转至登陆成功页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ftl/cmsHome/backend-main",method=RequestMethod.GET)
	public String welcomePage(HttpServletRequest request, HttpServletResponse response){
		logger.info("info|test log|test log|test log|test log|test log|test log");
		logger.error("error|logback");
		//这个验证是否登陆（暂时不验证）
		/*if(request.getSession().getAttribute(WebConstatVar.LOGIN_SESSION_ID)==null){
			return "/login";
		}*/
		return "cmsHome/backend-main";
	}
	
	/**
	 * 头部页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ftl/cmsHome/backend-top",method=RequestMethod.GET)
	public ModelAndView backendTop(HttpServletRequest request, HttpServletResponse response){
		logger.info("info|test log|test log|test log|test log|test log|test log");
		ModelAndView mav=new ModelAndView();
        mav.setViewName("cmsHome/backend-top");
        //验证登陆（暂时不用）
        /*SpotBackuser backendUser = (SpotBackuser) request.getSession().getAttribute(WebConstatVar.LOGIN_SESSION_ID);
        mav.addObject("userName",backendUser.getUsername());*/
		return mav;
	}
	/**
	 * 内容页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ftl/cmsHome/backend-content",method=RequestMethod.GET)
	public ModelAndView backendContent(HttpServletRequest request, HttpServletResponse response){
		logger.info("info|test log|test log|test log|test log|test log|test log");
		ModelAndView mav=new ModelAndView();
        mav.setViewName("cmsHome/backend-content");
        //验证登陆（暂时不用）
        /*SpotBackuser backendUser = (SpotBackuser) request.getSession().getAttribute(WebConstatVar.LOGIN_SESSION_ID);
        mav.addObject("userName",backendUser.getUsername());*/
		return mav;
	}
	/**
	 * 左边的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ftl/cmsHome/backend-left",method=RequestMethod.GET)
	public ModelAndView backendLeft(HttpServletRequest request, HttpServletResponse response){
		logger.info("info|test log|test log|test log|test log|test log|test log");
		ModelAndView mav=new ModelAndView();
        mav.setViewName("cmsHome/backend-left");
        //验证登陆（暂时不用）
        /*SpotBackuser backendUser = (SpotBackuser) request.getSession().getAttribute(WebConstatVar.LOGIN_SESSION_ID);
        mav.addObject("userName",backendUser.getUsername());*/
		return mav;
	}
	
}
