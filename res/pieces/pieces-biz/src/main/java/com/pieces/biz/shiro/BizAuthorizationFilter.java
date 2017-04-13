package com.pieces.biz.shiro;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.pieces.dao.model.User;
import com.pieces.service.enums.RedisEnum;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 用于验证身份信息
 *
 */
public class BizAuthorizationFilter extends AuthorizationFilter {


	@Autowired
	private WxMpService wxService;

	@Value("${biz.base.url}")
	public String baseUrl;

	private Pattern pattern = Pattern.compile("h5/",Pattern.CASE_INSENSITIVE);

	private Pattern patternH5c = Pattern.compile("h5c/",Pattern.CASE_INSENSITIVE);

	// h5/enquiry/success h5/enquiry

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		Subject subject = getSubject(request, response);
		// 先判断是否需要重新登录
		if (subject.getPrincipal() == null) {
			saveRequest(request);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String ua = httpRequest.getHeader("user-agent").toLowerCase();
			String uri = httpRequest.getRequestURI() + "?" + httpRequest.getQueryString();

			// enquiry/list enquiry/detail enquiry/update
			Matcher h5cMatcher = patternH5c.matcher(uri);
			if (h5cMatcher.find()||uri.contains("h5/enquiry/list")||uri.contains("h5/enquiry/detail")||uri.contains("h5/enquiry/update")) {
				uri = "/h5/bind?call="+uri;
			}

			Matcher matcher = pattern.matcher(uri);
			if (matcher.find()) {
				Session httpSession = subject.getSession();
				WxMpUser wxUser = (WxMpUser)httpSession.getAttribute("wxMpUser");
				if (wxUser == null) {
					String source = request.getParameter("source");
					if ("WECHAT".equals(source) || ua.indexOf("micromessenger") > 0) {
						String wechatLoginUrl = baseUrl + "/h5/login?call=" + uri;
						String OAUTH_URL = wxService.oauth2buildAuthorizationUrl(wechatLoginUrl, WxConsts.OAUTH2_SCOPE_USER_INFO, "weixin_state");
						WebUtils.issueRedirect(request, response, OAUTH_URL);
						return false;
					}
				} else {
					if (uri.contains("/h5/bind")) {
						WebUtils.issueRedirect(request, response, baseUrl+uri);
						return  false;
					} else {
						return  true;
					}

				}
			}
			WebUtils.issueRedirect(request, response, getLoginUrl());
			return false;
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}

}
