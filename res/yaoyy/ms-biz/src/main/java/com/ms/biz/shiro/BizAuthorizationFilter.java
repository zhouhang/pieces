package com.ms.biz.shiro;

import com.ms.biz.controller.WechatController;
import com.ms.tools.utils.HttpUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于验证身份信息
 *
 */
public class BizAuthorizationFilter extends AuthorizationFilter {



	@Value("${doc.base.url}")
	private String baseUrl;

	private static final Logger logger = Logger.getLogger(WechatController.class);

	@Autowired
	private WxMpService wxService;

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Subject subject = getSubject(request, response);
		// 先判断是否需要重新登录
		if (subject.getPrincipal() == null) {
			String source =  request.getParameter("source");
			if("WECHAT".equals(source)){
				String relUrl = HttpUtil.getRelUrl(httpRequest);
				String wechatLoginUrl = baseUrl+"/wechat/login?call="+relUrl;
				String OAUTH_URL = wxService.oauth2buildAuthorizationUrl(wechatLoginUrl, WxConsts.OAUTH2_SCOPE_USER_INFO, "weixin_state");
				httpResponse.sendRedirect(OAUTH_URL);
				logger.info("OAUTH_URL:" + OAUTH_URL);
				return true;
			}
			return false;
		}

		return true;
	}

}
