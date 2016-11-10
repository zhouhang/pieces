package com.ms.boss.exception;

import com.google.common.base.Throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangbin on 2016/6/30.
 */
public class BaseGlobalExceptionHandler {

    private boolean debug = true;

    protected static final Logger logger = LoggerFactory.getLogger(BaseGlobalExceptionHandler.class);

    protected static final String DEFAULT_ERROR_MESSAGE = "系统忙，请稍后再试";


    protected ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e, String viewName, HttpStatus status) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        String errorMsg =  DEFAULT_ERROR_MESSAGE;
        String errorStack = Throwables.getStackTraceAsString(e);

        logger.error("Request: {} raised {}", req.getRequestURI(), errorStack);
        if (isAjaxRequest(req)) {
            return handleAjaxError(rsp, e);
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

    protected ModelAndView handleAjaxError(HttpServletResponse rsp, Exception e) {
        String msg = DEFAULT_ERROR_MESSAGE;
//        if (isValidAndBindException(e)) {
//            WebUtil.print(rsp,new Result(false).data(formatVaildAndBindError(e)));
//        } else {
//            WebUtil.print(rsp,new Result(false).info(msg));
//        }
        return null;
    }



    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.indexOf("XMLHttpRequest")!=-1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否数据绑定和验证错误
     * @return
     */
    public static boolean isValidAndBindException(Exception e){
        Boolean result = false;
        if(e instanceof BindException || e instanceof MethodArgumentNotValidException){
            result = true;
        }
        return result;
    }

    public static Map<String, String> formatVaildAndBindError(Exception e) {
        Map result = new HashMap<String, String>();
        if(e instanceof BindException) {
            BindException be = (BindException)e;
            for(ObjectError error : be.getAllErrors()){
                if(error instanceof FieldError) {
                    FieldError errorf = (FieldError)error;
                    result.put(errorf.getField(), error.getDefaultMessage());
                } else {
                    result.put("error", error.getDefaultMessage());
                }
            }
        } else if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException me = (MethodArgumentNotValidException)e;
            for(ObjectError error : me.getBindingResult().getAllErrors()){
                if(error instanceof FieldError) {
                    FieldError errorf = (FieldError)error;
                    result.put(errorf.getField(), error.getDefaultMessage());
                } else {
                    result.put("error", error.getDefaultMessage());
                }
            }
        }
        return result;
    }
}
