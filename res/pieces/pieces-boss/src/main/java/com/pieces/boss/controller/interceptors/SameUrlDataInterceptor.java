package com.pieces.boss.controller.interceptors;


import com.pieces.boss.controller.exception.FormRepeatException;
import com.pieces.service.servlet.BodyReaderHttpServletRequestWrapper;
import com.pieces.tools.annotation.SameUrlData;
import org.apache.commons.io.IOUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by wangbin on 2016/12/28.
 */
public class SameUrlDataInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null) {
                if (repeatDataValidator(request)){
                   throw new FormRepeatException("不能提交相同的数据!");
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证同一个url数据是否相同提交  ,相同返回true
     * @param httpServletRequest
     * @return
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest)throws Exception {
        StringBuilder params = new StringBuilder();
//        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
//        String requestBody = IOUtils.toString(requestWrapper.getInputStream());
//        params.append(requestBody);
        for(Map.Entry<String, String[]> entry : httpServletRequest.getParameterMap().entrySet()){
            params.append(entry.getKey()).append(Arrays.asList(entry.getValue()).toString());
        }
        String url = httpServletRequest.getRequestURI();
        params.append(url);

        String nowUrlParams =params.toString();

        Object preUrlParams = httpServletRequest.getSession().getAttribute("repeatData");
        //如果上一个数据为null,表示还没有访问页面
        if (preUrlParams == null){
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
            return false;
        } else{
            //否则，已经访问过页面
            if (preUrlParams.toString().equals(nowUrlParams)) {
                return true;
            } else{
                //如果上次 url+数据 和本次url加数据不同，则不是重复提交
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
                return false;
            }

        }
    }


}
