package com.jointown.zy.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jointown.zy.common.exception.ErrorRepository;

/**
 * String类型的异常验证annotation. 各种过滤属性默认情况是或关系,只要满足一个就不通过验证,返回的错误也是对应此异常的一个错误
 * @author LiuPiao
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface StringValidate {
	/**
	 * 最小字符过滤
	 * @return
	 */
	int min() default 0;
	/**
	 * 最大字符过滤
	 * @return
	 */
	int max() default 0;
	/**
	 * 剔除字符限制,多个字符按逗号分割
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
	ErrorRepository error() default ErrorRepository.STRING_ERROR;
	
}
