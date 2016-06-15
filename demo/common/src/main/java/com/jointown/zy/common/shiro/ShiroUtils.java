package com.jointown.zy.common.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

public class ShiroUtils {
	
	/**
	 * 重定向到成功页面,加入post跳转逻辑
	 */
	public static void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl)
            throws IOException {
        String successUrl = null;
        boolean contextRelative = true;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null) {
        	String savedQueryString = savedRequest.getQueryString();
        	if(StringUtils.isNotEmpty(savedQueryString) && savedQueryString.startsWith("go=")){
        		//找go参数
        		if(savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD) 
        				|| savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.POST_METHOD)){
        			successUrl = savedQueryString.substring("go=".length());
                    contextRelative = false;
            	}
        	}else{
        		if(savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)){
                    contextRelative = false;
                    successUrl = savedRequest.getRequestUrl();
            	}
        	}
        }

        if (successUrl == null) {
            successUrl = fallbackUrl;
        }

        if (successUrl == null) {
            throw new IllegalStateException("Success URL not available via saved request or via the " +
                    "successUrlFallback method parameter. One of these must be non-null for " +
                    "issueSuccessRedirect() to work.");
        }

        WebUtils.issueRedirect(request, response, successUrl, null, contextRelative);
    }
	
	
	public static String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

}
