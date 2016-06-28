package com.jointown.zy.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jointown.zy.common.exception.ErrorRepository;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface NumberValidate {
	/**
	 * 最小数字过滤
	 * @return
	 */
	int min() default 0;
	/**
	 * 最大数字过滤
	 * @return
	 */
	int max() default 0;
	/**
	 * 剔除時間限制,多个字符按逗号分割
	 * @return
	 */
	String except() default "";
	
	/**
	 * 定义逻辑与关系,多个过滤属性。 
	 * e.g. and("min","max")
	 * @return
	 */
	String[] and() default {};
	
	/**
	 * 绑定默认错误
	 * @return
	 */
	ErrorRepository error() default ErrorRepository.NUMBER_ERROR;
	
}


