package com.jointown.zy.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jointown.zy.common.exception.ErrorRepository;

/**
 * 此annotation定义过滤失败对应错误信息。如果没有加上此annotation默认取各验证标签的defaultError属性.
 * 注意保持key和errors属性中的一一对应
 * @author LiuPiao
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Error {
	
	/**
	 * 此属性定义需要自定义错误的验证标签过滤属性，与errors属性顺序一一对应.
	 * 如果验证annotation中有and属性,则此次也写and,注意and会覆盖其中定义的子属性错误。
	 * @return
	 */
	String[] key() default {};
	
	/**
	 * 此属性定义key依次定义的过滤属性的绑定错误
	 *  
	 * @return ErrorRepository数组
	 */
	ErrorRepository[] errors() default {};
}
