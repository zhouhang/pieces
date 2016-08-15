package com.pieces.biz.freemarker.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/8/12.
 */
public class IncludePageInterceptor extends HandlerInterceptorAdapter {


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            String viewName = modelAndView.getViewName();
            if (viewName != null && (viewName.startsWith("template") || viewName.startsWith("/template"))) {
                // freemarker 原生的IncludePage指令
                modelAndView.addObject("include_page", new CustomIncludePage(request, response));
            }
        }
    }
}
