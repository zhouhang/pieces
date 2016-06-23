package com.pieces.boss.shiro;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;


/**
 * 用于验证身份信息
 *
 */
public class BossAuthorizationFilter extends AuthorizationFilter {
	

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
     	 return true;
    }
    
}
