package com.jointown.zy.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.enums.UserSecurityCertResultEnum;
import com.jointown.zy.common.enums.UserSecurityOptEnums;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.service.IMemberFindPwdService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.util.StringTransferSymbolUtil;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 手机认证
 * @ClassName:AuthenticateMobileController
 * @author:Calvin.Wangh
 * @date:2015-8-25下午2:06:40
 * @version V1.0
 * @Description:
 */
@Controller
@RequestMapping(value="/authenticateMobile")
public class AuthenticateMobileController {
	
	@Autowired
	private UcUserService ucUserService;
	@Autowired
	private IMemberFindPwdService memberFindPwdService;
	@Autowired
	private RedisManager redisManager;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticateMobileController.class);
	
	/**
	 * 操作步骤
	 */
	private static final Integer STEP_ONE = 1;
	private static final Integer STEP_TWO = 2;
	private static final Integer STEP_TREE = 3;
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public UcUser getUserInfo(){
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		UcUser user = new UcUser();
		if(null!=userinfo){
			user = ucUserService.findUcUserById(Integer.valueOf(userinfo.getUserId().toString()));
		}
		return user;
	}
	
	/**
	 * 认证入口
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	public String authenticateMobileIndex(Model model){
		UcUser user = getUserInfo();
		if(null!=user){
			//是否通过邮箱接收验证码
			if(StringUtils.isNotBlank(user.getEmail()))
				model.addAttribute("receive", true);
			else
				model.addAttribute("receive", false);
			model.addAttribute("userinfo", user);
			model.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
			model.addAttribute("hideMobile", StringTransferSymbolUtil.hideString(user.getMobile(),StringTransferSymbolUtil.MOBILE));
			return "/security/re-phoneIndex";
		}
		return "redirect:http://uc.54315.com";
	}
	
	
	/**
	 * 手机验证身份
	 * @param model
	 * @param optType
	 * @return
	 */
	@RequestMapping(value = "/authenticateIdentity")
	public String authenticateIdentity(Model model,@RequestParam(value="optType", defaultValue="") int optType) {
		String page = "";
		UcUser user = getUserInfo();
		if (null != user) {
			switch (optType) {
			case 0:
				model.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
				break;
			case 1:
				model.addAttribute("optTypeTitle", UserSecurityOptEnums.SET_EMAIL.getOptTitle());
				break;
			case 2:
				model.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle());
				break;
			default:
				break;
			}
			model.addAttribute("hideMobileNo", StringTransferSymbolUtil.hideString(user.getMobile(),StringTransferSymbolUtil.MOBILE));
			model.addAttribute("user",  user);
			model.addAttribute("optType", optType);
			model.addAttribute("step", STEP_ONE);
			model.addAttribute("data",
					dataEncryption(user,
							UserSecurityOptEnums.UPDATE_MOBILE.getOptCode(),
							UserSecurityCertResultEnum.SECURITY_CERT_UNPASS.getCertCode()));
			page = "/security/re-phone";
		} else {
			// 返回登录页
			page = "redirect:http://uc.54315.com";
		}
		return page;
	}
	
	/**
	 * 验证密文数据
	 * @param request
	 * @param model
	 * @param data 密文参数
	 * @return
	 */
	@RequestMapping(value="/validataCiphertext")
	public String validataCiphertext(HttpServletRequest request,Model model,
				@RequestParam(value="data", defaultValue="") String data,
				@RequestParam(value="optType",defaultValue="")int optType){
		String page = "";
		UcUser user = getUserInfo();
		if(null!=user){
			//解密data加密信息
			Map<String,Object> param = dataDecode(data);
			//返回验证结果
			boolean result = (boolean) param.get("result");
			if(result){
				model.addAttribute("user",  user);
				model.addAttribute("optType", optType);
				switch (optType) {
				case 0://修改手机号
					model.addAttribute("step", STEP_TWO);
					model.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
					model.addAttribute("data",
							dataEncryption(user,
									UserSecurityOptEnums.UPDATE_MOBILE.getOptCode(),
									UserSecurityCertResultEnum.SECURITY_CERT_MOBILE_PASS.getCertCode()));
					//request.getSession().removeAttribute(UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
					page = "/security/u-phone";
					break;
				case 1://设置邮箱
					model.addAttribute("optTypeTitle", UserSecurityOptEnums.SET_EMAIL.getOptTitle());
					model.addAttribute("optSuccess", "设置成功");
					model.addAttribute("data",
							dataEncryption(user,
									UserSecurityOptEnums.SET_EMAIL.getOptCode(),
									UserSecurityCertResultEnum.SECURITY_CERT_EMAIL_PASS.getCertCode()));
					page = "/security/set-mail";
					break;
				case 2://修改邮箱
					model.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle());
					model.addAttribute("optSuccess", "修改成功");
					model.addAttribute("data",
							dataEncryption(user,
									UserSecurityOptEnums.UPDATE_EMAIL.getOptCode(),
									UserSecurityCertResultEnum.SECURITY_CERT_EMAIL_PASS.getCertCode()));
					page = "/security/set-mail";
					break;
				default:
					model.addAttribute("errorMsg", "请求页面不存在！");
					page="reWrong";
					break;
				}
			}else{
				model.addAttribute("errorMsg", param.get("error"));
				page="reWrong";
			}
		}else{
			page="redirect:http://uc.54315.com";
		}
		return page;
	}
	
	/**
	 * 修改手机号
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateMobileNo")
	public String updateMobileNo(HttpServletRequest request,Model model){
		String mobileNo = request.getParameter("mobileNo");
		String data = request.getParameter("data");
		UcUser ucuser = new UcUser();
		Map<String, String> param = new HashMap<String,String>();
		try {
			// 解析数据
			param = BeanUtil.jsonToMap(PayUtil.getDesDecryptData(data));
		} catch (Exception e) {
			logger.error("AuthenticateMobileController updateMobileNo data参数解析错误"+e);
			model.addAttribute("errorMsg", "参数解析错误！");
			return "reWrong";
		}
		if (null == param || param.size() == 0) {
			model.addAttribute("errorMsg", "请求参数不能为空！");
			return "reWrong";
		}
		String userId = (String) param.get("userId");
		if(StringUtils.isNotBlank(userId)){
			ucuser = ucUserService.findUcUserById(Integer.valueOf(userId));
		}else{
			model.addAttribute("errorMsg", "用户为登录！");
			return "reWrong";
		}
		ucuser.setMobile(mobileNo);
		ucuser.setUpdateTime(new Date());
		//参数表单验证
		Map<String,String> map = validataParams(request);
		String ok = map.get("result");
		model.addAttribute("optTypeTitle", UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
		if("ok".equals(ok)){
			ucUserService.updatePhoneAndEmail(ucuser);
			//清除修改手机号session
			request.getSession().removeAttribute(UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
			//清空缓存
			redisManager.del(RedisEnum.USER_SECURITY_INFO_MOBILE_OPT.getValue() + ucuser.getUserId());
			model.addAttribute("user", ucUserService.findUcUserById(Integer.valueOf(ucuser.getUserId().toString())));
			model.addAttribute("step", STEP_TREE);
			return "/security/success-phone";
		}else{
			model.addAttribute("errorMsg", "请不要重复发送请求！");
			request.getSession().removeAttribute(MobileCodeUtil.MOBILE_CODE);
			return "reWrong";
		}
	}
	
	

	
	/**
	 * 异步验证手机号是否注册
	 */
	@RequestMapping(value = "/checkMobileNo",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkMobileNo(HttpServletRequest request) {
		String mobileNo = request.getParameter("param").trim();
		List<UcUser> list= ucUserService.findUcUserByMobile(mobileNo);
		if (null != list&&0!=list.size()) {
			return "该手机号已注册,请重新输入！";
		}
		return "y";
	}
	
	
	/**
	 *	表单参数验证
	 */
	public Map<String,String> validataParams(HttpServletRequest request){
		//获取手机号
		String mobileNo = request.getParameter("mobileNo");
		//获取手机验证码
		String mobileValidateCode = request.getParameter("validateCode");
		Map<String,String> result = new HashMap<String,String>();
		//手机号正则验证
		Pattern mobileValidate = Pattern.compile("/[0-9]{11}$/");
		//手机非空验证
		if(StringUtils.isBlank(mobileNo)){
			result.put("result", "您的手机号不能为空！");
			return result;
		}
		if(mobileValidate.matcher(mobileNo).matches()){
			result.put("result", "您的手机号不符合规范！");
			return result;
		}
		if(StringUtils.isBlank(mobileValidateCode)){
			result.put("result", "您的验证码不能为空！");
			return result;
		}else{
			@SuppressWarnings("unchecked")
			Map<String, Object> mobileCodeMap = (Map<String, Object>) request.getSession().getAttribute(UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
			//验证码 验证 是否匹配/是否过期
			String verityResult = MobileCodeUtil.verityMobileCode(mobileCodeMap, mobileValidateCode,mobileNo);
			//返回验证结果
			if(!"y".equals(verityResult)){
				result.put("result", verityResult);
				return result;
			}
		}
		result.put("result", "ok");
		return result;
	}
	
	/**
	 * 验证短信验证码
	 */
	@RequestMapping(value = "/checkValidateCode",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkValidateCode(HttpServletRequest request) {
		//获取验证码
		String checkValidateCode = request.getParameter("param").trim();
		int optType = Integer.valueOf(request.getParameter("optType"));
		
		Map<String, Object> mobileCodeMap = getSessionMap(request, optType);
		String verityMsg = MobileCodeUtil.verityMobileCode(mobileCodeMap,checkValidateCode);
		if ("success".equals(verityMsg)) {//验证手机验证码
			return "y";
		}
		return verityMsg;
	}
	
	/**
	 * 获取短信验证码，短信发送
	 */
	@RequestMapping(value = "/getValidateCode")
	@ResponseBody
	public String getValidateCode(HttpServletRequest request,@RequestParam String type) {
		//手机号
		String mobileNo = "";
		//根据type获取用户手机号
		if("1".equals(type))//获取当前用户手机号
			mobileNo = getUserInfo().getMobile();
		else if("2".equals(type))//获取页面新手机号
			mobileNo =  request.getParameter("mobileNo").trim();
		int optType = Integer.valueOf(request.getParameter("optType"));
		
		if(StringUtils.isBlank(mobileNo)){
			return "n";
		}
		//获取session
		Map<String, Object> moCode = getSessionMap(request, optType);
		//2分钟内防止重复获取验证码
		if(!"ok".equals(MobileCodeUtil.checkRepeatGain(moCode, mobileNo))){
			return "error";
		}
		//获取短信验证码
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobileNo);
		//发送短信
		HttpSession session = request.getSession();
		String sendFlag = memberFindPwdService.getMobileCode(mobileNo,(String) mobileCode.get("mobileCode"));
		if ("y".equals(sendFlag)) {//y标识短信发送前提交成功,短信提交成功后，再存session
			switch (optType) {
			case 0:
				session.setAttribute(UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle(),mobileCode);
				logger.info("getMobileCode mobileCode is:" + mobileCode);
				logger.info("getMobileCode session is:" + session.getAttribute(UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle()));
				break;
			case 1:
				session.setAttribute(UserSecurityOptEnums.SET_EMAIL.getOptTitle(),mobileCode);
				logger.info("getMobileCode mobileCode is:" + mobileCode);
				logger.info("getMobileCode session is:" + session.getAttribute(UserSecurityOptEnums.SET_EMAIL.getOptTitle()));
				break;
			case 2:
				session.setAttribute(UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle(),mobileCode);
				logger.info("getMobileCode mobileCode is:" + mobileCode);
				logger.info("getMobileCode session is:" + session.getAttribute(UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle()));
				break;
			}
		}
		return sendFlag;
	}
	
	
	/**
	 * 数据加密处理 
	 * @param userinfo
	 * @param optType 操作类型
	 * @param result  验证返回结果
	 * @return
	 */
	public String dataEncryption(UcUser userinfo, int optType, int result) {
		String data = "";
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		// 传参
		param.put("userId", userinfo.getUserId());
		param.put("optType", optType);
		param.put("validateResult", result);
		try {
			data = PayUtil.getDesEncryptData(BeanUtil.mapToJson(param));
		} catch (Exception e) {
			logger.error("AuthenticateMobileController dataEncryption" + e.getMessage());
		}
		return data;
	}
	
	/**
	 * 密文解密
	 * @param data 密文参数
	 * @return
	 */
	public Map<String, Object> dataDecode(String data) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			// 解密数据
			Map<String, String> param = BeanUtil.jsonToMap(PayUtil.getDesDecryptData(data));
			String optType = param.get("optType");
			if (null == param || param.size() == 0) {
				resultMap.put("result", false);
				resultMap.put("error", "data参数为空！");
				return resultMap;
			}
			// 校验数据
			if (!param.get("validateResult").equals(String.valueOf(UserSecurityCertResultEnum.SECURITY_CERT_UNPASS.getCertCode()))) {
				resultMap.put("result", false);
				resultMap.put("error", "手机认证失败！");
				return resultMap;
			}
			resultMap.put("result", true);
			resultMap.put("param", param);
		} catch (Exception e) {
			logger.error("AuthenticateMobileController dataDecode"+ e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 分类获取session
	 * @param request
	 * @param optType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object>  getSessionMap(HttpServletRequest request,int optType){
		HttpSession session = request.getSession();
		Map<String, Object> mobileCodeMap = null;
		//session分类
		switch (optType) {
		case 0:
			mobileCodeMap = (Map<String, Object>) session.getAttribute(UserSecurityOptEnums.UPDATE_MOBILE.getOptTitle());
			break;
		case 1:
			mobileCodeMap = (Map<String, Object>) session.getAttribute(UserSecurityOptEnums.SET_EMAIL.getOptTitle());
			break;
		case 2:
			mobileCodeMap = (Map<String, Object>) session.getAttribute(UserSecurityOptEnums.UPDATE_EMAIL.getOptTitle());
			break;
		}
		return mobileCodeMap;
	}
	
	
}
