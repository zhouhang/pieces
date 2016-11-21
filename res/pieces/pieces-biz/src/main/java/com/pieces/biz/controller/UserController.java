package com.pieces.biz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.enums.CertifyRecordStatusEnum;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.service.CertifyRecordService;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.UserCertificationService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.tools.log.annotation.BizLog;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.biz.shiro.BizRealm;
import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.utils.CommonUtils;
import com.pieces.tools.utils.WebUtil;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@Autowired
	private BizRealm bizRealm;
	
	@Autowired
	private RedisManager redisManager;

	@Autowired
	private ShippingAddressService shippingAddressService;

	@Autowired
	CertifyRecordService certifyRecordService;

	@Autowired
	UserCertificationService userCertificationService;

	@Autowired
	HttpSession httpSession;

	/**
	 * 进入注册页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String toRegister() {
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
	public void register(User user, String mobileCode,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//后台验证
		StringBuffer message = new StringBuffer();
		Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{5,19}$");
		Matcher matcher = pattern.matcher(user.getUserName());
		if(StringUtils.isBlank(user.getUserName()) || !matcher.matches()){
			message.append("用户名错误");
		}
		if(userService.checkUserName(user.getUserName())){
			message.append("用户名重复");
		}
		if(StringUtils.isBlank(user.getPassword())){
			message.append("密码不能为空");
		}
		pattern = Pattern.compile("^([a-zA-Z]|[\u4e00-\u9fa5]){2,50}$");
		matcher = pattern.matcher(user.getContactName());
		if(!matcher.matches()){
			message.append("联系人姓名有误");
		}
		pattern = Pattern.compile("^1[345678]\\d{9}$");
		matcher = pattern.matcher(user.getContactMobile());
		if(StringUtils.isBlank(user.getContactMobile()) || !matcher.matches()){
			message.append("联系人手机错误");
		}
		if(userService.ifExistMobile(user.getContactMobile())){
			message.append("联系人手机重复");
		}
		
		if (StringUtils.isNotBlank(message.toString())) {
			Result result = new Result(false).info(message.toString());
			WebUtil.print(response, result);
			return;
		}

		//校验验证码
		String code  = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA.getValue()+user.getContactMobile());
		if (!StringUtils.isNotBlank(code)) {
			Result result = new Result(false).info("请获取验证码");
			WebUtil.print(response, result);
			return;
		}
		if (!code.equals(mobileCode)) {
			Result result = new Result(false).info("验证码错误");
			WebUtil.print(response, result);
			return;
		}
		user.setSource(BasicConstants.USER_CREATECHANNEL_BIZ);
		user.setType(1);
		userService.addUser(user);

		Result result = new Result(true);
		WebUtil.print(response, result);
	}

	/**
	 * 验证用户名
	 * 用户名
	 * @return
	 */
	@RequestMapping(value = "/checkusername")
	public void checkUserName(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		String userName = request.getParameter("userName");
		Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{5,19}$");
		Matcher matcher = pattern.matcher(userName);

		if	(!StringUtils.isNotBlank(userName) || !matcher.matches()){
			result.put("error", "用户名必须以英文字母开头，长度6到20位!");
			WebUtil.print(response, result);
			return;
		}
		
		if (userService.checkUserName(userName)) {
			result.put("error", "该用户名已被使用，请重新输入");
			WebUtil.print(response, result);
			return;
		}
		
		result.put("ok", "");
		WebUtil.print(response, result);
	}


	/**
	 * 验证手机号
	 *
	 * @return
	 */
	@RequestMapping(value = "/checkmobile")
	public void checkMobile(String contactMobile,HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();

		if (userService.ifExistMobile(contactMobile)) {
			result.put("error", "该手机号已被使用，请重新输入");
			WebUtil.print(response, result);
			return;
		}

		result.put("ok", "");
		WebUtil.print(response, result);
	}

	/**
	 * 退出系统
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		return "redirect:/user/login";
	}
	
	/**
	 * 进入登录页
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	/**
	 * 登录页登录系统
	 * @param userName
	 * @param password
	 * @param url  跳转url
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String userName, String password, String url, HttpServletRequest request,
			HttpServletResponse response) {
		// 登陆验证
		Subject subject = SecurityUtils.getSubject();
		BizToken token = new BizToken(userName, password, false, CommonUtils.getRemoteHost(request), "");
		try{
			subject.login(token);
		}catch(Exception e){
			e.printStackTrace();
			Result result = new Result(false).info("用户名密码错误");
			WebUtil.print(response, result);
			return;
		}
		// 存入用户信息到session
		if(StringUtils.isBlank(url)){
			url = WebUtils.getSavedRequest(request) != null ? WebUtils.getSavedRequest(request).getRequestUrl() : "/user/info";
		}
		User user = userService.findByUserName(token.getUsername());
		user.setPassword(null);
		user.setSalt(null);
		Session s = subject.getSession();
		s.setAttribute(RedisEnum.USER_SESSION_BIZ.getValue(), user);
		Result result = new Result(true).info(url);
		WebUtil.print(response, result);
	}

	/**
	 * 注册成功登录系统
	 * @return
	 */
	@RequestMapping(value = "/regsuccess")
	public String regSuccess(){
		return "message_register";
	}

	/**
	 * 进入修改密码页
	 * @return
	 */
	@RequestMapping(value = "/findpwd/stepone", method = RequestMethod.GET)
	@BizLog(type = LogConstant.user, desc = "修改密码页面")
	public String toFindPassword() {
		return "find_password";
	}

	/**
	 * 修改密码第一步
	 * 
	 * @param request
	 * @param response
	 * @param username
	 * @param mobile
	 * @param mobileCode
	 */
	@RequestMapping(value = "/findpwd/stepone", method = RequestMethod.POST)
	@BizLog(type = LogConstant.user, desc = "修改密码One")
	public void findPasswordOne(HttpServletRequest request, HttpServletResponse response, String username,
			String mobile, String mobileCode) {
		User user = userService.findByUserName(username);

		if (user == null) {
			Result result = new Result("10001").info("系统找不到该用户名，请确认用户名是否正确");
			WebUtil.print(response, result);
			return;
		}

		// 验证页面mobile与数据库mobile是否相同
		if (!user.getContactMobile().equals(mobile)) {
			Result result = new Result("10002").info("手机号与用户名不匹配，请重新输入");
			WebUtil.print(response, result);
			return;
		}
		
		String code  = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA.getValue()+user.getContactMobile());
		if (!StringUtils.isNotBlank(code)) {
			Result result = new Result("10003").info("请获取验证码");
			WebUtil.print(response, result);
			return;
		}

		// 验证页面验证码与session验证码是否相同
		if (!code.equals(mobileCode)) {
			Result result = new Result("10003").info("验证码错误");
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
	 * @param pwd
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/findpwd/steptwo", method = RequestMethod.POST)
	@BizLog(type = LogConstant.user, desc = "修改密码Two")
	public void findPasswordTwo(String pwd, String userName,HttpServletRequest request,HttpServletResponse response) {
		User user = userService.findByUserName(userName);
		user.setPassword(pwd);
		user.setUpdateTime(new Date());
		user = userService.createPwdAndSaltMd5(user);
		userService.updateUserByCondition(user);
		
		//清除缓存
		bizRealm.removeAuthenticationCacheInfo();
		
		Result result = new Result(true);
		WebUtil.print(response, result);
	}
	
	/**
	 * 修改密码成功
	 * @return
	 */
	@RequestMapping(value = "/findpwd/success", method = RequestMethod.GET)
	public String findpwdSuccess() {
		return "message_find_pwd";
	}

	/**
	 * 跳转用户中心
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/info")
	public String userInfo(ModelMap model, HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		//user=userService.findById(user.getId());
		model.put("user", user);
		if(user.getCertifyStatus()==(CertifyStatusEnum.NOT_CERTIFY.getValue())){
			CertifyRecordVo certifyRecordVo=certifyRecordService.getLatest(user.getId());
			if(certifyRecordVo!=null){
				model.put("cerfiy", certifyRecordVo.getStatus());
			}
			else{
				model.put("cerfiy", -1);
			}

		}
		UserCertificationVo userCertification=new UserCertificationVo();
		userCertification.setUserId(user.getId());
		model.put("userCertification",userCertificationService.findAll(userCertification));


		return "user_info";
	}
	
	/**
	 * 进入修改密码页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pwd/update" , method = RequestMethod.GET)
	public String toUserUpdatePassword(ModelMap model, HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		model.put("user", user);
		return "user_update_password";
	}
	
	/**
	 * 密码修改
	 * @param model
	 * @param pwdOld
	 * @param pwd
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pwd/update" , method = RequestMethod.POST)
	@BizLog(type = LogConstant.user, desc = "修改密码")
	public void userUpdatePassword(ModelMap model, String pwdOld, String pwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		user = userService.findById(user.getId());
		User oldUser = new User();
		BeanUtils.copyProperties(user, oldUser);
		oldUser.setPassword(pwdOld);
		
		if (!(userService.getPwdAndSaltMd5(oldUser).getPassword()).equals(user.getPassword())) {
			Result result = new Result(false).info("原始密码与用户名不匹配，请重新输入");
			WebUtil.print(response, result);
			return;
		}
		
		user.setPassword(pwd);
		user.setUpdateTime(new Date());
		user = userService.createPwdAndSaltMd5(user);
		userService.updateUserByCondition(user);
		
		//清除缓存
		bizRealm.removeAuthenticationCacheInfo();
		
		Result result = new Result(true).info("密码修改成功");
		WebUtil.print(response, result);
		return;
	}

	/**
	 * 用户收货地址管理
	 * @return
     */
	@RequestMapping(value = "/shippingaddress/index", method = RequestMethod.GET)
	public String shippingAddress(ModelMap modelMap){

		User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		List<ShippingAddressVo> adds = shippingAddressService.findByUser(user.getId());
		modelMap.put("adds", adds);
		return "user_shippingaddress";
	}

	/**
	 * 用户收货地址管理
	 * @return
	 */
	@RequestMapping(value = "/shippingaddress/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result shippingAddressDetail(@PathVariable("id")Integer id){
		ShippingAddress shippingAddress = shippingAddressService.findVoById(id);
		return new Result(true).data(shippingAddress);
	}

	/**
	 * 保存或者添加用户收货地址
	 * @return
	 */
	@RequestMapping(value = "/shippingaddress/save", method = RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "保存或者添加用户收货地址")
	public Result saveShippingAddress(@Valid ShippingAddress address){
		User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());

		List<ShippingAddressVo>  shippingAddressList = shippingAddressService.findByUser(user.getId());
		if(shippingAddressList.size()>=10){
			return new Result(false).info("收货地址不能超过10条");
		}

		shippingAddressService.saveOrUpdate(address, user);
		return new Result(true).info("保存成功!");
	}

	/**
	 * 删除收货地址
	 * @return
	 */
	@RequestMapping(value = "/shippingaddress/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "删除收货地址")
	public Result deleteShippingAddress(@PathVariable("id")Integer id){
		shippingAddressService.deleteById(id);
		return new Result(true).info("删除成功!");
	}

	/**
	 * 设置用户的默认收货地址
	 * @return
	 */
	@RequestMapping(value = "/shippingaddress/default/{id}", method = RequestMethod.GET)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "设置默认收货地址")
	public Result defaultShippingAddress(@PathVariable("id")Integer id){
		User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		shippingAddressService.settingDefaultAddress(id, user.getId());
		return new Result(true).info("默认地址设置成功!");
	}
}
