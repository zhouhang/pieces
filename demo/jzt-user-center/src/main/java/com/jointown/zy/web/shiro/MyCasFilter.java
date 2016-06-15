package com.jointown.zy.web.shiro;


import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.vo.UcUserVo;


public class MyCasFilter
extends AuthenticatingFilter
{
	private static Logger logger = LoggerFactory
			.getLogger(MyCasFilter.class);
	
	private static final String TICKET_PARAMETER = "ticket";
	
	private String failureUrl;
	
	@Autowired
	private UcUserService userService;

	
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response)
	throws Exception
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String ticket = httpRequest.getParameter("ticket");
		logger.debug("#######createToken:" + ticket + "#######");
		return new CasToken(ticket);
		}

	
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response)
	throws Exception
	{
		logger.debug("#######onAccessDenied:" + request + "#######");
		logger.info("#######onAccessDenied:" + request + "#######");
		return executeLogin(request, response);
		}

	
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue)
	{
		logger
				.info("#######isAccessAllowed:" + request + "#######");
		return false;
		}

	
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
	throws Exception
	{
		logger.info("#######onLoginSuccess:" + getSuccessUrl()
				+ "#######");
		issueSuccessRedirect(request, response);
		return false;
		}

	
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException ae, ServletRequest request,
			ServletResponse response)
	{
		logger.debug("#######onLoginFailure:" + ae + "#######");
		logger.debug("#######failureUrl:" + this.failureUrl
				+ "#######");
		
		Subject subject = getSubject(request, response);
		if ((subject.isAuthenticated())
				|| (subject.isRemembered())) {
			try {
				issueSuccessRedirect(request, response);
				} catch (Exception e) {
				logger.error(
						"Cannot redirect to the default success url", e);
				}
			} else {
			try {
				WebUtils.issueRedirect(request, response,
						this.failureUrl);
				} catch (IOException e) {
				logger.error(
						"Cannot redirect to failure url : {}", this.failureUrl,
						e);
				}
			}
		return false;
		}

	
	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
		}
	
}

