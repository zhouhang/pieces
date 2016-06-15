package com.jointown.zy.web.controller.boss;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.IMemberFindPwdService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.service.WxUserService;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.web.controller.WxUserBaseController;

@Controller(value = "WxBossUserController")
@RequestMapping(value="/WxBoss")
public class WxBossUserController extends WxUserBaseController {
	
	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private IMemberFindPwdService memberFindPwdService;
	
	@Autowired
	private UcUserService ucUserService;
	

	
	/**
	 * 获取手机验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMobileCode", method = RequestMethod.POST)
	@ResponseBody
	public String getMobileCode(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		String mobileNo = request.getParameter("memberMobile");//从注册页面获取验证码
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobileNo);
		request.getSession().setAttribute(MobileCodeUtil.MOBILE_CODE,
				mobileCode);
		return wxUserService.getMobileCode(mobileNo,
				(String) mobileCode.get("mobileCode"));
	}
	
	
	/**
	 * 验证手机验证码
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
		if (MobileCodeUtil.verityMobileCode(mobileCodeMap, inputMobileCode) == "y"
				&& MobileCodeUtil.verityMobileCode(mobileCodeMap,
						inputMobileCode).equals("y")) {
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
		//用户名
		String userName = request.getParameter("username");
		//密码
		String passwd = request.getParameter("password");
		//公司名
		String companyname = request.getParameter("companyname");
		//电话
		String mobile = request.getParameter("mobile");
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
}
