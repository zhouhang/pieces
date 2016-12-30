package com.pieces.boss.controller.interceptors;

import com.pieces.boss.controller.exception.FormRepeatException;
import com.pieces.tools.annotation.TokenHold;
import com.pieces.tools.annotation.TokenVerify;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by wangbin on 2016/12/28.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            TokenHold tokenHold = method.getAnnotation(TokenHold.class);
            if (tokenHold != null) {
                request.getSession(false).setAttribute(tokenHold.value(), UUID.randomUUID().toString());
            }

            TokenVerify tokenVerify = method.getAnnotation(TokenVerify.class);
            if(tokenVerify!=null){
                if (isRepeatSubmit(request,tokenVerify.value())) {
                    throw new FormRepeatException("订单重复提交，请刷新页面后再尝试！");
                }
                request.getSession(false).removeAttribute(tokenVerify.value());
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request,String tokenKey) {
        String serverToken = (String) request.getSession(false).getAttribute(tokenKey);
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

}
