package com.ms.boss.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/6/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler  extends BaseGlobalExceptionHandler{


    //500的异常会被这个方法捕获
    @ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleShiroError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "/error/no-permission", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //500的异常会被这个方法捕获
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "/error/500", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
