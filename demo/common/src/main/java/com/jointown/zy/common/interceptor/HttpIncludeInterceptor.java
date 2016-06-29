package com.jointown.zy.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import freemarker.ext.servlet.IncludePage;

/**
 * 
 * 描述： 用于ftl页面中动态加载其它的ftl页面
 * 
 * 日期： 2014年12月22日
 * 
 * 作者： 赵航
 *
 * 版本： V1.0
 */
public class HttpIncludeInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String viewName = modelAndView.getViewName();  
        if(viewName != null && !viewName.startsWith("redirect:")) {  
            //笔者扩展的httpInclude  
            modelAndView.addObject("httpInclude", new HttpInclude(request, response));  
              
            //freemarker 原生的IncludePage指令  
            modelAndView.addObject("include_page", new IncludePage(request, response));  
        }  
		super.postHandle(request, response, handler, modelAndView);
	}
}
