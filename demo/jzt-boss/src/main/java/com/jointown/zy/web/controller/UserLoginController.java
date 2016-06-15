package com.jointown.zy.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.LoginDto;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BossPermissionService;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.ValidationCodeUtil;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.web.shiro.BossToken;

/**
 * 登陆
 * 
 */
@Controller(value = "userLoginController")
public class UserLoginController extends UserBaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserLoginController.class);

	@Autowired
	private BossUserService bossUserService;

	@Autowired
	private BossPermissionService permissionService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "/login";
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/vcode", method = RequestMethod.GET)
	public String createValidationCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ValidationCodeUtil.writeCode2Output(request, response);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}

	/**
	 * 校验验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/vcode", method = RequestMethod.POST)
	public @ResponseBody MessageVo checkVcode(
			@RequestParam(value = "vcode") String vcode) {
		boolean result = ValidationCodeUtil.isValidationCodeMatched(vcode);
		MessageVo vo = new MessageVo();
		vo.setOk(result);
		return vo;
	}

	/**
	 * 登录
	 * 
	 * @param loginForm
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody MessageVo login(@ModelAttribute LoginDto loginForm)
			throws ServletException, IOException {
		MessageVo mvo = new MessageVo();
		// 页面数据验证逻辑
		if (!CollectionUtils.isEmpty(loginForm.validate())) {
			return mvo.setOk(false).setErrorMessages(loginForm.getErrors());
		}
		try {
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			BossToken token = new BossToken(loginForm.getUsername(),
					loginForm.getPassword(), loginForm.isRememberMe(), subject
							.getSession().getHost(), loginForm.getVcode());
			subject.login(token);
			// 存入用户信息到session
			BossUser user = bossUserService.findBossUserByUserCode(token
					.getUsername());
			BossUserVo boss = new BossUserVo();
			BeanUtils.copyProperties(user, boss);
			boss.setId(user.getUserId());
			boss.setPassword(null);
			boss.setSalt(null);
			SecurityUtils.getSubject().getSession()
					.setAttribute(RedisEnum.SESSION_USER_BOSS.getValue(), boss);
			//查询菜单权限信息
	    	List<BossPermission> bplist = permissionService.findBossPermissionByUserCode(loginForm.getUsername());
	    	SecurityUtils.getSubject().getSession()
			.setAttribute(RedisEnum.SESSION_USER_BOSS_RULE.getValue(), bplist);
			mvo.setOk(true);
		} catch (Exception e) {
			// 异常处理
			mvo.setOk(false).addError(new BossErrorException(e));
		}
		return mvo;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "/403";
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "home/main";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model) {
		return "/user";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "/hello";
	}
	
	
//	@RequestMapping("/test")
//	public String test() {
//		return "/test";
//	}
//	
//	@RequestMapping("/test/checkPasswor")
//	@ResponseBody
//	public String testCheckPasswor(HttpServletRequest request, HttpServletResponse response) {
//		Subject subject = SecurityUtils.getSubject();
//		String userName = (String) subject.getPrincipal();
//		JsonObject j = new JsonObject();
//		String json =null;
//		Gson gson = new Gson();
//		// 加以下语句：页面中文没有乱码
//		response.setCharacterEncoding("utf-8");
//		if(userName==null||"".equals(userName)){
//			j.addProperty("status", "user_null");
//			return gson.toJson(j);
//		}
//		if(!"admin".equals(userName)){
//			j.addProperty("status", "user_wrong");
//			return gson.toJson(j);
//		}
//		String password = request.getParameter("password");
//		if(password==null||password.equals("")){
//			j.addProperty("status", "no");
//			return gson.toJson(j);
//		}
//		try{
//			String encodePassword=EncryptUtil.getMD5(password, "UTF-8").substring(10,20);
//			String right="EE5B2AEFCB";
//			if(right.equals(encodePassword)){
//				j.addProperty("status", "yes");
//			}else{
//				j.addProperty("status", "no");
//			}
//			
//		} catch (Exception e) {
//			j.addProperty("status", "no");
//			e.printStackTrace();
//		} finally{
//			
//			json = gson.toJson(j);
//			
//		}
//		return json;
//	}
//	
//	
//	@RequestMapping("/test/update")
//	@ResponseBody
//	public String updateSql(HttpServletRequest request, HttpServletResponse response) {
//
//		JsonObject j = new JsonObject();
//		String json =null;
//		Gson gson = new Gson();
//		// 加以下语句：页面中文没有乱码
//		response.setCharacterEncoding("utf-8");
//		String sql = request.getParameter("sql");
//		if(sql==null||sql.equals("")){
//			j.addProperty("status", "sql_null");
//			return gson.toJson(j);
//		}
//		String result=null;
//		try{
//			
//			result=permissionService.updateBossPermissionUnusual(sql);
//			
//		} catch (Exception e) {
//			j.addProperty("status", "no");
//			e.printStackTrace();
//		}
//		if("success".equals(result)){
//			j.addProperty("status", "yes");
//			json = gson.toJson(j);
//		}else{
//			j.addProperty("status", "执行不成功！"+result);
//			json = gson.toJson(j);
//		}
//		
//			
//
//		return json;
//	}

}
