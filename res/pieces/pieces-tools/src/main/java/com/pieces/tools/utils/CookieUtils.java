package com.pieces.tools.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * Created by wangbin on 2016/6/30.
 */
public class CookieUtils {

    /**
     * 获取COOKIE
     *
     * @param name
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null)	return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck;
        }
        return null;
    }

    /**
     * 获取COOKIE
     *
     * @param name
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null)	return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name,ck.getName()))
                return ck.getValue();
        }
        return null;
    }



    /**
     * 设置COOKIE
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie( HttpServletResponse response, String name,
                                 String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
    	Cookie[] allCookie = request.getCookies();
    	if(allCookie != null ){
    		for( int i=0; i<allCookie.length; i++){
    			Cookie ck = allCookie[i];
    	        if( ck.getName().equals(name) ){
    	        	ck.setMaxAge(0); // 设置cookie的周期为0，也就是删除cookie。。
    	        	response.addCookie( ck ); //必须要加上这一句，否则上面的一句就等于无效。结论：服务器修改cookie后，一定要调用addCookie()方法，重新添加到客户端中。。。
    	        }  
    	     }
    	}
    }

}
