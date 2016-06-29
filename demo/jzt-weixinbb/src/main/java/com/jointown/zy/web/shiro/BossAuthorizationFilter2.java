package com.jointown.zy.web.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.service.BossPermissionService;



/**
 * 用于验证身份信息
 * @author biran
 *
 */
public class BossAuthorizationFilter2 extends AuthorizationFilter {
	
	@Autowired
	private BossPermissionService bossPermissionService;
    //TODO - complete JavaDoc

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    	 HttpServletRequest req = (HttpServletRequest) request;
    	 Subject subject = getSubject(request, response);
    	//先判断是否需要重新登录
    	 if (subject.getPrincipal() == null) {
    		 return false;
         }
     	 String url=WebUtils.getPathWithinApplication(req);
     	 BossPermission p = new BossPermission();
     	 p.setOperationResource(url);
     	 BossShiroPermission permission = new BossShiroPermission(p);
     	 return subject.isPermitted(permission);
    }
    
}
