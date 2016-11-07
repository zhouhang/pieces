package com.ms.biz.exception;

import com.ms.tools.entity.Result;
import com.ms.tools.exception.ControllerException;
import com.ms.tools.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangbin on 2016/6/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler  extends BaseGlobalExceptionHandler{

    //500的异常会被这个方法捕获
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e) {
        return super.handleError(req, rsp, e);
    }

    //404的异常会被这个方法捕获
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleViewError(HttpServletRequest req, HttpServletResponse rsp,
                                        Exception e) {
        rsp.setStatus(404);
        return new ModelAndView("error/404", null);
    }
}
