package com.pieces.biz.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.pieces.service.enums.SessionEnum;

/**
 * 用于验证身份信息
 *
 */
public class BizAuthorizationFilter extends AuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		Subject subject = getSubject(request, response);
		// 先判断是否需要重新登录
		if (subject.getPrincipal() == null) {
			return false;
		}
//		String url = WebUtils.getPathWithinApplication(req);
//		BossPermission p = new BossPermission();
//		p.setOperationResource(url);
//		BossShiroPermission permission = new BossShiroPermission(p);
//		return subject.isPermitted(permission);
		return true;
	}

}
