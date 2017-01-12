package com.pieces.tools.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiao on 2017/1/9.
 *
 *
 * 防止重复提交注解，用于方法上
 * 在control方法上，设置needSaveToken()为true，此时拦截器会在Session中保存一个token
 * 在control方法上，设置needValidateToken()为true，此时拦截器会在Session中验证token,并且删除token
 * 需要防止重复提交的页面中需要添加
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityToken {
    boolean generateToken() default false;
    boolean validateToken() default false;
    //ajax请求如果重复提交后默认的JS回调方法
    String ajaxFailCallBack() default "HandleTokenFail";
}