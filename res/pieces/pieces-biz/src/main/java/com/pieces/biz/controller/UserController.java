package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.WebUtil;

/**
 * 用户控制器 包括用户注册，用户登录和用户中心
 * 
 * @author feng
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	/**
	 * 进入注册页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String toRegister(Model model) {
		return "register";
	}

	/**
	 * 用户注册信息保存
	 * 
	 * @param model
	 * @param user
	 *            用户参数
	 * @param mobileCode
	 *            页面输入的手机验证码
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(Model model, User user, String mobileCode,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (StringUtils.isNotBlank(userService.valid(user))) {
			Result result = new Result(false);
			WebUtil.print(response, result);
			return;
		}

		Map<String, Object> codeMap = (Map<String, Object>) request.getSession().getAttribute(user.getContactMobile());

		// codeMap为空判断
		if (ValidUtils.mapBlank(codeMap)) {
			Result result = new Result(false).info("请获取验证码");
			WebUtil.print(response, result);
			return;
		}
		String code = codeMap.get("code").toString();

		// 验证码为空
		if (!StringUtils.isNotBlank(code)) {
			Result result = new Result(false).info("验证码过期");
			WebUtil.print(response, result);
			return;
		}

		// 判断输入验证码与session验证码是否相同
		if (code.equals(mobileCode)) {
			Result result = new Result(false).info("验证码错误");
			WebUtil.print(response, result);
			return;
		}

		userService.addUser(user);
		Result result = new Result(true);
		WebUtil.print(response, result);
	}

	/**
	 * 验证用户名
	 * 
	 * @param model
	 * @param userName
	 *            用户名
	 * @return
	 */
	@RequestMapping(value = "/checkusername")
	public void checkUserName(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result(false);
		String userName = request.getParameter("param");
		if (StringUtils.isNotBlank(userName)) {
			Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{5,19}$");
			Matcher matcher = pattern.matcher(userName);
			if (matcher.matches() && !userService.checkUserName(userName)) {
				result = new Result(true);
			}
		}
		WebUtil.print(response, result);
	}

	/**
	 * 退出系统
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(ModelMap model) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
	
	/**
	 * 进入登录页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(Model model, HttpServletRequest request) {
		return "login";
	}

	/**
	 * 登录页登录系统
	 * 
	 * @param model
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(Model model, String userName, String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 登陆验证
		Subject subject = SecurityUtils.getSubject();
		BizToken token = new BizToken(userName, password, false, CommonUtils.getRemoteHost(request), "");
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
		Result result = new Result(true);
		WebUtil.print(response, result);
	}

	/**
	 * 注册成功登录系统
	 * 
	 * @param model
	 * @param userName
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regsuccess")
	public String registerLogin(ModelMap model, String userName, String password, HttpServletRequest request)
			throws Exception {
		// 登陆验证
		Subject subject = SecurityUtils.getSubject();
		BizToken token = new BizToken(userName, password, false, CommonUtils.getRemoteHost(request), "");
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
	}

	/**
	 * 进入修改密码页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findpwd/stepone", method = RequestMethod.GET)
	public String toFindPassword(Model model, HttpServletRequest request) {
		return "find_password";
	}

	/**
	 * 修改密码第一步
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param mobile
	 * @param mobileCode
	 */
	@RequestMapping(value = "/findpwd/stepone", method = RequestMethod.POST)
	public void findPasswordOne(HttpServletRequest request, HttpServletResponse response, Model model, String username,
			String mobile, String mobileCode) {
		Map codeMap = (Map) request.getSession().getAttribute(mobile);
		User user = new User();
		user.setUserName(username);
		List<User> users = userService.findUserByCondition(user);

		if (ValidUtils.listBlank(users)) {
			Result result = new Result(false).info("用户名不存在");
			WebUtil.print(response, result);
			return;
		}

		// 验证页面mobile与数据库mobile是否相同
		if (!users.get(0).getContactMobile().equals(mobile)) {
			Result result = new Result(false).info("手机号码错误");
			WebUtil.print(response, result);
			return;
		}

		if (ValidUtils.mapBlank(codeMap)) {
			Result result = new Result(false).info("请获取验证码");
			WebUtil.print(response, result);
			return;
		}

		String code = codeMap.get("code").toString();
		if (StringUtils.isBlank(code)) {
			Result result = new Result(false).info("验证码过期");
			WebUtil.print(response, result);
			return;
		}

		// 验证页面验证码与session验证码是否相同
		if (!code.equals(mobileCode)) {
			Result result = new Result(false).info("验证码错误");
			WebUtil.print(response, result);
			return;
		}

		Result result = new Result(true);
		WebUtil.print(response, result);
	}

	/**
	 * 进入修改密码第二步
	 * 
	 * @param model
	 * @param request
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/findpwd/steptwo", method = RequestMethod.GET)
	public String toFindPasswordTwo(ModelMap model, HttpServletRequest request, String userName) {
		model.put("userName", userName);
		return "find_password_2";
	}

	/**
	 * 修改密码第二步
	 * 
	 * @param model
	 * @param pwd
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/findpwd/steptwo", method = RequestMethod.POST)
	public String findPasswordTwo(Model model, String pwd, String userName) throws Exception {
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
	}

	@RequestMapping(value = "/info")
	public String userInfo(ModelMap model, HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		model.put("user", user);
		return "user_info";
	}

	@RequestMapping(value = "/pwd/update" , method = RequestMethod.GET)
	public String toUserUpdatePassword(ModelMap model, HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		model.put("user", user);
		return "user_update_password";
	}

	@RequestMapping(value = "/pwd/update" , method = RequestMethod.POST)
	public void userUpdatePassword(ModelMap model, String pwdOld, String pwd, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		User oldUser = new User();
		BeanUtils.copyProperties(user, oldUser);
		oldUser.setPassword(pwdOld);
		if (!userService.getPawAndSaltMd5(oldUser).getPassword().equals(user.getPassword())) {
			Result result = new Result(false).info("原密码有误");
			WebUtil.print(response, result);
			return;
		}
		
		user.setPassword(pwd);
		user.setUpdateTime(new Date());
		user = userService.creatPawAndSaltMd5(user);
		userService.updateUserByCondition(user);
		Result result = new Result(true);
		WebUtil.print(response, result);
		return;
	}
}
