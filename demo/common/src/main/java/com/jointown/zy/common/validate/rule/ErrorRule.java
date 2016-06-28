package com.jointown.zy.common.validate.rule;

import java.lang.annotation.Annotation;

import com.jointown.zy.common.exception.ErrorRepository;

/**
 * 返回annotation的error属性
 * @author LiuPiao
 *
 */
public interface ErrorRule {
	
public static final String NAME = "error";
	
	
	/**
	 * 方法返回默认error
	 * @return
	 */
	public ErrorRepository error(Annotation annotation);
}
