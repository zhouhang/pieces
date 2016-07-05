package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.pieces.service.AreaService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.SendMessage;
import com.pieces.service.utils.YPSendMessage;
import com.pieces.service.vo.MessageVo;
import com.pieces.tools.utils.WebUtil;

/**
 * 用户控制器
 * 包括用户注册，用户登录和用户中心
 * @author feng
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
    private AreaService areaService;
	@Autowired
	private UserService userService;
	
	/**
	 * 进入注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String toRegister(Model model) {
		return "register";
	}
	
	/**
	 * 用户注册信息保存
	 * @param model
	 * @param user 用户参数
	 * @param mobileCode 页面输入的手机验证码
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register/save")
	public void register(Model model,
							User user,
							String mobileCode,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
//		User user = new User();
//		user.setUserName(request.getParameter("userName"));
//		user.setPassword(request.getParameter("password"));
//		user.setCompanyFullName(request.getParameter("companyFullName"));
//		user.setProvinceCode(request.getParameter("provinceCode"));
//		user.setCityCode(request.getParameter("cityCode"));
//		user.setCountyCode(request.getParameter("countyCode"));
//		user.setContactName(request.getParameter("contactName"));
//		user.setContactMobile(request.getParameter("contactMobile"));
//		user.setAreaFull(request.getParameter("areaFull"));
//		String mobileCode = request.getParameter("mobileCode");
		//校验页面参数
		
		
		//session中获取codeMap
		Map codeMap = (Map)request.getSession().getAttribute(user.getContactMobile());
		//codeMap为空判断
		if(codeMap == null){
	        Result result =  new Result(false).info("请获取验证码");
	        WebUtil.print(response,result);
		}
		//获取code验证码
		String code = codeMap.get("code").toString();
		//验证码为空
		if(code == null){
	        Result result =  new Result(false).info("验证码过期");
	        WebUtil.print(response,result);
		}
		//判断输入验证码与session验证码是否相同
		if(code.equals(mobileCode)){
			userService.addUser(user);
	        Result result =  new Result(true);
	        WebUtil.print(response,result);
		}else{
	        Result result =  new Result(false).info("验证码错误");
	        WebUtil.print(response,result);
		}
	}
	
	/**
	 * 验证用户名
	 * @param model
	 * @param userName 用户名
	 * @return 
	 */
	@RequestMapping(value="/check")
	@ResponseBody
	public boolean ifExistUserName(Model model,String userName,String contactMobile){
		if(StringUtils.isNotBlank(userName) && StringUtils.isBlank(contactMobile)){
			return (!userService.ifExistUserName(userName));
		}
		if(StringUtils.isNotBlank(contactMobile) && StringUtils.isBlank(userName)){
			return (!userService.ifExistMobile(contactMobile));
		}
		return false;
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
			s.setAttribute(RedisEnum.USER_SESSION_BIZ.getValue(), tu);
			mv.setResult("ok");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setResult("false");
		}
		return gson.toJson(mv);
	}
	
	@RequestMapping(value = "/registerLogin")
	public String registerLogin(ModelMap model,String userName,String password,HttpServletRequest request) {
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
			s.setAttribute(RedisEnum.USER_SESSION_BIZ.getValue(), tu);
			model.put("user", tu);
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
			updateUser = userService.createPwdAndSaltMd5(updateUser);
			userService.updateUserByCondition(updateUser);
			return "message_find_pwd";
		} catch (Exception e) {
			e.printStackTrace();
			return "message_find_pwd";
		}
	}
	
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
