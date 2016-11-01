package com.ms.tools.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
    public static String getCookieValue(HttpServletRequest request, String name)throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies == null)	return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name,ck.getName())){
                return URLDecoder.decode(ck.getValue(),"utf-8");
            }
        }
        return null;
    }



    /**
     * 设置COOKIE
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) throws Exception {
        Cookie cookie = new Cookie(name, URLEncoder.encode(value,"utf-8"));
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name)throws Exception {
    	Cookie[] allCookie = request.getCookies();
    	if(allCookie != null ){
    		for( int i=0; i<allCookie.length; i++){
    			Cookie ck = allCookie[i];
    	        if( ck.getName().equals(name) ){
                    setCookie(response,name,"",0);
    	        }
    	     }
    	}
    }

}
