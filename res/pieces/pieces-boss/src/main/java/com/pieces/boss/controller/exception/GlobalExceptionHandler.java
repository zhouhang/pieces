package com.pieces.boss.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/6/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler  extends BaseGlobalExceptionHandler{

    @Autowired
    private ResourceUrlProvider resourceUrlProvider;


    @ModelAttribute("urls")
    ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }

    //500的异常会被这个方法捕获
    @ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleShiroError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "no-permission", HttpStatus.INTERNAL_SERVER_ERROR).addObject("urls",urls());
    }


    //订单重复提交错误处理
    @ExceptionHandler(FormRepeatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleErrorTokenError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "error-front", HttpStatus.INTERNAL_SERVER_ERROR).addObject("urls",urls());
    }

    //500的异常会被这个方法捕获
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "error-front", HttpStatus.INTERNAL_SERVER_ERROR).addObject("urls",urls());
    }



    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

}
