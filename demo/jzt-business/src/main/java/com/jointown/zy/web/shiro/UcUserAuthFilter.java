package com.jointown.zy.web.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.jointown.zy.common.constant.WebConstatVar;
import com.jointown.zy.common.enums.JsLoginStatusEnum;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.JsLoginMessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: UcUserAuthFilter
 * @Description:
 * @Author: guoyb
 * @Date: 2015年6月5日
 * @Version: 1.0
 */
public class UcUserAuthFilter extends AuthorizationFilter {

	@Autowired
	private UcUserService userService;

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(
				RedisEnum.SESSION_USER_UC.getValue());
		Integer certifyStatus = user.getCertifyStatus();
		// 增加校验，如果没有进行过认证，重定向到认证的URL,0未认证1已个人认证2已企业认证
		if (certifyStatus == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 在已登录的情况下，判断用户是否已认证
	 * 
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws IOException {
		if (!isAjax(request)) {
			WebUtils.issueRedirect(request, response,
					WebConstatVar.BUSI_AUTHENTICATION_URL);
		} else {
			PrintWriter out = response.getWriter();
			JsLoginMessageVo vo = new JsLoginMessageVo();
			vo.setLoginStatus(JsLoginStatusEnum.UNANTHENTICATION.getStatus());
			vo.setNext(WebConstatVar.BUSI_AUTHENTICATION_URL);
			Gson gson = GsonFactory.createGson();
			String message = gson.toJson(vo);
			out.print(message);
			System.out.println(message);
			return false;
		}
		return false;
	}
	
	/**
	 * @Description: 判断一个请求是否是ajax请求
	 * @Author: guoyb
	 * @Date: 2015年6月8日
	 * @param request 
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		String ua = httpServletRequest.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(ua);
	}
}
