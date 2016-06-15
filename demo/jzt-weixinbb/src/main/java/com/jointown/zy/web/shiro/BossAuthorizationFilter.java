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
public class BossAuthorizationFilter extends AuthorizationFilter {
	
	@Autowired
	private BossPermissionService bossPermissionService;
    //TODO - complete JavaDoc

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    	 HttpServletRequest req = (HttpServletRequest) request;
    	 Subject subject = getSubject(request, response);
    	//先判断是否需要重新登录
    	boolean result = false;
    	 if (subject.getPrincipal() == null) {
    		 result = false;
         }else{
        	
         	String url=WebUtils.getPathWithinApplication(req);
         	if(!checkPermission(url)) return true;
             
             result= subject.isPermittedAll(url);
         }
    	return result;
    }
    
    
    //是否是菜单中一个
    public boolean checkPermission(String url){
    	 //通过url获取BossPermission信息
		BossPermission bossPermissions = bossPermissionService.findBossPermissionByResource(url);
		if(bossPermissions==null){
			return false;
		}
    	return true;
    }
}
