package com.jointown.zy.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.IMemberFindPwdService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.util.ValidationCodeUtil;

/**
 * 操作用户信息
 * 
 * @author zhouji
 *
 */
@Controller(value = "userOperateController")
public class UserOperateController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(UserOperateController.class);

	@Autowired
	private UcUserService ucUserService;
	@Autowired
	private IMemberFindPwdService memberFindPwdService;
	/**
	 * 跳转到注册页面
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUcUserRegister")
	public String getUcUserRegister(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(request.getSession().getId());
		return "/register";
	}
	/**
	 * 跳转到注册成功页面
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getRegisterSuccess")
	public String getRegisterSuccess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return "/register-success";
	}
	
	/**
	 * 用户前台注册
	 * 
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ucUserRegister", method = RequestMethod.POST)
	public String ucUserRegister(HttpServletRequest request,
			HttpServletResponse response,ModelMap model) throws Exception {
		// 通过getUser方法取到前台参数(user对象)
		UcUser ucuser = this.getUcUser(request, response);
		//added by biran 20150901 前台注册，来源为2
		ucuser.setSource(0);
		//String msg = this.validateUcUser(ucuser, request, response);
		//update by Calvin.Wh 2015.7.15 将后台校验的错误信息 返回到页面
		Map<String,String> map = this.validateUcUser(ucuser, request, response);
		String result = map.get("result");
		if("ok".equals(result)){
			// 调用add方法插入数据
			ucUserService.addUcUser(ucuser);
			model.put("userName", ucuser.getUserName());
			request.getSession().removeAttribute(MobileCodeUtil.MOBILE_CODE);
			// 返回注册成功页面
			return "/register-success";
		}else{
			request.getSession().removeAttribute(MobileCodeUtil.MOBILE_CODE);
			model.put("result", result);
			return "/error";
		}

	}
	
	/**
	 * @Description: 获取短信验证码，及短信发送
	 * @Author: ldp
	 * @Date: 2015年8月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMobileCode")
	@ResponseBody
	public String getMobileCode(HttpServletRequest request) {
		String mobileNo = request.getParameter("memberMobile").trim();
		if(mobileNo == null || "".equals(mobileNo)){
			return "n";
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
		//获取手机号、验证码及过期时间
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobileNo);
		//发送短信
		String sendFlag = memberFindPwdService.getMobileCode(mobileNo,(String) mobileCode.get("mobileCode"));
		if ("y".equals(sendFlag)) {//y标识短信发送前提交成功,短信提交成功后，再存session
			session.setAttribute(MobileCodeUtil.MOBILE_CODE,mobileCode);//存session
			log.info("getMobileCode mobileCode is:" + mobileCode);
			log.info("getMobileCode session is:" + session.getAttribute(MobileCodeUtil.MOBILE_CODE));
			return sendFlag;
		}
		return sendFlag;
	}
	

	/**
	 * 验证用户名是否注册
	 * @author zhouji
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userNameisExist")
	@ResponseBody
	public String userNameisExist(HttpServletRequest request) {
		String userName = request.getParameter("param").trim();
		//此时忽略用户状态
		if (null != ucUserService.findUcUserByUserName(userName,true)) {
			return "该用户名已存在,请重新输入！";
		}
		return "y";
	}
	/**
	 * 验证手机号是否注册
	 * @author zhouji
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobileisExist")
	@ResponseBody
	public String mobileisExist(HttpServletRequest request) {
		String mobile = request.getParameter("param").trim();
		List<UcUser> list= ucUserService.findUcUserByMobile(mobile);
		if (null != list&&0!=list.size()) {
			return "该手机号已注册,请重新输入！";
		};
		return "y";
	}
	
	/**
	 * @Description: 验证手机号码是否已注册
	 * @Author: update by ldp
	 * @Date: 2015年8月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkMobileisExist")
	@ResponseBody
	public String checkMobileisExist(HttpServletRequest request) {
		String mobile = request.getParameter("mobile").trim();
		List<UcUser> list= ucUserService.findUcUserByMobile(mobile);
		if (null != list&&0!=list.size()) {
			return "该手机号已注册,请重新输入！";
		};
		return "y";
	}
	
	/**
	 * 验证手机验证码
	 * @author zhouji
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobileCodeisExist",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String mobileCodeisExist(HttpServletRequest request) {
		String inputMobileCode = request.getParameter("param").trim();
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) session.getAttribute(MobileCodeUtil.MOBILE_CODE);
		String verityMsg = MobileCodeUtil.verityMobileCode(mobileCodeMap,inputMobileCode);
		if ("success".equals(verityMsg)) {//验证手机验证码
			return "y";
		}
		return verityMsg;
	}
	
	/**
	 * 修改用户信息
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateUcUser", method = RequestMethod.POST)
	public String updateUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 通过getUser方法取到前台参数(user对象)
		UcUser ucuser = this.getUcUser(request, response);
		//验证数据有效性
		ucUserService.updateUcUserInfo(ucuser);
		return "/login";
	}

	/**
	 * 验证用户名是否存在
	 * 返回前台js
	 * @author zhouji
	 * @param userInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "attestationUcUser", method = RequestMethod.POST)
	public Boolean attestationUser(UcUser ucuser, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UcUser user = ucUserService.findUcUserByUserName(ucuser.getUserName());
		if (null == user) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 删除用户(逻辑删除)
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteUcUser", method = RequestMethod.POST)
	public String deleteUcUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		//根据id获得用户
		UcUser ucuser = ucUserService.findUcUserById(Integer.parseInt(id));
		//删除用户
		ucUserService.deleteUcUser(ucuser);
		return "/login";
	}

	/**
	 * 将request中参数放入一个对象中
	 * 
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public UcUser getUcUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//用户名
		String userName = request.getParameter("username")==null?"":request.getParameter("username").trim();
		//密码
		String passwd = request.getParameter("password");
		//公司名
		String companyname = request.getParameter("companyname").trim();
		//电话
		String mobile = request.getParameter("mobile").trim();
		//IP
		String accessIp = this.getIpAddr(request);
		UcUser ucuser = new UcUser();
		ucuser.setUserName(userName);
		ucuser.setPassword(passwd);
		ucuser.setCompanyName(companyname);
		ucuser.setMobile(mobile);
		ucuser.setAccessIp(accessIp);

		return ucuser;
	}
	
	
	public Map<String,String> validateUcUser(UcUser ucuser,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> result = new HashMap<String,String>();
		//userName正则验证  5-25个由字母数字或者字母 
		//update by Calvin.Wh 2015.7.15 /[a-z*A-z*0-9*\u4E00-\u9FA5*]{5,25}/
		Pattern userNameValidate = Pattern.compile("/^(?!\\d+$)(?![^a-z]+$)[\\da-zA-Z]{5,25}$/");
		if(userNameValidate.matcher(ucuser.getUserName()).matches()){
			result.put("result", "您的用户名不符合规范！");
			return result;
		}
		//passWord正则验证
		Pattern passWordValidate = Pattern.compile("/[a-zA-z0-9\\pP]{6,16}/");
		if(passWordValidate.matcher(ucuser.getPassword()).matches()){
			result.put("result", "您的密码不符合规范！");
			return result;
		}
		//公司/姓名正则验证 中文 2-50个字符
		Pattern companynameValidate = Pattern.compile("/[\u4E00-\u9FA5]{2,50}/");
		if(companynameValidate.matcher(ucuser.getCompanyName()).matches()){
			result.put("result", "您的公司名/姓名不符合规范！");
			return result;
		}
		//电话正则验证
		Pattern mobileValidate = Pattern.compile("/^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/");
		if(mobileValidate.matcher(ucuser.getMobile()).matches()){
			result.put("result", "您的手机号不符合规范！");
			return result;
		}
		//userName非空验证
		if(null == ucuser.getUserName()|| "".equals(ucuser.getUserName())){
			result.put("result", "您的用户名不能为空！");
			return result;
		}
		//passWord非空验证
		if(null == ucuser.getPassword()|| "".equals(ucuser.getPassword())){
			result.put("result", "您的密码不能为空！");
			return result;
		}
		//公司/姓名非空验证
		if(null == ucuser.getCompanyName()|| "".equals(ucuser.getCompanyName())){
			result.put("result", "您的公司/姓名不能为空！");
			return result;
		}
		//手机非空验证
		if(null == ucuser.getMobile()|| "".equals(ucuser.getMobile())){
			result.put("result", "您的手机号不能为空！");
			return result;
		}
		
		/*********图片验证码校验 add by ldp 2015-07-15 start******************/
		//验证图片验证码
		String picCode = request.getParameter("picCode").trim();
		if (null == picCode || "".equals(picCode)) {
			result.put("result", "图片验证码不能为空!");
			return result;
		}
		if (!ValidationCodeUtil.isValidationCodeMatched(picCode)) {
			result.put("result", "图片验证码输入错误!");
			return result;
		}
		/*********图片验证码校验 add by ldp 2015-07-15 end******************/
		
		//验证手机验证码
		String inputMobileCode = request.getParameter("mobileCode");
		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) request
				.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
		String verityResult = MobileCodeUtil.verityMobileCode(mobileCodeMap,inputMobileCode,ucuser.getMobile());
		if (!"y".equals(verityResult)) {
			result.put("result", verityResult);
			return result;
		}
		//userName是否注册,此时忽略用户状态
		if(null != ucUserService.findUcUserByUserName(ucuser.getUserName(),true)) {
			result.put("result", "您的用户名已注册！");
			return result;
		}
		//手机是否注册
		if (ucUserService.findUcUserByMobile(ucuser.getMobile()).size()!=0) {
			result.put("result", "您的手机号已注册！");
			return result;
		}
		result.put("result", "ok");
		return result;
	}

	/**
	 * 获取访问者IP
	 * 
	 * @author zhouji
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
	 * 生成验证码
	 * @param request
	 * @param response
	 * @author ldp  2015-07-15
	 * @return
	 */
	@RequestMapping(value = "/vcode", method = RequestMethod.GET)
	public String createValidationCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ValidationCodeUtil.writeCode2Output(request, response);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}

	/**
	 * 校验验证码
	 * @param request
	 * @param response
	 * @author ldp 2015-07-15
	 * @return
	 */
	@RequestMapping(value = "/checkVode",produces = "text/html;charset=UTF-8")
	public @ResponseBody String checkVcode(
			@RequestParam(value = "param") String vcode) {
		boolean result = ValidationCodeUtil.isValidationCodeMatched(vcode);
		if (result) {
			return "y";
		}
		return "图片验证码不对，请重新填写!";
	}
	
}
