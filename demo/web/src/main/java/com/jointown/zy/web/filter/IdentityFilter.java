package com.jointown.zy.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.WebConstatVar;
import com.jointown.zy.common.model.UserInfo;
import com.jointown.zy.common.service.UserInfoService;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.web.controller.UserBaseController;

/**
 * 
 * 身份验证filter
 * 
 * @author bd17kaka
 * 
 */
public class IdentityFilter implements Filter {

    private static final Logger Log = LoggerFactory.getLogger(IdentityFilter.class);
    
    public static String KEY = "SenDCLOUd--ewrew-Key---MD5!!&&234sdflljhhg!!";
    
    public void destroy()
    {
	Log.info(" IdentityFilter destroy");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
    	UserInfoService userInfoService = (UserInfoService)SpringUtil.getBean("userInfoService");
    	
    	Log.debug("start do Filter.....");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
	
		UserInfo userInfo = (UserInfo) req.getSession().getAttribute(WebConstatVar.LOGIN_SESSION_ID);
		if (userInfo == null) {
			// 记住登录
			Cookie[] cookies = req.getCookies();
			String user_name = null;
			boolean remermerMe = false;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					if (StringUtils.equals("sendcloud_passport", name)) {
						user_name = cookie.getValue();
						for (Cookie cookie2 : cookies) {
							String name2 = cookie2.getName();
							if (StringUtils.equals(name2, "sendcloud_" + user_name)) {
								String cookieKey= cookie2.getValue();
								if (StringUtils.equals(cookieKey, EncryptUtil.getMD5(user_name+ KEY + 
										UserBaseController.getRealIp(req), "UTF-8"))) {
									remermerMe = true;
									break;
								}
							}
						}
					}
					if (remermerMe) break;
				}
			}
			if (remermerMe && user_name != null) {
				userInfo = userInfoService.findUserInfoByUserName(user_name);
				if (userInfo != null) {
					req.getSession().setAttribute(WebConstatVar.LOGIN_SESSION_ID,
							userInfo);// 设置session
				}
			}
		}
		
		// 获取请求的URL
		String url = req.getRequestURL().toString();
		String[] tokens = url.split("/");
		url = tokens[tokens.length - 1];
	
		//不需登录也能访问的url,在这里配置
		if (url.equals("index.jsp") || url.equals("reg")||url.equals("register"))	{
		    chain.doFilter(req, resp);
		    return;
		}
		
		if (url.equals("loginAjax") || url.equals("login.jsp") || url.equals("login")){
			 if (userInfo == null) {
					chain.doFilter(req, resp);
					return;
			 }
		 }
		
		if (null == userInfo) { // 用户没有登陆
			resp.sendRedirect(WebConstatVar.LOGIN_PAGE);
			return;
		}else{
			// 各种状态用户登录mailchimp的情况，如域名未验证用户，冻结用户 TODO
			chain.doFilter(req, resp);
			return;
		}
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
    	Log.info(" IdentityFilter init");
    }

}
