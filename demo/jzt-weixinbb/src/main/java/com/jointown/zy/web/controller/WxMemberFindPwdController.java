/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.service.IMemberFindPwdService;
import com.jointown.zy.common.service.IMemberManageService;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.vo.MessageVo;

/**
 * @ClassName: WxMemberFindPwdController
 * @Description: 用户找回密码
 * @Author: guoyb
 * @Date: 2015年7月21日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/findBackPwd")
public class WxMemberFindPwdController extends WxUserBaseController {

	public static Log logger = LogFactory
			.getLog(WxMemberFindPwdController.class);

	@Autowired
	private IMemberFindPwdService memberFindPwdService;
	@Autowired
	private IMemberManageService memberManageService;
	@Autowired
	private RedisManager redisManager;

	/**
	 * 获取短信验证码，及短信发送(修改密码中使用)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMobileCodeXgmm")
	@ResponseBody
	public String getMobileCodeXgmm(HttpServletRequest request) {
		String mobileNo = request.getParameter("memberMobile").trim();
		String memberName = request.getParameter("memberMobName").trim();
		// 判断手机号和用户名是否一一对应
		Map<Object, Object> queryMap = new HashMap<Object, Object>();
		queryMap.put("mobile", mobileNo);
		queryMap.put("username", memberName);
		List<UcUser> list = memberFindPwdService
				.findMemberByMobileAndUsername(queryMap);
		if (null != list && list.size() > 0) {
			Map<String, Object> mobileCode = MobileCodeUtil
					.getMobileCode(mobileNo);
			request.getSession().setAttribute(MobileCodeUtil.MOBILE_CODE,
					mobileCode);
			if (mobileNo == null || "".equals(mobileNo)) {
				return "n";
			}
			return memberFindPwdService.getMobileCode(mobileNo,
					(String) mobileCode.get("mobileCode"));
		}
		return "n";
	}

	/**
	 * 获取短信验证码，及短信发送
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMobileCode")
	@ResponseBody
	public String getMobileCode(HttpServletRequest request) {
		String mobileNo = request.getParameter("memberMobile");
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
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobileNo);
		//发送短信
		String sendFlag = memberFindPwdService.getMobileCode(mobileNo,(String) mobileCode.get("mobileCode"));
		if ("y".equals(sendFlag)) {//y标识短信发送前提交成功,短信提交成功后，再存session
			session.setAttribute(MobileCodeUtil.MOBILE_CODE,mobileCode);//存session
			logger.info("getMobileCode mobileCode is:" + mobileCode);
			logger.info("getMobileCode session is:" + session.getAttribute(MobileCodeUtil.MOBILE_CODE));
			return sendFlag;
		}
		return sendFlag;
	}

	/**
	 * 手机短信验证码验证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/verityMobileCode")
	@ResponseBody
	public String verityMobileCode(HttpServletRequest request) {
		String inputMobileCode = request.getParameter("param").trim();
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) session
				.getAttribute(MobileCodeUtil.MOBILE_CODE);
		if (MobileCodeUtil.verityMobileCode(mobileCodeMap,
						inputMobileCode).equals("success")) {
			return "y";
		}
		return "验证码错误";
	}

	/**
	 * 手机找回密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobileVerity")
	public String verityMobile(HttpServletRequest request, ModelMap modelMap) {
		String memberName = request.getParameter("memberMobName");
		String inputMobileCode = request.getParameter("inputMobileCode");
		String mobile = request.getParameter("memberMobile");
		if (null == memberName || "".equals(memberName)) {
			modelMap.addAttribute("mobileMsg", "用户名不能为空");
			return "findPassword";
		}
		if (null == mobile || "".equals(mobile)) {
			modelMap.addAttribute("mobileMsg", "手机号不能为空");
			return "findPassword";
		}
		if (!mobile.equals(memberManageService.findMemberByUserName(memberName)
				.getMobile())) {
			modelMap.addAttribute("mobileMsg", "会员名与手机号不一致");
			return "findPassword";
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) request
				.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
		String verityFlag = MobileCodeUtil.verityMobileCode(mobileCodeMap,
				inputMobileCode, mobile);
		if (!"y".equals(verityFlag)) {
			modelMap.addAttribute("mobileMsg", verityFlag);
			request.getSession().removeAttribute(MobileCodeUtil.MOBILE_CODE);
			return "findPassword";
		}
		request.getSession().setAttribute("memberName", memberName);
		modelMap.addAttribute("userName", memberName);
		request.getSession().removeAttribute(MobileCodeUtil.MOBILE_CODE);
		return "modifyPwd";
	}

	/**
	 * 邮箱找回密码，发送邮件
	 * 
	 * @param request
	 * @version 1.0 1.1 by ldp 修改 邮箱找回密码方法 2015-06-12
	 * @return
	 */
	@RequestMapping(value = "/emailFindBack")
	@ResponseBody
	public MessageVo emailFindBackPwd(HttpServletRequest request,
			ModelMap modelMap) {
		String memberName = request.getParameter("memberEmailName");
		MessageVo mvo = new MessageVo();
		if (StringUtils.isEmpty(memberName)) {
			mvo.setOk(false);
			mvo.addError("error.code.01", "会员名不能为空!");
			return mvo;
		}
		String sendEmailInfo = memberFindPwdService
				.findPasswordByEmail(memberName);
		if ("success".equals(sendEmailInfo)) {
			mvo.setOk(true);
			// return "sendEmail";
			return mvo;
		} else if ("failed".equals(sendEmailInfo)) {
			mvo.setOk(false);
			mvo.addError("error.code.02", "邮件发送失败！");
			return mvo;
		}
		mvo.setOk(false);
		mvo.addError("error.code.o3", sendEmailInfo);
		return mvo;
	}

	/**
	 * @Description:邮件发送成功后，跳转邮件发送成功页面
	 * @Author: ldp
	 * @Date: 2015年6月12日
	 * @return
	 */
	@RequestMapping(value = "/sendEmail")
	public String sendEmail() {
		return "sendEmail";
	}

	/*
	 * @RequestMapping(value="/emailFindBack") public String
	 * emailFindBackPwd(HttpServletRequest request,ModelMap modelMap){ String
	 * memberName = request.getParameter("memberEmailName"); if
	 * (StringUtils.isEmpty(memberName)) { modelMap.addAttribute("sendEmail",
	 * "<script>('会员名不能为空!')</script>"); return "findPassword"; } String
	 * sendEmailInfo = memberFindPwdService.findPasswordByEmail(memberName); if
	 * ("success".equals(sendEmailInfo)){ return "sendEmail"; }else
	 * if("failed".equals(sendEmailInfo)) { modelMap.addAttribute("sendEmail",
	 * "<script>alert('邮件发送失败！');</script>"); return "findPassword"; }
	 * modelMap.addAttribute("sendEmail","<script>alert('" + sendEmailInfo +
	 * "');</script>"); return "findPassword"; }
	 */

	/**
	 * 通过发送的邮件链接找回密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/emailVerity")
	public String emailVerity(HttpServletRequest request, ModelMap modelMap) {
		String key = request.getParameter("key");
		String verityFlag = memberFindPwdService.verityEmail(key);
		if ("success".equals(verityFlag)) {
			modelMap.addAttribute("key", key);
			return "modifyPwd";
		}
		modelMap.addAttribute("errorMsg", verityFlag);
		return "modifyPwdError";
	}

	/**
	 * 找回密码时判断用户名是否存在
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/memberNameIsExist")
	@ResponseBody
	public String memberNameisExist(HttpServletRequest request) {
		String memberName = request.getParameter("param").trim();
		if (null == memberName || "".equals(memberName)) {
			return "该会员名不能为空";
		}
		if (null != memberManageService.memberNameIsHaved(memberName)) {
			return "y";
		}
		return "该会员名不存在";
	}

	/**
	 * 找回密码时判断手机号是否存在,是否匹配
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobIsExist")
	@ResponseBody
	public String memberMobIsExist(HttpServletRequest request) {
		String memberMobile = request.getParameter("param").trim();
		String memberName = request.getParameter("memberMobName").trim();
		if (StringUtils.isEmpty(memberMobile)) {
			return "手机号不能为空";
		}
		if (StringUtils.isEmpty(memberName)) {
			return "用户名不能为空";
		}
		// 判断手机号是否注册
		List<UcUser> list = memberFindPwdService.findMemberMobile(memberMobile);
		if (null == list || list.size() == 0) {
			return "手机号码未注册";
		}
		// 判断手机号和用户名是否一一对应
		Map<Object, Object> queryMap = new HashMap<Object, Object>();
		queryMap.put("mobile", memberMobile);
		queryMap.put("username", memberName);
		list = memberFindPwdService.findMemberByMobileAndUsername(queryMap);
		if (null != list && list.size() != 0) {
			return "y";
		}
		return "该手机号没有和用户名绑定";
	}

	/**
	 * 找回密码设置密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modityPwd")
	@ResponseBody
	public String modifyPwd(HttpServletRequest request) {
		String password = request.getParameter("newPassword");
		String memberName = request.getParameter("memberName");
		String key = request.getParameter("key");

		if (null != memberName && !"".equals(memberName)) {
			if (!memberName.equals(request.getSession().getAttribute(
					"memberName"))) {
				return "0";// 失败
			}
			request.getSession().removeAttribute("memberName");// 移除
		} else if (null != key && !"".equals(key)) {
			logger.info("modify pwd key is:" + key);
			String memberNameByKey = null;
			memberNameByKey = redisManager.get(key);
			if (null == memberNameByKey || "".equals(memberNameByKey)) {
				logger.info("memberNameByKey is null !");
				return "0";
			}
			memberName = memberNameByKey;
			logger.info("memberNameByKey is: " + memberNameByKey);
			redisManager.del(key);
		}
		return memberFindPwdService.modifyPwd(memberName, password);

	}

	@RequestMapping(value = "/forgetPwd")
	public String findPwd() {
		return "findPassword";
	}

}
