package com.ms.boss.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * 用于验证身份信息
 *
 */
public class BossAuthorizationFilter extends AuthorizationFilter {



    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		Subject subject = getSubject(request, response);

//		Session session = subject.getSession();
//		session.getAttributeKeys();
		//如果前台登录后，修改浏览器地址登录后台，会取到前台的的subject，所以在这里判断一下
//		if (session.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue()) == null ) {
//			return false;
//		}
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
