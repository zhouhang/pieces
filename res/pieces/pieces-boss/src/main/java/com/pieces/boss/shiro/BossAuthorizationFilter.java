package com.pieces.boss.shiro;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;


/**
 * 用于验证身份信息
 *
 */
public class BossAuthorizationFilter extends AuthorizationFilter {
	


    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		// 先判断是否需要重新登录
		if (subject.getPrincipal() == null) {
			return false;
		}

		return true;
//		p.setOperationResource(url);

//		BossShiroPermission permission = new BossShiroPermission(p);
//		return subject.isPermitted(permission);
//		return true;
    }
    
}
