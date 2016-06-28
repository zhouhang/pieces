package com.jointown.zy.web.controller.boss;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
/**
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.LoginDto;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.ValidationCodeUtil;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.web.controller.UserLoginController;
import com.jointown.zy.web.controller.WxUserBaseController;
import com.jointown.zy.web.shiro.BossToken;


/**
 * 微信管理系统登陆与注册
 * @author lichenxiao
 * 2015年7月25日
 * 
 */
@Controller(value = "wxBossUserLoginController")
@RequestMapping(value="/WxBoss")
public class WxBossUserLoginController extends WxUserBaseController{
	private static final Logger logger = LoggerFactory
			.getLogger(UserLoginController.class);

	@Autowired
	private BossUserService bossUserService;

	//@Autowired
	//private BossPermissionService permissionService;

	@Autowired
	private UcUserService ucUserService;
	
	@RequestMapping(value ="/wxSystem")
	public String loginForm() {
		return "/boss/admin-login";
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param responseb
	 * @return
	 */
	@RequestMapping(value = {"/vcode"}, method = RequestMethod.GET)
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
	@RequestMapping(value = "/vcodes", method = RequestMethod.GET)
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
	@RequestMapping(value = "/wx_login_ajax", method = RequestMethod.POST)
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
					.setAttribute(RedisEnum.SESSION_USER_BOSS_WX.getValue(), boss);
			//查询菜单权限信息
	    	//List<BossPermission> bplist = permissionService.findBossPermissionByUserCode(loginForm.getUsername());
	    	//SecurityUtils.getSubject().getSession()
			//.setAttribute(RedisEnum.SESSION_USER_BOSS_RULE.getValue(), bplist);
			mvo.setOk(true);
		} catch (Exception e) {
			// 异常处理
			mvo.setOk(false).addError(new BossErrorException(e));
		}
		return mvo;
	}

	@RequestMapping(value = "/wxLogout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		return "redirect:/WxBoss/wxSystem";
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
	

	
}
