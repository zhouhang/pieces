package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.enums.UserSecurityOptEnums;
import com.jointown.zy.common.mail.SimpleMailSender;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.service.UserSecurityOptService;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.StringTransferSymbolUtil;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: UserEmailOptController
 * @Description: 会员邮箱设置、邮箱修改Controller
 * @Author: ldp
 * @Date: 2015年8月25日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/userEmailOpt")
public class UserEmailOptController extends UserBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserEmailOptController.class);
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private UserSecurityOptService userSecurityOptService;
	@Autowired
	private SimpleMailSender simpleMailSender;
	@Autowired
	private UcUserService ucUserService;
	
	/**
	 * @Description: 修改邮箱-跳转邮箱修改方式列表页面
	 * @Author: ldp
	 * @Date: 2015年8月27日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/optEmail")
	public String optEmail(HttpServletRequest request,ModelMap modelMap){
		String optType = request.getParameter("optType");//optType 邮箱操作类型 0 设置 1修改....
		UcUser ucUser = getUcUser();
		if (null == ucUser) {//判断是否登录
			return "redirect:http://uc.54315.com";
		}
		//设置邮箱
		if ("1".equals(optType)) {
			modelMap.addAttribute("optType", UserSecurityOptEnums.SET_EMAIL.getOptCode());
			modelMap.addAttribute("mobile", ucUser.getMobile());
			return "security/re-phone";
		}
		//邮箱修改
		modelMap.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle());
		modelMap.addAttribute("hideMobile", StringTransferSymbolUtil.hideString(ucUser.getMobile(),StringTransferSymbolUtil.MOBILE));
		modelMap.addAttribute("user", ucUser);
		return "security/re-mailIndex";//修改邮箱页面
	}
	
	/**
	 * @Description: 发邮件的方式修改手机号码,发送邮件
	 * @Author: ldp
	 * @Date: 2015年8月28日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/modMobileEmAuth")
	@ResponseBody
	public String modMobileSendEmail(HttpServletRequest request){
		UcUser ucUser = getUcUser();
		JsonObject json = new JsonObject();
		if (null == ucUser) {//判断是否登录
			json.addProperty("status", "error01");
			json.addProperty("errorMsg", "用户未登录!");
			return json.toString();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String data = "";
		try {
			map.put("resourceUrl", MessageConstant.RESOURCE_IMG);
			map.put("ueUrl", "http://uc.54315.com/userEmailOpt/emailAuth?ea");
			data = userSecurityOptService.getModMobileEmContext(ucUser);
			map.put("emailActive", data);
		} catch (Exception e) {
			logger.error("UserEmailOptController.sendEmail error:",e);
		}
		//发送邮件
		simpleMailSender.setToEmail(ucUser.getEmail());
		simpleMailSender.setTemplateName("emailUpdateMobile.ftl");
		simpleMailSender.setSubject(GetEmailContext.MOBILE_UPDATE_SUBJECT);
		simpleMailSender.setMap(map);
		taskExecutor.execute(simpleMailSender);
		json.addProperty("status", "yes");
		json.addProperty("toEmail", ucUser.getEmail());
		json.addProperty("data", data);
		return json.toString();
	}
	
	/**
	 * @Description: 发邮件的方式修改手机号码,验证邮箱
	 * @Author: ldp
	 * @Date: 2015年8月28日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/emailAuth")
	public String modMobByEmailAuth(HttpServletRequest request,ModelMap modelMap){
		String mmveContext = request.getParameter("ea");//获取邮件内容
		MessageVo mvo = new MessageVo();
		String forward = null;
		//验证邮箱
		try {
			mvo = userSecurityOptService.emailAuth(mmveContext);
			if (mvo.isOk()) {
				modelMap.addAttribute("optType", mvo.getObj());
				modelMap.addAttribute("step", 2);
				modelMap.addAttribute("data", mmveContext);
				forward = "security/u-phone";
			}else {
				modelMap.addAttribute("errorMsg", mvo.getErrorMessages().get(0).getMessage());
				forward = "reWrong";
			}
		} catch (Exception e) {
			logger.error("UserEmailOptController.modMobByEmailAuth error:", e);
			modelMap.addAttribute("errorMsg", e.getMessage());
			forward = "reWrong";
		}
		return forward;
	}
	
	/**
	 * @Description: 判断会员邮箱是否存在
	 * @Author: ldp
	 * @Date: 2015年8月25日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/emailIsExist",produces="text/html;charset=utf-8")
	public @ResponseBody String userEmailIsExist(HttpServletRequest request){
		String email = request.getParameter("param").trim();
		logger.info("UserEmailOptController.userEmailIsExist email:" + email);
		MessageVo mVo = new MessageVo();
		try {
			mVo = userSecurityOptService.emailIsExist(email);
			if (mVo.isOk()) {
				return "y";
			}
		} catch (Exception e) {
			logger.error("UserEmailOptController.userEmailIsExist error:", e);
			mVo.addError("error04", e.getMessage());
		}
		return mVo.getErrorMessages().get(0).getMessage();
	}
	
	
	/**
	 * @Description: 邮箱设置、修改，发送邮件
	 * @Author: ldp
	 * @Date: 2015年8月25日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sendEmail")
	public @ResponseBody String sendEmail(HttpServletRequest request,ModelMap modelMap){
		String toEmail = request.getParameter("newEmail");
		String data = request.getParameter("data");//获取加密数据
		JsonObject json = new JsonObject();
		
		MessageVo mvo = new MessageVo();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UcUser ucUser = getUcUser();
			if (null == ucUser) {
				logger.info("UserEmailOptController.sendEmail user unlogin");
				json.addProperty("status", "error01");
				json.addProperty("errorMsg", "用户未登录!");
				return json.toString();
			}
			//身份认证数据校验，校验通过之后发送邮件
			mvo = userSecurityOptService.validateEmail(data);
			if (!mvo.isOk()) {
				logger.info("UserEmailOptController.sendEmail email validate failed");
				json.addProperty("status", "error02");
				json.addProperty("errorMsg", mvo.getErrorMessages().get(0).getMessage());
				return json.toString();
			}
			//判断新邮箱是否已经存在
			MessageVo messageVo = userSecurityOptService.emailIsExist(toEmail);
			if (!messageVo.isOk()) {
				logger.info("UserEmailOptController.sendEmail email exist");
				json.addProperty("status", "error03");
				json.addProperty("errorMsg", messageVo.getErrorMessages().get(0).getMessage());
				return json.toString();
			}
			//邮件模板内容
			map.put("resourceUrl", MessageConstant.RESOURCE_IMG);
			if (String.valueOf(UserSecurityOptEnums.SET_EMAIL.getOptCode()).equals((String)mvo.getObj())) {
				map.put("optTitle", "设置");
				simpleMailSender.setSubject(GetEmailContext.EMAIL_SET_SUBJECT);//邮件主题
			}else if(String.valueOf(UserSecurityOptEnums.UPDATE_EMAIL.getOptCode()).equals((String)mvo.getObj())) {
				map.put("optTitle", "修改");
				simpleMailSender.setSubject(GetEmailContext.EMAIL_UPDATE_SUBJECT);
			}
			map.put("ueUrl", "http://uc.54315.com/userEmailOpt/activeEm?ue");
			map.put("emailActive", userSecurityOptService.getSetEmailContext(toEmail,(String)mvo.getObj(), getUcUser()));
			
		} catch (Exception e) {
			logger.error("UserEmailOptController.sendEmail error:",e);
		}
		//发送邮件
		simpleMailSender.setToEmail(toEmail);
		simpleMailSender.setTemplateName("mailActive.ftl");
		simpleMailSender.setMap(map);
		taskExecutor.execute(simpleMailSender);
		json.addProperty("status", "yes");
		json.addProperty("toEmail", toEmail);
		json.addProperty("dt", data);
		json.addProperty("optType", (String)mvo.getObj());
		return json.toString();
	}
	
	/**
	 * @Description: 跳转邮件发送成功页面
	 * @Author: ldp
	 * @Date: 2015年8月27日
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/emailSended")
	public String emailSended(HttpServletRequest request,ModelMap modelMap){
		String toEmail = request.getParameter("toEmail");
		String optType = request.getParameter("optType");
		String data = request.getParameter("data");
		if(String.valueOf(UserSecurityOptEnums.UPDATE_MOBILE.getOptCode()).equals(optType))
			modelMap.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
		else if(String.valueOf(UserSecurityOptEnums.SET_EMAIL.getOptCode()).equals(optType))
			modelMap.addAttribute("optTypeTitle", UserSecurityOptEnums.SET_EMAIL.getOptTitle());
		else if(String.valueOf(UserSecurityOptEnums.UPDATE_EMAIL.getOptCode()).equals(optType))
			modelMap.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle());
		
		modelMap.addAttribute("toEmail", toEmail);
		modelMap.addAttribute("data", data);
		modelMap.addAttribute("optType", optType);
		return "security/emailSended";
	}

	/**
	 * @Description: 邮箱验证激活
	 * @Author: ldp
	 * @Date: 2015年8月27日
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/activeEm")
	public String emailVerity(HttpServletRequest request,ModelMap modelMap){
		//获取数据
		String emailContext = request.getParameter("ue");
		MessageVo mvo = new MessageVo();
		String forward = null;
		try {
			mvo = userSecurityOptService.activeEmail(emailContext);
			if (mvo.isOk()) {
				Map<String, String> returnMap = new HashMap<String, String>();
				returnMap = (Map<String, String>) mvo.getObj();
				modelMap.addAttribute("email", returnMap.get("email"));
				String optType = returnMap.get("optType");
				modelMap.addAttribute("optType",optType);
				forward = "security/set-mailSuccess";
			}else {
				modelMap.addAttribute("errorMsg", mvo.getErrorMessages().get(0).getMessage());
				forward = "reWrong";
			}
		} catch (Exception e) {
			logger.error("UserEmailOptController.emailVerity error:", e);
			modelMap.addAttribute("errorMsg", e.getMessage());
			forward = "reWrong";
		}
		return forward;
	}
	
	/**
	 * @Description: 获取用户信息-UcUser
	 * @Author: ldp
	 * @Date: 2015年8月26日
	 * @return
	 */
	public UcUser getUcUser(){
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		UcUser ucUser = null;
		if(null != ucUserVo){
			ucUser = ucUserService.findUcUserById(Integer.valueOf(ucUserVo.getUserId().toString()));
		}
		return ucUser;
	}
}
