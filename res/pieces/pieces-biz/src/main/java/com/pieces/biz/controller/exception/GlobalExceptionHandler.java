package com.pieces.biz.controller.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pieces.dao.enums.SeoTypeEnum;
import com.pieces.dao.model.SeoSetting;
import com.pieces.service.SeoSettingService;
import com.pieces.tools.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * Created by wangbin on 2016/6/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler  extends BaseGlobalExceptionHandler{

    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    @Autowired
    private SeoSettingService seoSettingService;


    @ModelAttribute("urls")
    ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }

    @ModelAttribute("baseSetting")
    SeoSetting seoSetting(){
        return seoSettingService.findByType(SeoTypeEnum.BASE.getValue());
    }










    //404的异常就会被这个方法捕获
    @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "error-404", HttpStatus.NOT_FOUND,GlobalExceptionHandler.LOG_LEVEL_NONE).addObject("urls",urls());
    }



    //500的异常会被这个方法捕获
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return handleError(req, rsp, e, "error-500", HttpStatus.INTERNAL_SERVER_ERROR,GlobalExceptionHandler.LOG_LEVEL_ERROR).addObject("urls",urls());
    }



    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

}
