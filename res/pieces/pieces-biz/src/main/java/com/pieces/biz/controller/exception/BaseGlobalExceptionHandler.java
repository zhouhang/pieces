package com.pieces.biz.controller.exception;

import com.google.common.base.Throwables;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by wangbin on 2016/6/30.
 */
public class BaseGlobalExceptionHandler {

    //打印那个级别的日志
    protected static String LOG_LEVEL_ERROR = "ERROR";
    protected static String LOG_LEVEL_NONE = "NONE";

    protected static final String DEFAULT_ERROR_MESSAGE = "系统忙，请稍后再试";

    protected ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e, String viewName, HttpStatus status,String logLevel) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        String errorMsg =  DEFAULT_ERROR_MESSAGE;
        String errorStack = Throwables.getStackTraceAsString(e);
        switch (logLevel){
            case "NONE":
                break;
            default:
                getLogger().error("Request: {} raised {}", req.getRequestURI(), errorStack);
        }
        if(isAjaxRequest(req)){
            return handleAjaxError(rsp, errorMsg, status);
        }
        return handleViewError(req.getRequestURL().toString(), errorStack, errorMsg, viewName);
    }

    protected ModelAndView handleViewError(String url, String errorStack, String errorMessage, String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", errorStack);
        mav.addObject("url", url);
        mav.addObject("message", errorMessage);
        mav.addObject("timestamp", new Date());
        mav.setViewName(viewName);
        return mav;
    }

    protected ModelAndView handleAjaxError(HttpServletResponse rsp, String errorMessage, HttpStatus status) throws IOException {
        WebUtil.print(rsp,new Result(false).info(DEFAULT_ERROR_MESSAGE));
        return null;
    }

    public Logger getLogger() {
        return LoggerFactory.getLogger(BaseGlobalExceptionHandler.class);
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.indexOf("XMLHttpRequest")!=-1) {
            return true;
        } else {
            return false;
        }
    }



}
