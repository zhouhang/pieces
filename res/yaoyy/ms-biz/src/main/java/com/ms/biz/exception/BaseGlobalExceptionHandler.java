package com.ms.biz.exception;

import com.google.common.base.Throwables;
import com.ms.tools.entity.Result;
import com.ms.tools.exception.ControllerException;
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


    protected Result handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e) {
        String errorMsg =  DEFAULT_ERROR_MESSAGE;
        String errorStack = Throwables.getStackTraceAsString(e);
        logger.error("Request: {} raised {}", req.getRequestURI(), errorStack);
        if (e instanceof ControllerException) {
            throw (ControllerException)e;
        }
        return handleAjaxError(errorStack, e);
    }


    protected Result handleAjaxError(String errorStack, Exception e) {
        Result result;
        if (isValidAndBindException(e)) {
            result = Result.failVerification().data(formatVaildAndBindError(e));
        } else {
            result = Result.error().msg(debug?e.getMessage():DEFAULT_ERROR_MESSAGE);
        }
        return result;
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
