package com.jointown.zy.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jointown.zy.common.exception.ErrorRepository;

/**
 * Date类型的异常验证annotation. 各种过滤属性默认情况是或关系,只要满足一个就不通过验证,返回的错误也是对应此异常的一个错误
 * 統一解析時間為模式"yyyy-MM-dd HH:mm:ss"
 * @author LiuPiao
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface TimeValidate {
	/**
	 * 最小時間过滤 
	 * @return
	 */
	String min() default "";
	/**
	 * 最大時間过滤
	 * @return
	 */
	String max() default "";
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
	ErrorRepository error() default ErrorRepository.STRING_ERROR;
	
}
