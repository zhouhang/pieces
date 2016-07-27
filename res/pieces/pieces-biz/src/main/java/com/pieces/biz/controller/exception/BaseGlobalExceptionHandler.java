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

    protected static final Logger logger = null;
    protected static final String DEFAULT_ERROR_MESSAGE = "系统忙，请稍后再试";

    protected ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e, String viewName, HttpStatus status) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        String errorMsg =  DEFAULT_ERROR_MESSAGE;
        String errorStack = Throwables.getStackTraceAsString(e);
        getLogger().error("Request: {} raised {}", req.getRequestURI(), errorStack);
        String xRequested=req.getHeader("X-Requested-With");
        if(xRequested!=null && xRequested.indexOf("XMLHttpRequest")!=-1){
            return handleAjaxError(rsp, errorMsg, status);
        }

        return handleViewError(req.getRequestURL().toString(), errorStack, errorMsg, viewName);
    }

    protected ModelAndView handleViewError(String url, String errorStack, String errorMessage, String viewName) {
         return new ModelAndView("redirect:/user/login");
    }

    protected ModelAndView handleAjaxError(HttpServletResponse rsp, String errorMessage, HttpStatus status) throws IOException {
        WebUtil.print(rsp,new Result(false).info(DEFAULT_ERROR_MESSAGE));
        return null;
    }

    public Logger getLogger() {
        return LoggerFactory.getLogger(BaseGlobalExceptionHandler.class);
    }
}
