/**
 * @author lichenxiao
 * 2015年3月19日 下午15:00:00
 */
package com.jointown.zy.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.service.WxUserService;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.web.shiro.BossToken;
import com.jointown.zy.common.service.IMemberFindPwdService;

/**
 * 微信用户登录与注册
 * 
 * @author lichenxiao 2015年3月19日 下午15:04:00
 */
@Controller(value = "wxUserController")
public class WxUserController extends WxUserBaseController {
	
//	private static final Logger log = LoggerFactory.getLogger(UserBaseController.class);


	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private IMemberFindPwdService memberFindPwdService;

	@Autowired
	private UcUserService ucUserService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/login_ajax")
	public @ResponseBody MessageVo findAnalysisAJAXByPage(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		String username = request.getParameter("username");
		UcUser userByName = ucUserService.findUcUserByUserName(username);
		List<UcUser> userBymobile = new ArrayList<UcUser>();
		userBymobile = ucUserService.findUcUserByMobile(username);
		if (userByName == null) {// 如果所接收的是手机号码，并且查询出来的用户不存在，则需要查询手机号是否配
			if (userBymobile != null && userBymobile.size() > 0)
				username = userBymobile.get(0).getUserName();
		}

		String password = request.getParameter("password");
		String go = request.getParameter("go");
		MessageVo mvo = new MessageVo();
		try {
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			BossToken token = new BossToken(username, password);
			subject.login(token);
			// 存入用户信息到session
			UcUser user = ucUserService.findUcUserByUserName(username);
			UcUserVo ucUserVo = new UcUserVo();
			BeanUtils.copyProperties(user, ucUserVo);
			ucUserVo.setPassword(null);
			ucUserVo.setSalt(null);
			subject.getSession().setAttribute(
					RedisEnum.SESSION_USER_WX.getValue(), ucUserVo);// 设置session

			mvo.setOk(true);
			if (StringUtils.isNotBlank(go)) {
				mvo.setObj(go);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mvo.setOk(false).addError(new BossErrorException(e));
		}

		return mvo;
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMobileCode", method = RequestMethod.POST)
	@ResponseBody
	public String getMobileCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String _mobileNo = request.getParameter("memberMobile");// 从注册页面获取验证码
		String mobileNo=null;
		if(_mobileNo==null||_mobileNo.contains("*")){
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			String username = subject.getPrincipal().toString();
			// 存入用户信息到session
			UcUser user = ucUserService.findUcUserByUserName(username);
			mobileNo = user.getMobile();
			/**
			 * 不能使用缓存数据，如果用户不登出，此时会有问题。
			 */
			//UcUserVo uservo = getUserInfo(request); 
		}else{
			mobileNo = _mobileNo;
		}
		
		HttpSession session = request.getSession();
		Map<String, Object> moCode = (Map<String, Object>) session.getAttribute(MobileCodeUtil.MOBILE_CODE);
		Date time = new Date();
		if(moCode!=null&&moCode.get("mobileNo").equals(mobileNo)){
			Date reSendDate = (Date) moCode.get("reSendDate");
			if(reSendDate.after(time)){
				return "eorr";
			}
		}
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobileNo);
		request.getSession().setAttribute(MobileCodeUtil.MOBILE_CODE,
				mobileCode);
		return wxUserService.getMobileCode(mobileNo,
				(String) mobileCode.get("mobileCode"));
	}

	/**
	 * 验证手机号是否注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/checkMobileisExist")
	@ResponseBody
	public String checkMobileisExist(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String mobile = request.getParameter("param").trim();
		List<UcUser> list = ucUserService.findUcUserByMobile(mobile);
		if (null != list && 0 != list.size()) {
			return "该手机号已注册,请重新输入！";
		}
		return "y";
	}

	/**
	 * 验证用户名是否注册
	 * 
	 * @author zhouji
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userNameisExist")
	@ResponseBody
	public String userNameisExist(HttpServletRequest request) {
		String userName = request.getParameter("param").trim();
		if (null != ucUserService.findUcUserByUserName(userName)) {
			return "该用户名已存在,请重新输入！";
		}
		return "y";
	}

	/**
	 * 验证手机验证码
	 * 
	 * @author lichenxiao
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobileCodeisExist")
	@ResponseBody
	public String mobileCodeisExist(HttpServletRequest request) {
		String inputMobileCode = request.getParameter("param");
		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) request
				.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
		if (MobileCodeUtil.verityMobileCode(mobileCodeMap,
						inputMobileCode).equals("success")) {
			return "y";
		}
		return "验证码错误！";
	}

	/**
	 * 获取访问者IP
	 * 
	 * @author lichenxiao
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 验证输入的值
	 * 
	 * @author lichenxiao
	 * @param ucuser
	 * @param request
	 * @param response
	 * @return 返回错误代码
	 * @throws Exception
	 */
	public String validateUcUser(UcUser ucuser, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// userName正则验证
		Pattern userNameValidate = Pattern
				.compile("/[a-z*A-z*0-9*\u4E00-\u9FA5*]{5,25}/");
		if (userNameValidate.matcher(ucuser.getUserName()).matches()) {
			return "userNameValidate";
		}
		// passWord正则验证
		Pattern passWordValidate = Pattern.compile("/[a-zA-z0-9\\pP]{6,16}/");
		if (passWordValidate.matcher(ucuser.getPassword()).matches()) {
			return "passWordValidate";
		}
		// 公司/姓名正则验证
		Pattern companynameValidate = Pattern
				.compile("/[\u4E00-\u9FA5]{2,50}/");
		if (companynameValidate.matcher(ucuser.getCompanyName()).matches()) {
			return "companynameValidate";
		}
		// 电话正则验证
		Pattern mobileValidate = Pattern.compile("/[0-9]{11}$/");
		if (mobileValidate.matcher(ucuser.getMobile()).matches()) {
			return "mobileValidate";
		}
		// userName非空验证
		if (null == ucuser.getUserName() || "".equals(ucuser.getUserName())) {
			return "userNameNotNull";
		}
		// passWord非空验证
		if (null == ucuser.getPassword() || "".equals(ucuser.getPassword())) {
			return "passWordNotNull";
		}
		// 公司/姓名非空验证
		if (null == ucuser.getCompanyName()
				|| "".equals(ucuser.getCompanyName())) {
			return "companynameNotNull";
		}
		// 手机非空验证
		if (null == ucuser.getMobile() || "".equals(ucuser.getMobile())) {
			return "mobileNotNull";
		}
		// 验证手机验证码
		String inputMobileCode = request.getParameter("mobileCode");
		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) request
				.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
		if (!"y".equals(MobileCodeUtil.verityMobileCode(mobileCodeMap,
				inputMobileCode, ucuser.getMobile()))) {
			return "验证码错误！";
		}
		// userName是否注册
		if (null != ucUserService.findUcUserByUserName(ucuser.getUserName())) {
			return "该用户名已存在,请重新输入！";
		}
		// 手机是否注册
		if (ucUserService.findUcUserByMobile(ucuser.getMobile()).size() != 0) {
			return "该手机号已注册,请重新输入！";
		}
		;
		return "ok";
	}

	/**
	 * 将request中参数放入一个对象中
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public UcUser getUcUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 用户名
		String userName = request.getParameter("username");
		// 密码
		String passwd = request.getParameter("password");
		// 公司名
		String companyname = request.getParameter("companyname");
		// 电话
		String mobile = request.getParameter("mobile");
		// IP
		String accessIp = this.getIpAddr(request);
		UcUser ucuser = new UcUser();
		ucuser.setUserName(userName);
		ucuser.setPassword(passwd);
		ucuser.setCompanyName(companyname);
		ucuser.setMobile(mobile);
		ucuser.setAccessIp(accessIp);

		return ucuser;
	}

	/**
	 * 用户前台注册
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register_ajax", method = RequestMethod.POST)
	@ResponseBody
	public String ucUserRegister(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		// 通过getUser方法取到前台参数(user对象)
		UcUser ucuser = this.getUcUser(request, response);
		String msg = this.validateUcUser(ucuser, request, response);
		if (msg == "ok") {
			// 调用add方法插入数据
			ucuser.setSource(1);
			ucUserService.wxAddUcUser(ucuser);
			model.put("userName", ucuser.getUserName());
		}
		return msg;

	}

	/**
	 * @Description: 会员服务协议
	 * @Author: guoyb
	 * @Date: 2015年7月20日
	 * @return
	 */
	@RequestMapping("register_auth_service")
	public String register_auth_service() {
		return "register_auth_service";
	}

	/**
	 * @Description: 交易服务协议
	 * @Author: guoyb
	 * @Date: 2015年7月20日
	 * @return
	 */
	@RequestMapping("register_auth_busi")
	public String register_auth_busi() {
		return "register_auth_busi";
	}

}
